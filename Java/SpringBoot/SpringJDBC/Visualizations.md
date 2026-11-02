# Spring JDBC — Visual Diagrams

This companion file contains Mermaid diagrams for the concepts in [Readme.md](./Readme.md). GitHub renders Mermaid diagrams directly in Markdown.

## 1. Spring JDBC architecture

```mermaid
flowchart TD
    A[Client / HTTP Request] --> B[Controller]
    B --> C[Service Layer]
    C --> D[Repository / DAO]
    D --> E[JdbcTemplate or NamedParameterJdbcTemplate]
    E --> F[DataSource]
    F --> G[Connection Pool - HikariCP]
    G --> H[JDBC Driver]
    H --> I[(Database)]
```

**Remember:** The repository owns SQL; JdbcTemplate handles JDBC plumbing; the DataSource supplies connections.

## 2. JdbcTemplate query lifecycle

```mermaid
sequenceDiagram
    participant S as Service
    participant R as Repository
    participant J as JdbcTemplate
    participant D as DataSource
    participant DB as Database
    S->>R: findById(id)
    R->>J: queryForObject(SQL, RowMapper, id)
    J->>D: Get connection
    D-->>J: Connection
    J->>DB: Prepare statement, bind id, execute
    DB-->>J: ResultSet
    loop For each returned row
        J->>J: RowMapper.mapRow(rs, rowNum)
    end
    J-->>R: Mapped Employee
    R-->>S: Employee
    Note over J,D: JdbcTemplate cleans up JDBC resources
```

## 3. RowMapper vs ResultSetExtractor vs RowCallbackHandler

```mermaid
flowchart TD
    A[SQL Query] --> B[ResultSet]
    B --> C{How should rows be processed?}
    C -->|Map every row to an object| D[RowMapper]
    D --> E[List of mapped objects]
    C -->|Build one result from the whole result set| F[ResultSetExtractor]
    F --> G[Single aggregate result]
    C -->|Process rows without collecting a List| H[RowCallbackHandler]
    H --> I[Incremental row processing]
```

## 4. Transaction flow with @Transactional

```mermaid
flowchart TD
    A[Call transactional service method] --> B[Spring transaction proxy]
    B --> C[Begin or join transaction]
    C --> D[Debit source account]
    D --> E{Debit successful?}
    E -->|No| F[Throw exception]
    E -->|Yes| G[Credit destination account]
    G --> H{Credit successful?}
    H -->|No| F
    H -->|Yes| I[Method completes]
    I --> J[Commit]
    F --> K[Rollback when exception matches rollback rules]
```

**Important:** By default, Spring rolls back for unchecked exceptions and Errors. Checked exceptions need an explicit rollback rule when they should trigger rollback.

## 5. Batch update processing

```mermaid
flowchart TD
    A[Input records] --> B[Split into bounded chunks]
    B --> C[Prepare SQL and parameter values]
    C --> D[JdbcTemplate.batchUpdate]
    D --> E[Database executes batch]
    E --> F{More chunks?}
    F -->|Yes| B
    F -->|No| G[Finish]
```

Batching reduces repeated execution overhead. Use transaction boundaries deliberately: multiple batches are not automatically one all-or-nothing unit unless enclosed in a suitable transaction.

## 6. Spring JDBC exception translation

```mermaid
flowchart TD
    A[Database / JDBC Driver] --> B[SQLException]
    B --> C[Spring SQLExceptionTranslator]
    C --> D[DataAccessException hierarchy]
    D --> E[Repository or Service handles exception]
    E --> F[Application-level error response / retry / logging]
```

Examples include `DuplicateKeyException`, `BadSqlGrammarException`, and `CannotGetJdbcConnectionException`.

## 7. Choosing a data access approach

```mermaid
flowchart TD
    A[Choose persistence approach] --> B{Need direct SQL control?}
    B -->|Yes| C[Spring JDBC]
    B -->|No| D{Need entity relationships and ORM?}
    D -->|Yes| E[Spring Data JPA / Hibernate]
    D -->|No| F[Evaluate requirements and query patterns]
```

This is a guideline, not a strict rule. Spring JDBC and JPA can coexist when DataSource and transaction management are configured consistently.

## 8. Large result-set processing

```mermaid
flowchart TD
    A[Large database result] --> B{How much data must be processed?}
    B -->|Small / moderate result| C[JdbcTemplate query + RowMapper]
    B -->|Large result| D[Choose bounded processing]
    D --> E[Pagination]
    D --> F[RowCallbackHandler]
    D --> G[Stream API with proper closing]
    E --> H[Process chunk]
    F --> H
    G --> H
    H --> I[Release resources and continue]
```

Driver fetch-size and cursor behavior vary. Validate streaming behavior with the production database and JDBC driver.

---

[Back to the complete Spring JDBC guide](./Readme.md)
