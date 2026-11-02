# Spring Boot JPA Interview Q&A: The N+1 Query Problem

> **Level:** Mid-to-senior Java / Spring Boot interviews  
> **Stack:** Spring Boot, Spring Data JPA, Hibernate, SQL  
> **Example:** One `User` has many `Order` records.

## 1. What is the N+1 problem?

The **N+1 query problem** is a database performance issue in which an application executes one query to load N parent entities, then N additional queries to load a related association for each parent. For example, loading 100 users and accessing each user's orders can produce 101 SQL statements.

It often appears with lazy-loaded relationships accessed inside loops, but eager mappings and other fetch plans can also result in inefficient SQL. The issue is the number and shape of SQL statements, not simply the use of `FetchType.LAZY`.

**Why it matters:** extra database round trips, network latency, database CPU, connection-pool pressure, and slower API responses as data volume and concurrency grow.

## 2. Example domain: User and Order

```text
User (parent)                 Order (child)
------------------            ------------------
id                            id
name                          user_id (FK)
                              amount

One User  ----------------->  Many Orders
```

### User entity

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    // getters and setters
}
```

### Order entity

```java
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal amount;

    // getters and setters
}
```

`@OneToMany` defaults to LAZY and `@ManyToOne` defaults to EAGER in Jakarta Persistence. Explicit fetch declarations make intent clear, but neither default guarantees an optimal query plan.

## 3. How N+1 happens

### Repository

```java
public interface UserRepository extends JpaRepository<User, Long> {
}
```

### Problematic service

```java
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserSummary> getUsersWithOrderCounts() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserSummary(
                        user.getId(),
                        user.getName(),
                        user.getOrders().size()))
                .toList();
    }
}
```

The access to `user.getOrders()` initializes a lazy collection when it has not already been loaded. Hibernate may issue:

**Query 1 — fetch users**
```sql
SELECT u.id, u.name
FROM users u;
```

**Query 2 — orders for user 1**
```sql
SELECT o.id, o.user_id, o.amount
FROM orders o
WHERE o.user_id = 1;
```

**Query 3 — orders for user 2**
```sql
SELECT o.id, o.user_id, o.amount
FROM orders o
WHERE o.user_id = 2;
```

The pattern continues for each user.

### Query count

| Users (N) | Parent query | Child queries | Total |
|---:|---:|---:|---:|
| 1 | 1 | 1 | 2 |
| 3 | 1 | 3 | 4 |
| 10 | 1 | 10 | 11 |
| 100 | 1 | 100 | 101 |
| 1,000 | 1 | 1,000 | 1,001 |

The total is **1 + N**. Exact counts can vary with the persistence context, cache, association state, batching, and Hibernate version/configuration.

## 4. Solution 1 — JOIN FETCH (JPQL)

Use a fetch join when the use case needs the association for the returned parents.

```java
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
        SELECT DISTINCT u
        FROM User u
        LEFT JOIN FETCH u.orders
        """)
    List<User> findAllWithOrders();
}
```

Use it in a transaction and map to a DTO:

```java
@Transactional(readOnly = true)
public List<UserSummary> getUsersWithOrderCounts() {
    return userRepository.findAllWithOrders().stream()
            .map(user -> new UserSummary(
                    user.getId(), user.getName(), user.getOrders().size()))
            .toList();
}
```

Representative SQL:

```sql
SELECT u.id, u.name, o.id, o.user_id, o.amount
FROM users u
LEFT JOIN orders o ON o.user_id = u.id;
```

**Benefits:** users and orders are obtained together in one SQL statement; LEFT JOIN retains users with no orders.

**Trade-offs:** parent columns repeat for each child row; multiple collection fetch joins can multiply rows dramatically. Collection fetch joins plus pagination are risky because SQL pagination applies to joined rows, not necessarily distinct parents. Hibernate may warn or paginate in memory depending on version/configuration. Use a two-step parent-ID pattern for paged roots. JPQL `DISTINCT` expresses unique root results, but inspect actual SQL and de-duplication behavior.

## 5. Solution 2 — @EntityGraph

Spring Data JPA entity graphs specify associations to fetch for a repository method without putting the fetch join directly into JPQL.

```java
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "orders")
    @Query("SELECT u FROM User u")
    List<User> findAllWithOrders();

    @EntityGraph(attributePaths = "orders")
    List<User> findByNameContainingIgnoreCase(String name);
}
```

**Benefits:** declarative, use-case-specific, readable, and does not change the mapping's global fetch type.

**Trade-offs:** the provider chooses how to satisfy the graph. It is not a guarantee of exactly one statement or a universally optimal plan. Inspect generated SQL.

## 6. Solution 3 — Hibernate batch fetching

Batch fetching reduces repeated selects by loading collections for several owners in a single `IN` query.

```java
@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
@BatchSize(size = 10)
private List<Order> orders = new ArrayList<>();
```

Or set a default:

```properties
spring.jpa.properties.hibernate.default_batch_fetch_size=10
```

Representative child query:

```sql
SELECT o.id, o.user_id, o.amount
FROM orders o
WHERE o.user_id IN (1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
```

For 100 users and batch size 10, a simplified expectation is one parent query plus about ten child batch queries (11 total), rather than 101. Actual counts depend on access order, dialect limits, and configuration.

**When to use:** keep associations lazy while reducing round trips when several owner collections are accessed in the same session.

**Trade-offs:** multiple queries remain, and batch size needs measurement. It does not mean every association is eagerly loaded.

## 7. Solution 4 — DTO projection (often best for read APIs)

If the endpoint only needs a few fields or an aggregate, avoid loading a full entity graph. Fetch exactly the required result.

### DTO
```java
public record UserOrderCount(Long userId, String userName, Long orderCount) {
}
```

### Repository
```java
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
        SELECT new com.example.api.UserOrderCount(
            u.id, u.name, COUNT(o.id)
        )
        FROM User u
        LEFT JOIN u.orders o
        GROUP BY u.id, u.name
        """)
    List<UserOrderCount> findUserOrderCounts();
}
```

### Service
```java
@Transactional(readOnly = true)
public List<UserOrderCount> getUserOrderCounts() {
    return userRepository.findUserOrderCounts();
}
```

This returns one row per user and avoids initializing each `orders` collection. LEFT JOIN includes users with no orders, with a count of zero.

**When to use:** read-heavy endpoints, summary screens, reports, and APIs needing selected columns or aggregates.

**Trade-offs:** DTOs are not managed entities, which is appropriate for most read use cases but not for modifying a loaded entity graph.

## 8. Which solution should you choose?

| Use case | Good starting point |
|---|---|
| Need parent and one bounded child collection | JOIN FETCH |
| Want a declarative method-specific fetch plan | @EntityGraph |
| Keep lazy loading but reduce repeated selects | @BatchSize / batch fetch |
| API needs selected fields or aggregates | DTO projection |
| Large paginated parent result | Page parent IDs, then fetch required data in a second query |

There is no universal fix. Choose based on the endpoint's data needs, result size, pagination, and measured SQL behavior.

## 9. How to detect N+1 in Spring Boot

### SQL logging for local development
```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
```

For Hibernate 6 bind parameter values:
```properties
logging.level.org.hibernate.orm.jdbc.bind=TRACE
```

Bind logs can expose sensitive data. Do not enable parameter TRACE logging in production.

### Hibernate statistics
```properties
spring.jpa.properties.hibernate.generate_statistics=true
logging.level.org.hibernate.stat=DEBUG
```

Statistics help inspect prepared statement counts and entity/collection fetches. Interpret them within the test/request scope because shared traffic can make global counts hard to attribute.

### Query-count integration test
```java
@SpringBootTest
@Transactional
class UserRepositoryPerformanceTest {
    @Autowired UserRepository userRepository;
    @Autowired EntityManager entityManager;
    @Autowired EntityManagerFactory entityManagerFactory;

    @Test
    void fetchUsersAndOrdersWithoutNPlusOne() {
        // Prepare fixtures before measuring.
        entityManager.flush();
        entityManager.clear();

        SessionFactory sessionFactory =
                entityManagerFactory.unwrap(SessionFactory.class);
        Statistics statistics = sessionFactory.getStatistics();
        statistics.clear();

        List<User> users = userRepository.findAllWithOrders();
        int totalOrders = users.stream()
                .mapToInt(user -> user.getOrders().size())
                .sum();

        assertThat(users).isNotEmpty();
        assertThat(totalOrders).isGreaterThanOrEqualTo(0);
        assertThat(statistics.getPrepareStatementCount())
                .isLessThanOrEqualTo(2);
    }
}
```

Imports:
```java
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
```

Adapt the query budget to test setup, Hibernate version, generated SQL, and configuration. Clear the persistence context before measurement and assert a budget rather than relying on a fragile exact count across environments.

## 10. Common mistakes

1. **Changing every relationship to EAGER.** It can load data callers do not need and create other performance problems.
2. **Assuming LAZY fixes N+1.** Iterating over lazy relationships can trigger N+1.
3. **Adding JOIN FETCH everywhere.** Large collections and multiple joins can multiply rows and memory use.
4. **Using collection fetch joins with pagination without checking behavior.** Page root entities safely, often with two queries.
5. **Relying only on SQL logs in production.** Use controlled query metrics, tracing, and tests; protect sensitive values.
6. **Returning entities directly from controllers.** Serialization may trigger lazy loads, recursion, or lazy-initialization exceptions. Prefer DTOs at API boundaries.

## 11. Pagination-safe pattern: two queries

For a page of users, first page the parent IDs, then fetch the needed association for those IDs.

### Repository
```java
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u.id FROM User u ORDER BY u.id",
           countQuery = "SELECT COUNT(u) FROM User u")
    Page<Long> findUserIds(Pageable pageable);

    @Query("""
        SELECT DISTINCT u
        FROM User u
        LEFT JOIN FETCH u.orders
        WHERE u.id IN :ids
        """)
    List<User> findUsersWithOrdersByIdIn(@Param("ids") Collection<Long> ids);
}
```

### Service
```java
@Transactional(readOnly = true)
public List<UserSummary> getUserPage(Pageable pageable) {
    Page<Long> idPage = userRepository.findUserIds(pageable);
    if (idPage.isEmpty()) return List.of();

    Map<Long, User> usersById = userRepository
            .findUsersWithOrdersByIdIn(idPage.getContent())
            .stream()
            .collect(Collectors.toMap(User::getId, Function.identity()));

    // IN queries do not guarantee the original page order.
    return idPage.getContent().stream()
            .map(usersById::get)
            .filter(Objects::nonNull)
            .map(user -> new UserSummary(
                    user.getId(), user.getName(), user.getOrders().size()))
            .toList();
}
```

This keeps pagination on parent IDs and loads children only for the selected page. The count query may be a third statement. Adapt sorting, filtering, and count semantics to the real use case.

## 12. Interview questions and answers

### Q1. Explain the N+1 problem in Hibernate.
**Answer:** One query loads N parents and accessing a related association triggers an additional query for each parent. For 100 users, one users query plus 100 orders queries gives 101 statements. It increases round trips and latency. I detect it through SQL logs, Hibernate statistics, and query-count integration tests, then select fetch joins, entity graphs, batch fetching, or DTO projections based on the use case.

### Q2. Does FetchType.LAZY always prevent N+1?
**Answer:** No. LAZY delays loading until access. If code accesses the association for every parent, Hibernate may execute one query per parent. LAZY is a loading strategy, not a query optimization by itself.

### Q3. Does FetchType.EAGER solve N+1?
**Answer:** Not necessarily. EAGER requires the provider to make the association available, but it does not guarantee one SQL join. Hibernate may use additional selects, and eager loading may fetch data the caller does not need. Prefer explicit, use-case-specific fetch plans.

### Q4. JOIN FETCH vs @EntityGraph?
**Answer:** Both define which associations should be fetched. JOIN FETCH expresses the fetch in JPQL. @EntityGraph declares a fetch plan separately and can be convenient across repository methods. Inspect SQL and row volume rather than assuming either always creates one query.

### Q5. What is the difference between JOIN and JOIN FETCH?
**Answer:** A normal JPQL JOIN can filter or select related data, but does not by itself tell the provider to initialize the association on the returned entity. JOIN FETCH requests that association be fetched as part of the query.

### Q6. Can JOIN FETCH be used with pagination?
**Answer:** Be careful with collection fetch joins. One-to-many joins duplicate parent rows, while pagination applies to SQL rows. This can produce incorrect page boundaries or in-memory pagination. A safer pattern is to page parent IDs first and fetch associations for those IDs in a second query.

### Q7. What is @BatchSize?
**Answer:** A Hibernate-specific hint that lets Hibernate initialize multiple proxies or collections in a batch, often using an IN clause. For example, batch size 10 can fetch orders for up to 10 users per query, reducing round trips while preserving lazy associations. Counts depend on access patterns and configuration.

### Q8. When choose DTO projection over entity fetching?
**Answer:** When a read API needs selected columns, calculated values, or aggregates. DTO projection avoids materializing full managed entities and unrelated associations. A dashboard needing a user name and order count can use a grouped query instead of loading every order.

### Q9. How prove the issue is fixed?
**Answer:** Create realistic data, flush and clear the persistence context, reset Hibernate statistics, execute the service method, and assert a reasonable SQL statement budget. Inspect generated SQL and measure response time with representative data. The test must access the associations that caused the original issue.

### Q10. Can JSON serialization trigger N+1?
**Answer:** Yes. If a controller returns JPA entities, Jackson may access lazy associations during serialization, causing extra SQL or a lazy-initialization exception after the persistence context closes. Use DTOs and keep database fetching inside the service transaction.

### Q11. What is the Cartesian product risk with multiple collection fetch joins?
**Answer:** If a user has several orders and several roles, joining both collections can produce a row for every order-role combination. This can greatly increase rows and memory. Fetch collections separately, use batch fetching, or use DTO queries based on response needs.

### Q12. Does the second-level cache eliminate N+1?
**Answer:** It may reduce database round trips when relevant data is cached, but it does not fix the underlying inefficient access pattern. Cache misses, invalidation, memory, and consistency still matter. Optimize the query plan first and evaluate caching separately.

## 13. Production scenario — interview-ready answer

**Question:** An endpoint returning users and order counts takes 8 seconds in production. How do you investigate and fix it?

**Answer:**
1. Reproduce with representative data and identify SQL statements using Hibernate logs, statistics, or APM database spans.
2. Check whether the service loads all users and accesses `user.getOrders()` in a loop or during JSON serialization.
3. Confirm the pattern. For 500 users, a parent query plus one collection query per user can mean roughly 501 statements.
4. Clarify the response requirement. For only an order count, use a DTO projection with COUNT and GROUP BY. For bounded order details, evaluate JOIN FETCH or @EntityGraph. If lazy loading across multiple owners is useful, evaluate batch fetching.
5. Check indexes and query plans, especially the foreign key `orders.user_id`. An index helps child lookups but does not eliminate excessive round trips.
6. Add an integration test that clears the persistence context and verifies the query budget. Compare latency and database load with realistic data.
7. For large paginated results, page parent IDs first and fetch associated data only for that page.

**Lead-level point:** Do not apply global EAGER settings or blindly fetch every association. Align the fetch plan with the endpoint's data contract, pagination, and expected result size, then verify statement count and total rows transferred.

## 14. Quick revision notes

- **N+1 = 1 parent query + N association queries.**
- Often exposed by accessing lazy associations in loops or during serialization.
- **JOIN FETCH:** fetch the needed association with the root query.
- **@EntityGraph:** declarative, use-case-specific fetch plan.
- **@BatchSize:** group lazy loads into batches.
- **DTO projection:** fetch only fields or aggregates the API needs.
- Be careful with multiple collection joins and pagination.
- Measure SQL statements, rows, execution plans, and endpoint latency.
- Do not treat EAGER as a universal fix.

## 15. Mini hands-on assignment

Build a small Spring Boot application with `User` and `Order` entities.

1. Insert 20 users with varying numbers of orders; include at least two users with no orders.
2. Implement an endpoint returning each user's ID, name, and order count.
3. First implement it using lazy collections and observe SQL query count.
4. Implement a DTO projection and compare SQL count and response.
5. Implement a fetch-join or entity-graph version returning order details.
6. Add a paginated endpoint and implement the two-query parent-ID pattern.
7. Add integration tests that clear the persistence context and assert query budgets.
8. Record query count, rows returned, and response time for each approach.

**Expected learning:** explain why N+1 occurs, demonstrate it, choose an appropriate fix, and defend trade-offs with measurements.

---

*Study note: SQL shown here is representative. Exact SQL, query counts, and pagination behavior vary by Hibernate version, database dialect, mappings, cache state, and configuration. Verify against the version and database used by your application.*