# Java 21 — Elaborate Feature Guide + Senior Interview Q&A

Java 21 is an LTS release and arguably the most significant Java release since Java 8 — it finalizes several features that were preview in 17 (pattern matching for switch, record patterns) and introduces virtual threads, which fundamentally changes how the JVM handles concurrency. Expect this release to dominate senior-level interviews for years.

---

## PART 1: ELABORATE EXPLANATIONS

### 1. Virtual Threads (JEP 444) — the headline feature

**The problem it solves:** Traditional Java threads are thin wrappers over OS threads. OS threads are expensive — each one reserves a large stack (often 1MB by default) and involves the OS scheduler. This hard-caps how many concurrent threads a JVM can realistically run (typically thousands, not millions), which is why high-throughput I/O-bound services historically relied on asynchronous, non-blocking code (reactive streams, `CompletableFuture` chains, callback-based frameworks like Netty) — not because async code is more readable, but because it was the only way to handle massive concurrency without exhausting OS threads.

**How it works mechanically:** A virtual thread is a lightweight thread managed by the JVM itself, not the OS. Many virtual threads are multiplexed onto a small pool of *carrier* OS threads (the `ForkJoinPool` by default). When a virtual thread performs a blocking operation (I/O, `Thread.sleep`, blocking on a lock), the JVM detects this and **unmounts** the virtual thread from its carrier thread, freeing the carrier to run other virtual threads. When the blocking operation completes, the virtual thread is remounted on any available carrier thread to continue.

```java
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 100_000; i++) {
        executor.submit(() -> {
            // blocking I/O here is cheap — this thread will unmount
            // during the blocking call and free up the carrier thread
            String result = callSlowService();
            process(result);
        });
    }
}
```

**Why this is transformative:** You get the simple, readable, sequential blocking style of code (no reactive chains, no callback hell) while achieving the throughput characteristics of async code. The "one thread per request" model — which was always the simplest mental model, but scaled poorly — suddenly scales to millions of concurrent virtual threads.

**Critical nuances senior engineers must know:**
- **Pinning:** a virtual thread cannot unmount while inside a `synchronized` block or method, or while executing certain native code — it stays pinned to its carrier thread for the duration. Heavy use of `synchronized` for long-held locks defeats the purpose of virtual threads; the JDK team recommends migrating to `java.util.concurrent.locks.ReentrantLock` for code that will run inside virtual threads.
- **Not for CPU-bound work:** virtual threads don't add parallelism for CPU-bound tasks — they only help when threads spend time blocked/waiting. Running CPU-bound work on millions of virtual threads just contends for the same limited CPU cores.
- **Thread pools become mostly obsolete for I/O-bound tasks:** the old pattern of carefully sizing a thread pool to avoid OS thread exhaustion is no longer necessary for virtual threads — the recommended pattern is "one virtual thread per task," not pooling virtual threads (they're cheap to create and discard).
- **`ThreadLocal` still works but is discouraged at scale:** with potentially millions of virtual threads, heavy `ThreadLocal` usage can bloat memory; Java 21 also introduces **Scoped Values** (preview) as a lighter-weight alternative for passing context.

---

### 2. Sequenced Collections (JEP 431)

**The problem it solves:** Before Java 21, there was no common interface expressing "this collection has a defined encounter order, with a first and last element." `List` has one, `LinkedHashSet` has one, `LinkedHashMap` has one — but they had no shared supertype capturing that, so there was no uniform way to say "give me the first element" or "give me this in reverse order" across all of them. Getting the last element of a `LinkedHashSet`, for instance, required awkward workarounds (iterating to the end, or converting to an array).

**How it works mechanically:** Three new interfaces were introduced:

```java
interface SequencedCollection<E> extends Collection<E> {
    SequencedCollection<E> reversed();
    void addFirst(E e);
    void addLast(E e);
    E getFirst();
    E getLast();
    E removeFirst();
    E removeLast();
}
```

`List`, `Deque`, and `LinkedHashSet` now implement `SequencedCollection`. Similarly, `SequencedMap` and `SequencedSet` were added, and `LinkedHashMap`/`TreeMap` now implement `SequencedMap`.

```java
LinkedHashSet<String> set = new LinkedHashSet<>(List.of("a", "b", "c"));
String first = set.getFirst();       // "a" — no workaround needed
String last  = set.getLast();        // "c"
var reversed = set.reversed();       // view, not a copy
```

**Senior-level point:** `.reversed()` returns a *view* — it's backed by the original collection, not a defensive copy, so mutations propagate both ways. This is a subtle but important distinction interviewers like to probe: "does calling `.reversed()` allocate a new collection?"

---

### 3. Record Patterns (JEP 440) — finalized in 21

**The problem it solves:** Pattern matching for `instanceof`/`switch` (Java 17) let you match a type and bind a single variable, but if the value was a `record`, you still had to manually call each accessor to get at its components. Record patterns let you destructure a record directly in the pattern.

```java
record Point(int x, int y) {}

// Java 17 style — match type, then manually pull out fields
if (obj instanceof Point p) {
    int x = p.x();
    int y = p.y();
}

// Java 21 — destructure directly
if (obj instanceof Point(int x, int y)) {
    // x and y are already bound, no accessor calls needed
}
```

**Where this becomes powerful — nested destructuring:**

```java
record Point(int x, int y) {}
record Line(Point start, Point end) {}

static String describe(Object obj) {
    return switch (obj) {
        case Line(Point(var x1, var y1), Point(var x2, var y2)) ->
            "Line from (%d,%d) to (%d,%d)".formatted(x1, y1, x2, y2);
        default -> "unknown";
    };
}
```

This nested pattern matches a `Line` *and* pulls apart both of its `Point` components in one expression — something that previously required multiple lines of manual accessor calls.

**Senior-level nuance:** You can mix concrete types and `var` freely inside a record pattern, and you can add `when` guards just like with type patterns. Record patterns combined with sealed interfaces give you the closest thing Java has to full algebraic data type destructuring, comparable to pattern matching in Scala, Kotlin, or Haskell.

---

### 4. Pattern Matching for `switch` (JEP 441) — finalized in 21

This was preview since Java 17; Java 21 finalizes it. The core mechanics (type patterns, guarded patterns with `when`, `case null`, exhaustiveness over sealed hierarchies) are unchanged from the preview described in the Java 17 guide — what matters for interviews is that **it's now production-safe, no `--enable-preview` flag required**, and it's commonly combined with record patterns:

```java
sealed interface Shape permits Circle, Rectangle {}
record Circle(double radius) implements Shape {}
record Rectangle(double width, double height) implements Shape {}

static double area(Shape shape) {
    return switch (shape) {
        case Circle(double r) -> Math.PI * r * r;
        case Rectangle(double w, double h) -> w * h;
    };
}
```

No `default` needed — the compiler proves exhaustiveness over the sealed hierarchy, and the record pattern destructures each shape's fields inline.

---

### 5. Generational ZGC (JEP 439)

**The problem it solves:** ZGC (the low-latency garbage collector introduced in Java 15) originally treated all objects the same regardless of age. But most objects die young (the "generational hypothesis" that underlies most modern GC design) — treating young and old objects identically means ZGC does more work scanning long-lived objects repeatedly, unnecessarily.

**How it works:** Generational ZGC splits the heap into young and old generations, focusing collection effort on the young generation (where most garbage is) far more frequently than the old generation, similar in spirit to G1's generational design but while preserving ZGC's sub-millisecond pause time guarantees even at multi-terabyte heap sizes.

**Senior-level point:** In Java 21 it's opt-in (`-XX:+UseZGC -XX:+ZGenerational`); it became the default ZGC mode starting in Java 23. Interviewers may ask you to compare it against G1 (Java's default collector) — G1 is throughput-oriented with occasional longer pauses, ZGC/Generational ZGC trades some throughput for consistently very low pause times, which matters most for latency-sensitive services (trading systems, real-time APIs).

---

### 6. Virtual Thread-Adjacent: Structured Concurrency (JEP 453, Preview)

**The problem it solves:** When you fan out work across multiple threads (e.g., call two services concurrently and combine results), error handling and cancellation are notoriously easy to get wrong with raw `ExecutorService`/`Future` code — if one subtask fails, the others may keep running unnecessarily, and cancellation logic is manual and error-prone.

**How it works:**

```java
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Future<String> user = scope.fork(() -> fetchUser());
    Future<String> order = scope.fork(() -> fetchOrder());

    scope.join();           // wait for both, or fail fast if either fails
    scope.throwIfFailed();  // propagate the first exception, if any

    process(user.resultNow(), order.resultNow());
}
```

**Senior-level point:** `StructuredTaskScope` treats a group of related concurrent subtasks as a single unit — if the scope exits (successfully, by exception, or by cancellation), all forked subtasks are guaranteed to have completed or been cancelled before control returns, which eliminates a whole class of "leaked" background threads that outlive their logical parent task. This directly complements virtual threads — cheap threads make fanning out many subtasks feasible, and structured concurrency makes managing that fan-out safe.

---

### 7. Scoped Values (JEP 446, Preview)

**The problem it solves:** `ThreadLocal` was traditionally used to pass implicit context (e.g., a request ID, a security principal) down a call stack without threading it through every method signature. But `ThreadLocal` is mutable, inheritable in ways that are easy to misuse, and doesn't clean up automatically — with millions of virtual threads, this becomes a real memory and correctness concern.

**How it works:**

```java
static final ScopedValue<String> USER_ID = ScopedValue.newInstance();

ScopedValue.where(USER_ID, "user-123").run(() -> {
    // anything called from here can read USER_ID.get()
    processRequest();
});
// USER_ID is automatically and deterministically unbound here
```

**Senior-level point:** Scoped values are immutable for the duration of the scope (unlike `ThreadLocal`, which can be reassigned at any point) and are automatically cleared when the scope exits — no risk of a stale value leaking into a reused thread (a classic `ThreadLocal` + thread-pool bug). They're designed specifically to pair well with virtual threads and structured concurrency.

---

### 8. Other Notable Java 21 Additions (brief)

- **Sequenced Collections** (already covered above in detail).
- **`String.templates` were NOT part of 21** — this is a common point of confusion; string templates were previewed later (Java 21 actually did include a *preview* of this in some builds discourse, but the officially recognized JEP 430 "String Templates" preview landed in Java 21 as a preview feature — worth verifying exact JEP number if asked, since it was later withdrawn/redesigned before finalization).
- **Unnamed patterns and variables** (JEP 443, preview) — using `_` for a variable/pattern you don't care about, e.g., `case Point(var x, var _) -> ...` when you only need `x`.
- **Foreign Function & Memory API** — moved from incubator (17) to a second preview in 21, continuing to mature toward the finalized version.

---

## PART 2: SENIOR-LEVEL INTERVIEW Q&A

### Virtual Threads

**Q1: What is the core difference between a platform thread and a virtual thread?**
A: A platform thread is a 1:1 wrapper over an OS thread — expensive to create, limited in number (typically thousands). A virtual thread is a JVM-managed lightweight thread; many virtual threads are multiplexed onto a small pool of carrier (platform) threads, and the JVM automatically unmounts a virtual thread from its carrier when it blocks, allowing millions of virtual threads to exist concurrently.

**Q2: What is "pinning," and why is it a problem?**
A: Pinning happens when a virtual thread cannot be unmounted from its carrier thread during a blocking operation — most commonly inside a `synchronized` block, or during certain native calls. A pinned virtual thread occupies its carrier thread for the full duration of the block, which defeats the scalability benefit of virtual threads. The recommended mitigation is replacing long-held `synchronized` blocks with `java.util.concurrent.locks.ReentrantLock` in code paths expected to run on virtual threads.

**Q3: Should you still use fixed-size thread pools with virtual threads?**
A: Generally no, for I/O-bound work — the recommended pattern is "one virtual thread per task" via `Executors.newVirtualThreadPerTaskExecutor()`, since virtual threads are cheap to create and don't need to be pooled/reused the way expensive OS threads did. Pooling virtual threads defeats their purpose and can even limit concurrency unnecessarily.

**Q4: Do virtual threads help CPU-bound workloads?**
A: No — virtual threads only help when threads spend significant time blocked (I/O, waiting on locks, sleeping). For CPU-bound work, you're still bounded by the number of physical CPU cores; adding more virtual threads just increases contention for the same cores.

**Q5: How would you migrate an existing reactive (e.g., WebFlux/reactive streams) service to virtual threads, and would you always recommend doing so?**
A: You could replace non-blocking, callback-based I/O calls with simple blocking calls running on virtual threads, potentially simplifying code significantly. However, this isn't a universal recommendation — reactive frameworks offer other capabilities (backpressure, complex operator chains for stream transformation) that virtual threads alone don't replace; the decision depends on whether the primary reason for reactive style was scalability (which virtual threads now address more simply) or genuine need for its operators/backpressure semantics.

---

### Sequenced Collections

**Q6: Why were Sequenced Collections needed if `List` already had `get(0)` and index-based access?**
A: The gap wasn't in `List` — it was in `LinkedHashSet`, `LinkedHashMap`, and similar ordered-but-non-indexed collections, which had a defined encounter order but no uniform API to access the first/last element or get a reversed view. `SequencedCollection`/`SequencedSet`/`SequencedMap` retrofit a common interface across all of these.

**Q7: Does `.reversed()` copy the collection?**
A: No — it returns a view backed by the original collection. Mutating the original is reflected in the reversed view and vice versa.

---

### Record Patterns & Pattern Matching for Switch

**Q8: What's the difference between pattern matching for `instanceof` (Java 17) and record patterns (Java 21)?**
A: The Java 17 feature lets you match a type and bind one variable of that type. Record patterns extend this to destructure a record's components directly in the pattern — including recursively, matching nested records in one expression — instead of matching the outer type and then manually calling accessors.

**Q9: How do sealed interfaces, records, and pattern-matching switch work together to eliminate `default` branches?**
A: A sealed interface declares a closed, known set of permitted subtypes. If every permitted subtype is a record and each is handled in its own `case` branch (potentially destructured via a record pattern), the compiler can statically verify every possible subtype is covered, making a `default` branch unnecessary — and if a new permitted subtype is added later without updating this switch, the code fails to compile rather than silently falling through at runtime.

**Q10: What does a `when` guard add to a type or record pattern?**
A: An additional boolean condition evaluated after a successful pattern match, letting you match on both type/shape and value, e.g., `case Circle(double r) when r > 100 -> "large circle"`.

---

### GC and JVM Internals

**Q11: How does Generational ZGC differ from the original (non-generational) ZGC?**
A: The original ZGC treated the whole heap uniformly regardless of object age, which meant repeatedly rescanning long-lived objects unnecessarily. Generational ZGC splits the heap into young and old generations and focuses collection effort on the young generation, where most garbage is created — following the generational hypothesis — while retaining ZGC's sub-millisecond pause guarantees.

**Q12: When would you choose G1 over ZGC, or vice versa?**
A: G1 is the default collector and optimizes for overall throughput with pause times that are usually short but not strictly bounded. ZGC (especially Generational ZGC) targets extremely low, consistent pause times even at very large heap sizes, at some cost to raw throughput — appropriate for latency-sensitive systems (trading platforms, real-time bidding, interactive APIs) where occasional longer G1 pauses are unacceptable.

---

### Structured Concurrency & Scoped Values

**Q13: What problem does `StructuredTaskScope` solve that raw `ExecutorService` + `Future` doesn't?**
A: It enforces that a group of concurrently forked subtasks is treated as a single unit of work — if the scope exits (success, failure, or cancellation), all forked subtasks are guaranteed to have completed or been cancelled first. Raw executor-based fan-out has no such guarantee, making it easy to leak background threads that outlive the logical operation that spawned them or to leave a failed sibling task running needlessly.

**Q14: Why introduce Scoped Values when `ThreadLocal` already exists?**
A: `ThreadLocal` values are mutable at any point during their lifetime and don't automatically clean up, which is risky with thread pooling and increasingly problematic at the scale virtual threads enable (potentially millions of threads). Scoped values are immutable for the duration of a well-defined scope and are automatically and deterministically unbound when the scope exits, eliminating the classic "stale ThreadLocal value leaks into a reused pooled thread" bug class.

---

### Synthesis / Design Questions

**Q15: Design a concurrent service that fetches data from three independent downstream services and combines the results, using Java 21 features. What would you use, and why?**
A: Use virtual threads (via `Executors.newVirtualThreadPerTaskExecutor()` or directly within a `StructuredTaskScope`) to fork three concurrent blocking calls cheaply, wrap them in a `StructuredTaskScope.ShutdownOnFailure()` so that a failure in any one call cancels the others and propagates the error cleanly, and use Scoped Values (if context like a request ID needs to flow into each subtask) instead of `ThreadLocal` to avoid stale-context bugs. This replaces what would previously have required reactive operators or manual executor/future bookkeeping with straightforward, sequential-looking blocking code.

**Q16: A teammate proposes keeping heavy use of `synchronized` blocks when migrating a service to virtual threads "because it's simpler." What would you flag?**
A: Long-held `synchronized` blocks pin the virtual thread to its carrier thread for the block's duration, preventing the JVM from unmounting it during any blocking operation inside — which can severely limit the scalability benefit of using virtual threads in the first place. Recommend auditing for long-held locks and migrating hot paths to `ReentrantLock`, reserving `synchronized` for short, non-blocking critical sections where pinning is a non-issue.

**Q17: What's the practical difference between "Java 17 finalized pattern matching for switch" versus "Java 21 finalized pattern matching for switch," if the syntax looks similar?**
A: In Java 17, pattern matching for switch (JEP 406) was a *preview* feature — it required `--enable-preview` to compile and run, and its final semantics weren't guaranteed to be stable. Java 21 (JEP 441) finalizes the feature with no preview flag needed, and simultaneously finalizes record patterns (JEP 440), which significantly extend what you can express in a `case` clause (destructuring, not just type-matching) compared to what existed in the 17 preview.
