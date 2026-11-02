# Spring JDBC

A practical guide to Spring JDBC for Java and Spring Boot developers. This document covers the JDBC fundamentals, Spring abstractions, CRUD examples, PostgreSQL/H2 configuration, batch processing, transactions, exception handling, performance, and interview topics.

## 1. What is Spring JDBC?

**Spring JDBC** is the Spring Framework's JDBC abstraction. It reduces repetitive JDBC code while keeping SQL under the developer's control.

With plain JDBC, application code typically has to acquire a connection, create a statement, bind parameters, execute SQL, iterate through a `ResultSet`, handle `SQLException`, and close resources.

Spring's JDBC support handles the repetitive infrastructure work. The developer still writes SQL, supplies parameters, and defines how result rows map to application objects.

### Plain JDBC vs Spring JDBC

| Concern | Plain JDBC | Spring JDBC |
|---|---|---|
| Connection acquisition/release | Usually handled manually | Managed through `DataSource` and Spring utilities |
| Statement creation/execution | Manual | Handled by template classes |
| ResultSet iteration | Manual | Template iterates; callbacks map/process rows |
| Resource cleanup | Manual or try-with-resources | Handled by template |
| Exception handling | `SQLException` | Translated to unchecked `DataAccessException` hierarchy |
| SQL | Developer writes it | Developer writes it |

Spring JDBC does **not** eliminate SQL. It removes much of the JDBC plumbing around SQL.

## 2. Spring JDBC architecture and flow

Typical request flow:

```text
Client / Controller
        |
        v
Service layer
        |
        v
Repository / DAO
        |
        v
JdbcTemplate or NamedParameterJdbcTemplate
        |
        v
DataSource (commonly backed by HikariCP)
        |
        v
JDBC Driver
        |
        v
Database (PostgreSQL, MySQL, H2, etc.)
```

### Main building blocks

| Component | Responsibility |
|---|---|
| `DataSource` | Supplies database connections; often a connection pool |
| JDBC Driver | Implements communication with a particular database |
| `JdbcTemplate` | Executes SQL and manages JDBC resource lifecycle |
| `RowMapper<T>` | Maps one ResultSet row to one Java object |
| `RowCallbackHandler` | Processes each row without returning a mapped list |
| `ResultSetExtractor<T>` | Processes the complete ResultSet and returns one result |
| `NamedParameterJdbcTemplate` | Supports named parameters such as `:employeeId` |
| `KeyHolder` | Captures generated keys from INSERT statements |
| `SQLExceptionTranslator` | Converts SQLExceptions into Spring's data-access exception hierarchy |
| `DataAccessException` | Root of Spring's unchecked data-access exceptions |

### What happens when JdbcTemplate executes a query?

1. The repository calls a JdbcTemplate method.
2. JdbcTemplate obtains a connection through the configured DataSource. If a Spring transaction is active, Spring can participate in its connection.
3. JdbcTemplate creates and executes the appropriate statement and binds supplied parameters.
4. The query result is processed by a RowMapper or another callback.
5. JdbcTemplate closes the ResultSet and statement and releases the connection appropriately.
6. The repository returns the mapped result to the service.

A configured `JdbcTemplate` is thread-safe and is normally shared as a Spring bean rather than created for every query.

## 3. Add dependencies

For a Spring Boot application, add the JDBC starter and the database driver.

### Maven: PostgreSQL

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### Maven: H2 for local development or tests

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

Use the Spring Boot dependency management/BOM so compatible dependency versions are selected. The JDBC starter provides Spring JDBC and the necessary Spring infrastructure; the database driver is a separate dependency.

## 4. Configure the DataSource

Spring Boot can auto-configure a DataSource and JdbcTemplate when the JDBC starter, a database driver, and datasource properties are present.

### PostgreSQL: application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/company
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
# Usually inferred from the JDBC URL; optional:
spring.datasource.driver-class-name=org.postgresql.Driver

# Example HikariCP settings; tune for the application and database:
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=2
spring.datasource.hikari.connection-timeout=30000
```

Store credentials in environment variables or a secret manager. Do not commit real passwords to source control.

### H2 example

```properties
spring.datasource.url=jdbc:h2:mem:companydb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
```

The H2 console is intended for development; do not expose it casually in production.

### Example table

PostgreSQL-compatible schema:

```sql
CREATE TABLE employee (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(80),
    salary NUMERIC(12, 2) NOT NULL
);
```

For H2, the identity syntax above is also supported by current H2 versions.

## 5. JdbcTemplate: the core class

`JdbcTemplate` is the most commonly used Spring JDBC API. It handles statement execution, result processing callbacks, resource cleanup, and exception translation.

### Constructor injection

Prefer constructor injection over field injection:

```java
@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
```

Spring Boot auto-configures JdbcTemplate when the JDBC infrastructure is configured. You generally do not need to create it manually.

## 6. Important JdbcTemplate methods

| Method | Use | Typical return |
|---|---|---|
| `query(..., RowMapper)` | Retrieve zero or more rows | `List<T>` |
| `queryForObject(..., RowMapper, args)` | Retrieve exactly one row/object | `T` |
| `queryForObject(..., Class<T>, args)` | Retrieve one scalar value | `T` |
| `update(...)` | INSERT, UPDATE, or DELETE | Affected row count |
| `batchUpdate(...)` | Execute a batch of updates | Update counts |
| `execute(...)` | Execute SQL or a JDBC callback | Depends on overload |

### 6.1 queryForObject(): retrieve one row

```java
public Employee findById(long id) {
    String sql = """
        SELECT id, name, department, salary
        FROM employee
        WHERE id = ?
        """;

    return jdbcTemplate.queryForObject(
        sql,
        (rs, rowNum) -> new Employee(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("department"),
            rs.getBigDecimal("salary")
        ),
        id
    );
}
```

Important: `queryForObject` expects exactly one result. It throws `EmptyResultDataAccessException` if no row is returned and an incorrect-result-size exception if multiple rows are returned. If absence is a normal outcome, use a query returning a list and handle an empty list, or handle the no-result exception deliberately.

### 6.2 query(): retrieve multiple rows

```java
public List<Employee> findAll() {
    String sql = """
        SELECT id, name, department, salary
        FROM employee
        ORDER BY id
        """;

    return jdbcTemplate.query(sql, (rs, rowNum) ->
        new Employee(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("department"),
            rs.getBigDecimal("salary")
        )
    );
}
```

An empty result produces an empty list, not a null list.

### 6.3 queryForObject(): retrieve a single value

```java
public int countEmployees() {
    return jdbcTemplate.queryForObject(
        "SELECT COUNT(*) FROM employee",
        Integer.class
    );
}
```

### 6.4 update(): INSERT, UPDATE, and DELETE

Despite its name, `update()` is used for INSERT, UPDATE, and DELETE statements. It returns the number of affected rows.

INSERT:

```java
public int insert(Employee employee) {
    String sql = """
        INSERT INTO employee(name, department, salary)
        VALUES (?, ?, ?)
        """;

    return jdbcTemplate.update(
        sql,
        employee.getName(),
        employee.getDepartment(),
        employee.getSalary()
    );
}
```

UPDATE:

```java
public int updateSalary(long id, BigDecimal salary) {
    String sql = "UPDATE employee SET salary = ? WHERE id = ?";
    return jdbcTemplate.update(sql, salary, id);
}
```

DELETE:

```java
public int deleteById(long id) {
    return jdbcTemplate.update(
        "DELETE FROM employee WHERE id = ?", id
    );
}
```

Always bind untrusted values as parameters. Do not concatenate user input into SQL.

## 7. RowMapper: map database rows to Java objects

`RowMapper<T>` is a functional interface. Its `mapRow(ResultSet rs, int rowNum)` method maps the current row to one object. JdbcTemplate handles iterating through the rows and invokes the mapper for each row.

### Employee model

```java
public class Employee {
    private long id;
    private String name;
    private String department;
    private BigDecimal salary;

    public Employee(long id, String name,
                    String department, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public BigDecimal getSalary() { return salary; }
}
```

### Create a reusable RowMapper

```java
@Component
public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        return new Employee(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("department"),
            rs.getBigDecimal("salary")
        );
    }
}
```

Use it in the repository:

```java
@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final EmployeeRowMapper rowMapper;

    public EmployeeRepository(
            JdbcTemplate jdbcTemplate,
            EmployeeRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query(
            "SELECT id, name, department, salary FROM employee",
            rowMapper
        );
    }
}
```

### RowMapper vs ResultSetExtractor vs RowCallbackHandler

| API | Scope | Return/use |
|---|---|---|
| `RowMapper<T>` | One row at a time | Maps each row to an object; template commonly returns a List |
| `ResultSetExtractor<T>` | Entire ResultSet | Builds one result, such as a nested object graph |
| `RowCallbackHandler` | One row at a time | Processes rows without requiring a returned List |

Use a RowMapper for normal row-to-object mapping. For very large result sets, consider processing rows incrementally or using a streaming approach instead of collecting everything into a List.

## 8. NamedParameterJdbcTemplate

Regular JdbcTemplate uses positional placeholders (`?`). The caller must supply values in the exact placeholder order.

```sql
SELECT * FROM employee WHERE department = ? AND salary > ?
```

With many parameters, positional binding can become difficult to read. `NamedParameterJdbcTemplate` allows descriptive names such as `:department` and `:minimumSalary`.

```java
@Repository
public class EmployeeSearchRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public EmployeeSearchRepository(
            NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Employee> search(String department,
                                 BigDecimal minimumSalary) {
        String sql = """
            SELECT id, name, department, salary
            FROM employee
            WHERE department = :department
              AND salary >= :minimumSalary
            """;

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("department", department)
            .addValue("minimumSalary", minimumSalary);

        return namedJdbcTemplate.query(sql, params, (rs, rowNum) ->
            new Employee(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("department"),
                rs.getBigDecimal("salary")
            )
        );
    }
}
```

You can also pass a Map:

```java
Map<String, Object> params = Map.of(
    "department", department,
    "minimumSalary", minimumSalary
);
```

### JdbcTemplate vs NamedParameterJdbcTemplate

| JdbcTemplate | NamedParameterJdbcTemplate |
|---|---|
| Uses `?` placeholders | Uses named placeholders |
| Values are positional | Values are associated with names |
| Good for short/simple SQL | Often clearer for queries with many parameters |
| Parameter order must match | Parameter names must match |

NamedParameterJdbcTemplate wraps/delegates to JdbcTemplate and converts named parameters into JDBC placeholders for execution.

## 9. Generated keys: retrieve an auto-generated ID

When a table generates its primary key, use a `KeyHolder` to retrieve it after an INSERT.

```java
public long insertAndReturnId(Employee employee) {
    String sql = """
        INSERT INTO employee(name, department, salary)
        VALUES (?, ?, ?)
        """;

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection.prepareStatement(
            sql, Statement.RETURN_GENERATED_KEYS
        );
        ps.setString(1, employee.getName());
        ps.setString(2, employee.getDepartment());
        ps.setBigDecimal(3, employee.getSalary());
        return ps;
    }, keyHolder);

    Number key = keyHolder.getKey();
    if (key == null) {
        throw new IllegalStateException("Database did not return a generated ID");
    }
    return key.longValue();
}
```

The exact generated-key behavior can vary by database and driver. When needed, specify generated key column names using the appropriate PreparedStatement overload.

## 10. Batch processing with batchUpdate()

Batch processing sends multiple parameter sets for the same SQL statement through JDBC batch execution. It can reduce repeated database round trips and improve bulk-write throughput.

### Example: insert employees in batches

```java
public void insertBatch(List<Employee> employees) {
    String sql = """
        INSERT INTO employee(name, department, salary)
        VALUES (?, ?, ?)
        """;

    int batchSize = 500;

    for (int start = 0; start < employees.size(); start += batchSize) {
        List<Employee> batch = employees.subList(
            start,
            Math.min(start + batchSize, employees.size())
        );

        jdbcTemplate.batchUpdate(
            sql,
            batch,
            batchSize,
            (ps, employee) -> {
                ps.setString(1, employee.getName());
                ps.setString(2, employee.getDepartment());
                ps.setBigDecimal(3, employee.getSalary());
            }
        );
    }
}
```

Key points:

- The SQL statement is reused for the batch.
- The batch size should be selected based on driver/database behavior, payload size, and memory.
- A batch update returns update counts; drivers may report special values for batch results.
- Batching does not automatically make an entire multi-batch import one atomic transaction. Use an explicit transaction if all batches must succeed or fail together.
- For very large imports, combine batching with chunked input processing and appropriate transaction boundaries.

## 11. execute() vs update() vs query()

This distinction is a common interview topic.

| Method | Purpose | Example |
|---|---|---|
| `execute()` | General SQL execution or callback-based JDBC work | DDL such as CREATE TABLE |
| `update()` | INSERT, UPDATE, DELETE | Update employee salary |
| `query()` | Retrieve rows | Find all employees |

### Plain JDBC: execute vs executeUpdate

In plain JDBC:

- `Statement.execute(sql)` can execute SQL that may produce a ResultSet or an update count. It returns a boolean indicating whether the first result is a ResultSet.
- `Statement.executeUpdate(sql)` is used for INSERT, UPDATE, DELETE, and commonly DDL; it returns an update count (DDL commonly returns zero).

In Spring JDBC, the usual APIs are `JdbcTemplate.execute(...)`, `update(...)`, and `query(...)`. Do not confuse the plain JDBC Statement methods with the Spring template methods.

## 12. Exception handling in Spring JDBC

JDBC throws checked `SQLException`. Spring translates database exceptions into the unchecked `DataAccessException` hierarchy using an SQLExceptionTranslator.

This gives application code a consistent exception model without requiring it to depend directly on vendor-specific SQL error codes.

### Common exceptions

| Exception | Typical meaning |
|---|---|
| `DuplicateKeyException` | Duplicate primary key or unique key |
| `EmptyResultDataAccessException` | A single-result query returned no row |
| `IncorrectResultSizeDataAccessException` | Number of rows did not match expectation |
| `DataIntegrityViolationException` | Constraint or integrity violation |
| `BadSqlGrammarException` | SQL syntax or grammar problem |
| `CannotGetJdbcConnectionException` | Could not obtain a database connection |
| `DataAccessResourceFailureException` | Underlying data resource failure |

Example:

```java
try {
    jdbcTemplate.update(
        "INSERT INTO employee(name, department, salary) VALUES (?, ?, ?)",
        "Alex", "Engineering", new BigDecimal("85000.00")
    );
} catch (DuplicateKeyException ex) {
    // Handle a known duplicate-key condition.
    log.warn("Employee already exists", ex);
} catch (DataAccessException ex) {
    // Log context and let a suitable application-level handler respond.
    log.error("Database operation failed", ex);
    throw ex;
}
```

Do not swallow database exceptions silently. Translate them into meaningful application errors at an appropriate layer, and avoid exposing SQL, credentials, or internal database details to API clients.

## 13. Transaction management with @Transactional

A transaction groups multiple database operations into one unit of work. The goal is atomicity: either all required operations commit, or the transaction rolls back.

### Example: transfer money between accounts

Assume the account table has an `id` and `balance` column.

```java
@Service
public class AccountService {

    private final JdbcTemplate jdbcTemplate;

    public AccountService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void transfer(long fromId, long toId, BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        int debited = jdbcTemplate.update("""
            UPDATE account
            SET balance = balance - ?
            WHERE id = ? AND balance >= ?
            """, amount, fromId, amount);

        if (debited != 1) {
            throw new IllegalStateException(
                "Source account not found or has insufficient funds"
            );
        }

        int credited = jdbcTemplate.update("""
            UPDATE account
            SET balance = balance + ?
            WHERE id = ?
            """, amount, toId);

        if (credited != 1) {
            throw new IllegalStateException("Destination account not found");
        }
    }
}
```

If the credit operation fails and an unchecked exception escapes the transactional method, Spring rolls back the debit as well.

### How @Transactional works

1. A Spring proxy/interceptor intercepts a call to the transactional method.
2. The configured transaction manager starts or joins a transaction.
3. JDBC operations using the same transaction-managed DataSource participate in that transaction.
4. If the method completes successfully, the transaction commits.
5. If a rollback-triggering exception escapes, Spring rolls back.

### Important transaction interview points

- By default, Spring rolls back for unchecked exceptions (`RuntimeException` and `Error`), not for checked exceptions.
- Use `@Transactional(rollbackFor = Exception.class)` if checked exceptions should trigger rollback.
- In the usual proxy-based mode, self-invocation (one method in a class calling another method on the same instance) does not pass through the proxy and may not activate the second method's transaction settings.
- Keep transactions short. Avoid slow network/API calls while holding database locks or connections.
- Ensure the transaction manager and JdbcTemplate use the same DataSource.
- Transaction propagation and isolation settings should be selected deliberately.

## 14. Connection pooling with HikariCP

A database connection is an expensive resource. Creating a new physical connection for every request adds latency and load to the database.

A connection pool maintains reusable connections. Spring Boot commonly uses HikariCP when it is available on the classpath.

Example configuration:

```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=2
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
```

These are example values, not universal production defaults. Tune the pool using measured workload, database connection limits, request concurrency, and transaction duration.

Monitor active/idle connections, pending threads, connection acquisition time, timeouts, and database-side connection usage. Increasing pool size without understanding database capacity can make performance worse.

## 15. Handling large result sets

A normal `jdbcTemplate.query(...)` with a RowMapper builds a List in memory. That is convenient for small and moderate result sets but can cause high heap usage for millions of rows.

Options include:

- **Pagination:** fetch bounded pages using a stable sort key.
- **RowCallbackHandler:** process one row at a time without building a List.
- **ResultSetExtractor:** process the ResultSet with custom logic.
- **Streaming:** use JdbcTemplate's stream query API where appropriate and always close the returned Stream.
- **Chunking:** process a limited number of rows per transaction or processing unit.

Example row-by-row processing:

```java
public void processEmployees() {
    jdbcTemplate.query(
        "SELECT id, name, department, salary FROM employee",
        (RowCallbackHandler) rs -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            // Process this row; do not accumulate every row in a List.
            processEmployee(id, name);
        }
    );
}
```

For streaming APIs, driver-specific fetch-size and transaction/cursor requirements may apply. Verify behavior with the actual database and JDBC driver. Do not assume that setting fetch size alone guarantees streaming for every driver.

## 16. Spring JDBC vs Spring Data JPA vs Hibernate

| Feature | Spring JDBC | Spring Data JPA | Hibernate |
|---|---|---|---|
| What it is | Spring JDBC abstraction | Repository abstraction over a persistence technology | ORM implementation and JPA provider |
| SQL control | Direct SQL | Derived queries, JPQL, native SQL | HQL/JPQL and native SQL |
| Object mapping | Usually explicit RowMapper | Entity mapping | Entity mapping |
| Relationships | Usually handled through SQL joins and mapping | Entity relationships | Entity relationships |
| Boilerplate | Less than plain JDBC | Often very low for CRUD | Depends on usage |
| Best fit | SQL-centric operations and precise query control | Domain/entity-oriented repository CRUD | ORM-based persistence and object relationships |

Choose Spring JDBC when SQL control, predictable query shape, or bulk SQL operations are important. Choose JPA/Hibernate when entity relationships and ORM behavior provide value. They can coexist in one application, but transaction boundaries and DataSource configuration must be designed consistently.

## 17. Real-world Spring Boot layered architecture

A common structure:

```text
src/main/java/com/example/employee/
    controller/
        EmployeeController.java
    service/
        EmployeeService.java
    repository/
        EmployeeRepository.java
    model/
        Employee.java
    mapper/
        EmployeeRowMapper.java
```

Responsibilities:

- **Controller:** handles HTTP requests and response status.
- **Service:** contains business rules and transaction boundaries.
- **Repository:** owns SQL and database access.
- **RowMapper:** maps result rows into Java objects.
- **DataSource/JdbcTemplate:** supplied by Spring configuration.

Keep SQL out of controllers and avoid putting business workflows in RowMapper implementations.

## 18. Production best practices

1. Use parameterized SQL; never concatenate untrusted input into SQL values.
2. Use constructor injection and a shared configured JdbcTemplate.
3. Select only the columns the application needs; avoid unnecessary `SELECT *`.
4. Add indexes based on query patterns and execution plans, not guesswork.
5. Use pagination or incremental processing for large reads.
6. Use batch updates for bulk writes and tune the batch size.
7. Keep transactions short and define rollback behavior deliberately.
8. Configure and monitor connection pools based on real workload.
9. Set appropriate query/transaction timeouts where supported.
10. Log useful operation context, but never log passwords, tokens, or sensitive row data.
11. Test SQL against the same database engine used in production when vendor-specific behavior matters.
12. Use migrations (for example, Flyway or Liquibase) to version schema changes.

## 19. Senior-level interview questions

### Q1. What problem does JdbcTemplate solve?
It removes repetitive JDBC resource management, statement execution plumbing, ResultSet iteration, and SQLException translation while allowing developers to write SQL directly.

### Q2. What is the difference between query() and queryForObject()?
`query()` returns a List and supports zero or more rows. `queryForObject()` expects exactly one result and throws an incorrect-result-size exception when the result count is not one.

### Q3. Is RowMapper a functional interface?
Yes. It has one abstract method, `mapRow(ResultSet, int)`, and maps one current row to one object. JdbcTemplate controls the iteration.

### Q4. Does JdbcTemplate prevent SQL injection?
Parameterized calls bind values through PreparedStatements and protect value positions. They do not make dynamically concatenated SQL safe. Table names, column names, and sort directions generally cannot be bound as ordinary values; validate/allowlist such dynamic SQL fragments.

### Q5. How does Spring translate SQLExceptions?
Spring uses an SQLExceptionTranslator to translate JDBC exceptions into the unchecked DataAccessException hierarchy, with translation informed by database-specific error information.

### Q6. How does @Transactional work with JdbcTemplate?
Spring's transaction interceptor works with a transaction manager, commonly DataSourceTransactionManager or JdbcTransactionManager, to bind/manage a connection for the transaction. JdbcTemplate obtains transaction-aware connections through Spring's DataSource utilities.

### Q7. What is the difference between batch processing and a transaction?
Batching groups statement executions to reduce overhead. A transaction defines atomic commit/rollback behavior. A batch may execute inside a transaction, but batching alone does not guarantee that multiple batches form one atomic unit.

### Q8. How would you process millions of rows?
Avoid loading all rows into a List. Use pagination or incremental row processing/streaming, configure driver fetch behavior appropriately, and process bounded chunks with carefully chosen transaction boundaries.

### Q9. How would you troubleshoot connection pool exhaustion?
Inspect active, idle, and pending pool metrics; connection acquisition latency/timeouts; long-running transactions; leaked connections in code outside Spring-managed templates; slow SQL; and database connection limits. Fix the root cause before simply increasing pool size.

### Q10. When would you choose Spring JDBC over JPA?
When SQL needs to be explicit and database-oriented, such as complex reporting queries, bulk updates, stored procedures, or performance-sensitive operations where ORM behavior is unnecessary.

## 20. Quick revision cheat sheet

| Concept | Remember |
|---|---|
| `DataSource` | Supplies database connections |
| `JdbcTemplate` | Core Spring JDBC execution template |
| `query()` | Fetch zero or more rows |
| `queryForObject()` | Fetch exactly one expected result |
| `update()` | INSERT, UPDATE, DELETE |
| `execute()` | General SQL/callback execution |
| `RowMapper` | Maps one ResultSet row to an object |
| `NamedParameterJdbcTemplate` | Named SQL parameters |
| `batchUpdate()` | Batch statement execution |
| `KeyHolder` | Retrieves generated keys |
| `DataAccessException` | Spring unchecked data-access exception hierarchy |
| `SQLExceptionTranslator` | Converts SQLExceptions |
| `@Transactional` | Defines transaction boundaries |
| HikariCP | Common JDBC connection pool in Spring Boot |

## Official references

- [Spring Framework: JDBC core classes and JdbcTemplate](https://docs.spring.io/spring-framework/reference/data-access/jdbc/core.html)
- [Spring Framework: Choosing an approach for JDBC access](https://springframework.org/spring-framework/reference/data-access/jdbc/choose-style.html)
- [Spring Boot documentation](https://docs.spring.io/spring-boot/reference/)

## Visual diagrams

See [Visualizations.md](./Visualizations.md) for Mermaid diagrams covering the Spring JDBC architecture, JdbcTemplate query lifecycle, row-mapping strategies, transactions, batch processing, exception translation, data-access choices, and large-result-set processing.

---

**Key takeaway:** Spring JDBC keeps SQL in the developer's hands while Spring manages the repetitive JDBC infrastructure. Understand JdbcTemplate, RowMapper, named parameters, batch updates, exception translation, connection pooling, and transaction management to use it effectively in production Spring Boot services.
