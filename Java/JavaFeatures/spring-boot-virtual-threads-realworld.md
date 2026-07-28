# Virtual Threads in Spring Boot: A Real-World Example, and When to Use Which

This guide walks through an actual Spring Boot scenario — a checkout service calling several downstream services — to show concretely what virtual threads change, then gives a decision framework for choosing virtual vs. platform threads.

---

## 1. The real-world scenario

Imagine an e-commerce **checkout service**. A single checkout request needs to:

1. Call the **inventory service** to confirm stock (~80ms)
2. Call the **payment service** to charge the card (~150ms)
3. Call the **user profile service** to fetch loyalty points (~60ms)
4. Write the final order to the **database** (~30ms)

Total time per request: roughly 320ms, almost all of it spent **waiting** on other services and the database — the actual CPU work your checkout code does (some validation, some JSON mapping) is maybe 2-3ms.

This is an extremely common shape for backend services, and it's exactly the shape that virtual threads were built for: **lots of concurrent requests, each mostly idle/waiting rather than crunching numbers.**

---

## 2. Baseline: this service on traditional platform threads

A default Spring Boot app (embedded Tomcat) handles each incoming HTTP request on a **platform thread** pulled from a fixed-size pool. Out of the box, Tomcat's default max is **200 threads**.

```java
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;
    private final ProfileClient profileClient;
    private final OrderRepository orderRepository;

    public CheckoutController(InventoryClient inventoryClient,
                               PaymentClient paymentClient,
                               ProfileClient profileClient,
                               OrderRepository orderRepository) {
        this.inventoryClient = inventoryClient;
        this.paymentClient = paymentClient;
        this.profileClient = profileClient;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public OrderConfirmation checkout(@RequestBody CheckoutRequest request) {
        // Each of these is a BLOCKING call — the thread sits idle while waiting
        boolean inStock = inventoryClient.checkStock(request.itemId());     // ~80ms
        PaymentResult payment = paymentClient.charge(request.cardToken());  // ~150ms
        int loyaltyPoints = profileClient.getLoyaltyPoints(request.userId()); // ~60ms

        Order order = new Order(request.itemId(), payment.transactionId(), loyaltyPoints);
        orderRepository.save(order); // ~30ms

        return new OrderConfirmation(order.id(), payment.transactionId());
    }
}
```

Nothing about this code looks "wrong" — it's simple, sequential, easy-to-read blocking code. The problem shows up under load.

**What happens during a traffic spike:** if 5,000 checkout requests arrive within a couple of seconds, but Tomcat only has 200 platform threads, only 200 requests can be "in flight" at any moment. The other 4,800 requests **queue up**, waiting for a thread to free up — even though every one of those 200 in-flight threads is mostly just sitting idle waiting on the payment service, not actually using the CPU. Your CPU might be sitting at 5% utilization while your users experience multi-second delays or timeouts, purely because you've run out of *threads*, not CPU capacity.

![Before: a fixed pool of platform threads becomes the bottleneck even though the CPU is barely used](images/spring-before-platform-threads.svg)

This is the single most common real-world justification for virtual threads: **your service isn't CPU-bound, it's thread-count-bound.**

---

## 3. Turning on virtual threads — the actual change

Here's the part that surprises people: **the controller code above does not change at all.** Spring Boot 3.2+ lets you switch the underlying thread model with a single property:

```properties
# application.properties
spring.threads.virtual.enabled=true
```

With this one line, Spring Boot configures the embedded Tomcat connector to hand off each incoming request to a **virtual thread** instead of a pooled platform thread. Your `CheckoutController` code above is untouched — same blocking calls, same simple sequential logic.

If you're not using Spring Boot's auto-configuration (e.g., a plain Java app, or you want explicit control), you can wire it manually:

```java
@Bean
public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
    return protocolHandler ->
        protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
}
```

Or, if you're calling downstream services yourself via an `ExecutorService` (e.g., fanning out manually rather than relying on Spring MVC's per-request threading), you'd simply construct it with:

```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
```

**What changes under load now:** with virtual threads, there's effectively no fixed cap like Tomcat's 200. Each of the 5,000 incoming checkout requests gets its own cheap virtual thread. While a request is blocked waiting on the payment service, its virtual thread unmounts and frees up the small pool of real carrier threads (roughly one per CPU core) to go process other requests. Your CPU stays productively busy instead of most requests just queuing up waiting for a platform thread to free.

![After: virtual threads let thousands of blocked requests coexist, using carrier threads only when doing real CPU work](images/spring-after-virtual-threads.svg)

---

## 4. A more explicit example: fanning out calls concurrently

The checkout example above calls services one after another — but you could also run the three independent downstream calls (inventory, payment, profile) **concurrently**, since none of them depend on each other's result:

```java
@PostMapping("/concurrent")
public OrderConfirmation checkoutConcurrent(@RequestBody CheckoutRequest request) throws Exception {
    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

        var stockCheck   = scope.fork(() -> inventoryClient.checkStock(request.itemId()));
        var paymentCall  = scope.fork(() -> paymentClient.charge(request.cardToken()));
        var loyaltyCall  = scope.fork(() -> profileClient.getLoyaltyPoints(request.userId()));

        scope.join();           // waits for all three
        scope.throwIfFailed();  // propagates the first failure, if any, and cancels the rest

        Order order = new Order(request.itemId(),
                                 paymentCall.resultNow().transactionId(),
                                 loyaltyCall.resultNow());
        orderRepository.save(order);

        return new OrderConfirmation(order.id(), paymentCall.resultNow().transactionId());
    }
}
```

Because virtual threads are so cheap to create, `scope.fork()` here effectively costs nothing extra — you get all three downstream calls running at the same time (bringing total request latency from ~320ms down to roughly the slowest single call, ~150ms), while `StructuredTaskScope` guarantees that if the payment call fails, the inventory and profile calls are cancelled rather than left running pointlessly.

---

## 5. Gotchas specific to Spring Boot you should actually know

**5.1 Your database connection pool is still a real limit.** Virtual threads remove the *thread* bottleneck, but HikariCP's connection pool (default max 10 connections) is unaffected — if 5,000 virtual threads all try to hit the database at once, you'll just move the bottleneck to "waiting for a DB connection" instead of "waiting for a thread." You may need to size your connection pool (and the downstream service's own capacity) based on realistic concurrent load, not just assume virtual threads make everything infinitely scalable.

**5.2 Spring's `synchronized`-based code paths can pin.** Some older code (and a few legacy libraries) uses `synchronized` blocks around potentially blocking operations. If your checkout path passes through such code, the virtual thread handling that request gets **pinned** to its carrier thread for the duration — undermining the benefit for that specific request. Worth auditing hot paths (especially any custom caching or legacy synchronization code) if you adopt virtual threads at scale.

**5.3 `ThreadLocal`-heavy code (including MDC-based logging context) needs a second look.** Libraries like Logback's MDC (used to attach a request/trace ID to every log line) traditionally use `ThreadLocal`. This still works with virtual threads, but at very high thread counts it's worth checking whether your logging/tracing library has been updated to use Java 21's `ScopedValue` internally, since `ThreadLocal` per virtual thread at massive scale can add memory overhead.

**5.4 It's not a magic performance switch for CPU-bound work.** If your checkout logic did heavy computation (e.g., fraud-scoring with a local ML model) rather than waiting on network calls, virtual threads wouldn't speed that part up — you're still bound by physical CPU cores for the computation itself.

---

## 6. When to use virtual threads vs. platform threads

| Situation | Recommendation | Why |
|---|---|---|
| Web request handling with blocking I/O (REST calls, JDBC, JPA/Hibernate) | **Virtual threads** | This is the textbook case — many concurrent requests, each mostly waiting |
| Fanning out to multiple independent downstream services per request | **Virtual threads** (+ `StructuredTaskScope`) | Cheap to fork many, structured cancellation on failure |
| CPU-bound computation (image processing, encryption, heavy math, ML inference) | **Platform threads**, sized to CPU core count | Virtual threads add no benefit — you're limited by physical cores either way |
| Code with long-held `synchronized` blocks you can't easily change (legacy library) | **Platform threads** | Virtual threads would just get pinned repeatedly, gaining nothing |
| A small, fixed number of long-running background workers (e.g., a handful of Kafka consumers) | **Platform threads** are fine | You were never going to have thousands of these; the platform thread cost is negligible at this scale |
| Batch job needing to process millions of mostly-I/O-bound items in parallel (e.g., calling an API per row in a huge CSV) | **Virtual threads** | Exactly the "many things, mostly waiting" scenario virtual threads target |
| High-frequency trading / ultra-low-latency path where you need precise, predictable thread pinning to specific CPU cores | **Platform threads** with careful affinity tuning | Virtual threads' scheduling is not designed for this level of low-level control |
| General application scheduled tasks (`@Scheduled`), a handful running periodically | **Platform threads** (default) are usually fine | Low concurrency; no real benefit to switching |

![Decision flow: virtual threads vs platform threads](images/virtual-vs-platform-decision.svg)

---

## 7. The one-paragraph summary

Use **virtual threads** whenever you have a lot of concurrent units of work that each spend most of their time **waiting** on something external (a database, another service, a file, a network call) — this is most backend web request handling. Stick with **platform threads** when your work is genuinely **CPU-bound**, when you're stuck with legacy code that uses long `synchronized` blocks you can't safely change, or when you only ever need a small, fixed number of threads to begin with — in those cases, virtual threads add complexity without adding benefit.
