# Java 17 — Elaborate Concept Guide + Interview Question Bank

---

## PART 1: ELABORATE EXPLANATIONS

### 1. Sealed Classes (JEP 409)

**The problem it solves:** Before sealed classes, Java only offered two extremes for controlling inheritance — `final` (nobody can extend) or open (anyone can extend). There was no middle ground for saying "this type can be extended, but only by a specific, known set of types." This mattered a lot for domain modeling: imagine a `PaymentMethod` type. You want other developers to be able to see and reason about every possible payment method in the codebase, but you don't want some unrelated module quietly adding a new subclass that nobody accounted for in business logic.

**How it works mechanically:** A `sealed` class or interface declares a `permits` clause listing its allowed direct subtypes:

```java
public sealed interface PaymentMethod permits CreditCard, PayPal, BankTransfer {}
```

Each permitted subtype must itself declare how it continues the hierarchy:
- `final` — no further extension
- `sealed` — extension continues, but still restricted to a `permits` list
- `non-sealed` — the hierarchy is reopened; anyone can extend this subtype

This three-way choice is *mandatory* — the compiler forces you to be explicit about what happens next in the hierarchy, which is a deliberate design choice to prevent "accidental openness."

**Why this matters architecturally:** Sealed hierarchies let you model a *closed set of possibilities* — the functional programming world calls this a "sum type" or "algebraic data type." Once combined with pattern matching (see below), the compiler can prove that a `switch` over a sealed type handles every case, catching an entire category of bugs at compile time: the "someone added a new subclass and forgot to update the six places that switch on it" bug.

**Nuance senior engineers should know:** All permitted subtypes must reside in the same module (or same package, for code not using the module system). This is a physical/compilation constraint, not just a naming convention — it prevents someone in a totally separate library from claiming to be a permitted subtype.

---

### 2. Pattern Matching for `instanceof` (JEP 394)

**The problem it solves:** The classic `instanceof` + cast idiom was always redundant — you check the type, then immediately cast to that exact type you just checked:

```java
if (obj instanceof String) {
    String s = (String) obj;
    ...
}
```

This is boilerplate and, worse, a copy-paste error risk (casting to the wrong type after checking a different one).

**How it works mechanically:** The pattern variable is bound directly in the `instanceof` expression:

```java
if (obj instanceof String s) {
    // s is already a String here, no cast needed
}
```

**The part that actually gets tested in interviews — flow scoping:** The pattern variable's scope isn't just "inside the if-block." The compiler performs flow analysis to figure out everywhere the pattern is *definitely true*, including after early returns:

```java
if (!(obj instanceof String s)) {
    return;
}
// s is usable here — the only way to reach this line is if
// the instanceof matched (because if it didn't, we already returned)
System.out.println(s.length());
```

This also works with `&&` chains:
```java
if (obj instanceof String s && s.length() > 5) {
    // valid — s is bound before the length() check runs
}
```

but NOT with `||`, since the pattern isn't guaranteed to have matched in the right-hand branch.

---

### 3. Records (JEP 395)

**The problem it solves:** Plain data-carrier classes in Java required an enormous amount of ceremony — a constructor, getters, `equals()`, `hashCode()`, and `toString()`, all of which are mechanical and derivable purely from the fields. Lombok emerged specifically to paper over this gap using annotation processing, but that's a third-party tool with its own build-time magic. Records make "transparent, immutable data carrier" a first-class part of the language.

**How it works mechanically:** Declaring a record automatically generates:
- A `private final` field for each component
- A canonical (all-args) constructor
- Accessor methods named exactly like the component (`x()`, not `getX()`)
- `equals()` and `hashCode()` implemented over *all* components
- A `toString()` that prints all components

```java
public record Point(int x, int y) {}
```

is roughly equivalent to a hand-written immutable class with all of the above, but the compiler guarantees consistency (you can't accidentally leave a field out of `equals()` but not `hashCode()`, for instance).

**Compact constructors — validating without repeating yourself:**

```java
public record Point(int x, int y) {
    public Point {  // note: no parameter list — it's implicit
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("coordinates must be non-negative");
        }
        // no need to write "this.x = x; this.y = y;" — that happens automatically
        // after this block, using the (possibly reassigned) parameters
    }
}
```

**What you cannot do with a record:**
- Add extra instance fields beyond the declared components (this is the core guarantee — a record IS its components, nothing more)
- Extend another class (records implicitly extend `java.lang.Record`)
- Make it non-final (records are implicitly `final`)

**What you CAN do:**
- Implement interfaces
- Add static fields and static/instance methods
- Override the generated methods if you have a good reason

**Why senior engineers care:** Records combine beautifully with sealed interfaces to build closed algebraic data types (see Part 3 below), and their built-in structural equality makes them natural fits for map keys, cache keys, and value objects in DDD-style modeling — but that same structural equality is a gotcha if you assumed reference equality somewhere in legacy code.

---

### 4. Pattern Matching for `switch` (JEP 406, Preview in 17)

**The problem it solves:** Traditional `switch` only matched on exact constant values (`int`, `enum`, `String`) and required `instanceof` chains for anything type-based:

```java
if (obj instanceof Integer i) { ... }
else if (obj instanceof String s) { ... }
else { ... }
```

This is verbose and, critically, the compiler cannot verify you've covered every case.

**How it works mechanically:**

```java
static String describe(Object obj) {
    return switch (obj) {
        case Integer i when i > 0 -> "positive int";
        case Integer i             -> "non-positive int";
        case String s              -> "string of length " + s.length();
        case null                  -> "it's null";
        default                    -> "something else";
    };
}
```

- `case Type variable ->` binds a pattern variable, just like `instanceof` patterns.
- `when` clauses add an extra boolean guard after the type match (a "guarded pattern").
- `case null` lets you handle null explicitly — traditional switch throws `NullPointerException` immediately on a null selector, which is a classic production bug when the input can legitimately be null.

**Where this becomes powerful — exhaustiveness over sealed types:**

```java
sealed interface Shape permits Circle, Square {}
record Circle(double r) implements Shape {}
record Square(double side) implements Shape {}

static double area(Shape s) {
    return switch (s) {
        case Circle c -> Math.PI * c.r() * c.r();
        case Square sq -> sq.side() * sq.side();
        // no default needed! compiler knows Circle + Square is everything
    };
}
```

If someone adds a new permitted subtype of `Shape` later and forgets to update this switch, **the code won't compile** — this is the single biggest practical payoff of combining sealed types + records + pattern-matching switch.

**Caveat for 17 specifically:** This feature is *preview* in Java 17 — you must compile and run with `--enable-preview`, and it wasn't finalized until Java 21. Interviewers sometimes use this to test whether you actually know the JEP history versus just knowing "Java has pattern matching now."

---

### 5. Text Blocks (JEP 378)

**The problem it solves:** Embedding multi-line strings (JSON, SQL, HTML) in Java historically required painful concatenation with `\n` and `+`:

```java
String json = "{\n" +
              "  \"name\": \"value\"\n" +
              "}\n";
```

**How it works mechanically:**

```java
String json = """
    {
      "name": "value"
    }
    """;
```

**The incidental whitespace algorithm (a favorite interview trap):** The compiler determines the *minimum common leading whitespace* across all non-blank lines, including the line with the closing `"""`, and strips exactly that much from every line. This means the position of the closing delimiter directly affects the string's content — moving it one space to the left or right changes the output. Interviewers love giving you a text block with an oddly-indented closing `"""` and asking you to predict the resulting string.

Trailing whitespace on each line is always stripped (unless you escape it with `\s`, which means "preserve this space here"). A `\` at the end of a line suppresses the following newline, letting you wrap a logical line across multiple physical lines without inserting a `\n`.

---

### 6. Strong Encapsulation of JDK Internals (JEP 403)

**The problem it solves:** Since the introduction of the module system (Java 9), internal JDK APIs (`sun.*`, `com.sun.*` packages, etc.) were only *weakly* encapsulated — you could still reach them reflectively with a warning, or bypass restrictions using `--illegal-access=permit`. This was meant as a transition period. Java 17 ends that transition: `--illegal-access` is gone, and internal APIs are strongly encapsulated by default. Reflective access now requires an explicit `--add-opens module/package=ALL-UNNAMED` flag at startup.

**Why this is a huge deal in practice, not just theory:** A large amount of the Java ecosystem — older ORMs, mocking libraries, some serialization frameworks — historically reached into JDK internals via reflection to do clever things (accessing private fields, bypassing final, etc.). Upgrading a legacy application from Java 8 straight to 17 frequently breaks at runtime with `InaccessibleObjectException`, and the fix is either upgrading the offending library to a version that doesn't do this, or manually adding `--add-opens` flags for every violated package — which is exactly the kind of real "war story" interviewers ask candidates to describe.

**What's still open by default:** `sun.misc.Unsafe`'s critical low-level memory methods remain accessible (for now) because too much foundational infrastructure — Netty, various caching libraries, parts of the JDK itself — depends on it, and no full replacement existed until the Foreign Function & Memory API matured in later versions.

---

### 7. Enhanced Pseudo-Random Number Generators (JEP 356)

**The problem it solves:** For decades, `java.util.Random` was the default PRNG, using a simple linear congruential algorithm with known statistical weaknesses (relatively short period, detectable correlations between successive outputs). There was no unified interface if you wanted a better algorithm — you had ad hoc classes like `SecureRandom` and `ThreadLocalRandom` with no shared abstraction.

**How it works mechanically:** JEP 356 introduces the `RandomGenerator` interface, implemented uniformly by `Random`, `SecureRandom`, `ThreadLocalRandom`, and several new algorithm families (`Xoshiro256PlusPlus`, `L128X256MixRandom`, and others), selectable by name through `RandomGeneratorFactory`:

```java
RandomGenerator rng = RandomGeneratorFactory.of("L64X128MixRandom").create();
int val = rng.nextInt(100);
```

**Why this matters for a senior engineer:** Being able to explain *why* you'd pick a specific algorithm — better statistical properties for Monte Carlo simulation, splittable generators for parallel streams, cryptographic strength for `SecureRandom` — signals real understanding versus just knowing the API exists.

---

### 8. Foreign Function & Memory API (JEP 412, Incubator in 17)

**The problem it solves:** Calling native (C/C++) code from Java has historically meant JNI — notoriously verbose, error-prone, and requiring native glue code compiled per-platform. Additionally, managing memory outside the JVM heap (for performance-critical buffers, memory-mapped files, etc.) meant either `ByteBuffer` (limited) or `sun.misc.Unsafe` (unsupported, internal API).

**How it works conceptually (API evolved significantly after 17):**

```java
try (Arena arena = Arena.ofConfined()) {
    MemorySegment segment = arena.allocate(100);
    // read/write native memory directly, safely scoped to this Arena
}
```

The `Arena` acts as a lifecycle/scope manager for off-heap memory, ensuring it's freed deterministically rather than relying on GC or manual `free()` calls prone to double-free or use-after-free bugs.

**Why this is asked about even though it's incubator-only in 17:** It signals the JDK's long-term direction — replacing both JNI and `Unsafe` with a safe, ergonomic, supported API. A senior candidate should be able to say "this was incubating in 17 and finalized later (Java 22)" rather than treating it as production-ready in 17.

---

### 9. Deprecations: Security Manager (JEP 411) and Applet API

**Security Manager, deprecated for removal:** The Security Manager was Java's original sandboxing mechanism — letting code run with restricted permissions (no file access, no network, etc.). It was designed for a world of untrusted applets running in browsers, which essentially no longer exists. Its performance overhead and the complexity of correctly configuring policies made it a poor fit for modern deployment models, where isolation is handled at the OS/container level (Docker, Kubernetes namespaces) instead. It was fully removed in Java 24.

**Why an interviewer asks about this:** It's a good proxy for "do you understand where the Java platform is heading, and can you reason about *why* a feature gets deprecated, not just memorize that it did."

---

## PART 2: INTERVIEW QUESTION BANK

### Sealed Classes
1. What problem do sealed classes solve that `final` classes don't?
2. What are the three options a permitted subtype has, and what does each mean?
3. Why must permitted subtypes live in the same module or package as the sealed type?
4. How do sealed classes interact with exhaustive `switch` pattern matching?
5. Design a `sealed interface` hierarchy for representing HTTP response outcomes (success, client error, server error) and explain your subtype choices.

### Pattern Matching for `instanceof`
6. Explain flow scoping for pattern variables — why does `s` remain in scope after `if (!(obj instanceof String s)) return;`?
7. Why doesn't a pattern variable from `a instanceof String s || b instanceof String s` compile cleanly?
8. Rewrite a traditional `instanceof` + cast block using pattern matching, and explain what bugs the rewrite eliminates.

### Records
9. What exactly does the compiler generate for a `record`, and what does it deliberately NOT let you do?
10. What is a compact constructor, and how is it different from a canonical constructor you write out fully?
11. Why can't a record extend another class?
12. What's the risk of using a record as a `HashMap` key if one of its components is itself mutable?
13. Compare records to Lombok's `@Data`/`@Value` — what does the language guarantee that an annotation processor cannot?

### Pattern Matching for `switch`
14. Why is `case null` significant, and what did the old `switch` statement do when the selector was null?
15. What does a `when` clause add to a `case` pattern, and give an example where it's necessary?
16. Explain exactly how sealed types + records + pattern-matching switch together eliminate the need for a `default` branch, and why that's valuable.
17. Why was this feature marked preview in Java 17, and what does `--enable-preview` actually do at compile and runtime?

### Text Blocks
18. Given a text block with a specific indentation of the closing `"""`, predict the exact resulting string.
19. What's the difference between `\s` and a normal space at the end of a text-block line?
20. When would you still prefer string concatenation or `String.format` over a text block?

### Strong Encapsulation (JEP 403)
21. What broke when your team upgraded [a legacy app] from Java 8 to 17? (Behavioral/experience-based — expects a real story.)
22. What's the difference between `--illegal-access=permit` (removed) and `--add-opens` (still available)?
23. Why does `sun.misc.Unsafe` remain accessible when most other internal APIs don't?

### Random Number Generators
24. Why might `java.util.Random` be a poor choice for a Monte Carlo simulation, and what would you pick instead?
25. What does "splittable" mean in the context of PRNGs used in parallel streams?

### Foreign Function & Memory API
26. What two older mechanisms is the Foreign Function & Memory API designed to eventually replace, and why?
27. What guarantees does `Arena` provide over manual native memory management?

### General / Synthesis Questions
28. Walk me through designing a `PaymentMethod` domain model using sealed interfaces, records, and pattern-matching switch — and explain what compile-time guarantees you get versus a traditional class-hierarchy + visitor-pattern approach.
29. Which Java 17 features are officially finalized, and which are still preview or incubator — and why does that distinction matter when recommending features for production use?
30. If you were mentoring a team migrating from Java 8 to Java 17, what would you flag as the highest-risk breaking changes, and what's your rollout strategy?
