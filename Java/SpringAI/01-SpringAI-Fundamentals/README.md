# Spring AI Fundamentals

## Goal
Build a strong foundation for Senior / Lead Java AI Engineer interviews and production development.

Spring AI brings Spring-style abstractions to AI application development, including chat models, embeddings, vector stores, structured output, tools, Advisors, memory, RAG, MCP, observability, evaluation, and document ETL.

## 1. What is Spring AI?

Spring AI is an application framework in the Spring ecosystem for integrating AI capabilities into Spring Boot applications.

### Mental model

```text
Spring Boot Application
        |
        v
     Spring AI
        |
  +-----+-----+----------------+
  |           |                |
ChatClient Embeddings      VectorStore
  |           |                |
ChatModel  EmbeddingModel   Vector DB
  |
Provider Adapter
  |
OpenAI / Azure / Anthropic / Ollama / Bedrock / ...
```

Spring AI provides common abstractions while still allowing provider-specific capabilities.

## 2. Why Spring AI?

Without an abstraction:

```text
Business Service
      |
Vendor SDK
      |
Vendor Request/Response Types
      |
Specific Provider
```

With Spring AI:

```text
Business Service
      |
   ChatClient
      |
   ChatModel
      |
+-----+-----------+
|                 |
OpenAI           Ollama
```

Benefits:
- Provider portability
- Spring Boot auto-configuration
- Dependency injection
- Common AI patterns
- Reusable Advisors
- Vector-store abstraction
- Tool calling
- RAG support
- Observability and evaluation

## 3. Core Building Blocks

| Component | Purpose |
|---|---|
| ChatClient | High-level fluent API for application code |
| ChatModel | Lower-level chat model abstraction |
| StreamingChatModel | Streaming chat interaction |
| EmbeddingModel | Converts text into vectors |
| VectorStore | Stores/searches embedded documents |
| Document | Content + metadata used in ingestion/retrieval |
| Advisors | Reusable AI request/response patterns |
| Tools | Application functions that a model can request |
| ChatMemory | Conversation history |
| MCP | Interoperable tools/resources/prompts |
| ETL | Document ingestion pipeline |

## 4. ChatModel vs ChatClient

### ChatModel
Think: **How do I communicate with the model?**

Use it when you need lower-level control or custom orchestration.

### ChatClient
Think: **How does my application use AI?**

Example:

```java
String answer = chatClient
        .prompt()
        .user("Explain dependency injection")
        .call()
        .content();
```

### Comparison

| ChatModel | ChatClient |
|---|---|
| Lower-level | Higher-level |
| Provider/model abstraction | Application-facing fluent API |
| More control | Better developer ergonomics |
| Useful for custom orchestration | Best for most application flows |

## 5. Request Lifecycle

```text
HTTP / Event
    |
Controller / Service
    |
ChatClient
    |
Advisor Chain
    |
ChatModel
    |
Provider Adapter
    |
LLM Provider
    |
Model
    |
ChatResponse
    |
Application Response
```

The Advisor chain is where reusable behaviors such as retrieval, memory, and tool calling can participate in the request.

## 6. Spring Boot Auto-Configuration

Conceptually:

```text
Spring AI Starter
       |
application.yml / properties
       |
Spring Boot Auto-Configuration
       |
ChatModel bean
       |
ChatClient.Builder
       |
Application
```

Benefits:
- Less boilerplate
- Externalized configuration
- Environment-specific configuration
- Dependency injection
- Easy provider configuration

### Secure configuration

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
```

Never commit real provider secrets.

## 7. Provider Abstraction

```text
                 Application
                      |
                  ChatClient
                      |
                  ChatModel
           +----------+----------+
           |          |          |
       OpenAI      Azure      Ollama
           |          |          |
         Cloud      Cloud      Local
```

### Important interview point

Portability does **not** mean feature parity.

Providers can differ in:
- Tool calling
- Streaming
- Multimodality
- Structured output
- Context window
- Latency
- Cost
- Model quality

Design around the common abstraction, but verify capabilities before switching providers.

## 8. Prompt and Message Basics

A model request can contain:

```text
System instructions
        +
User request
        +
Conversation history
        +
Retrieved context
        +
Tool definitions/results
        |
        v
       LLM
```

System prompts define behavior and policy. User prompts carry the current task.

Authorization must be enforced by the application, not trusted to a prompt.

## 9. call() vs stream()

### call()
Best when the caller needs a complete result.

```java
String answer = chatClient
        .prompt("Explain event-driven architecture")
        .call()
        .content();
```

### stream()
Best when the client should receive progressive output.

```java
Flux<String> stream = chatClient
        .prompt("Explain microservices")
        .stream()
        .content();
```

## 10. Structured Output

Enterprise Java applications usually need typed objects rather than free-form text.

```java
record IncidentSummary(
        String severity,
        String summary,
        List<String> probableCauses
) {}
```

Pipeline:

```text
LLM Output
   |
Structured Mapping
   |
Java POJO / Record
   |
Validation
   |
Business Logic
```

Treat model output as untrusted input and validate it.

## 11. Embeddings

Embeddings convert text into numerical vectors.

```text
"Spring Boot microservices"
            |
            v
      Embedding Model
            |
            v
[0.12, -0.08, 0.44, ...]
```

Embeddings are primarily used for semantic retrieval and similarity.

### Chat model vs embedding model

| Chat Model | Embedding Model |
|---|---|
| Generates responses | Generates vectors |
| Used for generation | Used for similarity/retrieval |
| Produces text/content | Produces numerical representation |

## 12. VectorStore

```text
Document
   |
Chunk
   |
EmbeddingModel
   |
Vector
   |
VectorStore
```

At query time:

```text
User Question
      |
EmbeddingModel
      |
Query Vector
      |
Similarity Search
      |
Top-K Chunks
```

Spring AI provides a portable VectorStore API across many vector database integrations.

## 13. Advisors

An Advisor is reusable AI request/response processing logic.

Think:

```text
Spring Filter / Interceptor / AOP
              |
              ~
      Spring AI Advisor
```

Examples:
- Memory
- Retrieval
- Tool calling
- Safety
- Reasoning patterns
- Custom application logic

### Advisor chain

```text
ChatClient
   |
Advisor A
   |
Advisor B
   |
ToolCallingAdvisor
   |
ChatModel
   |
Provider
```

Advisor ordering can change application behavior, so it is a lead-level design concern.

## 14. Tool Calling

Tool calling allows a model to request that the **application** invoke a function.

```text
User
 |
ChatClient
 |
LLM
 |
Tool Request
 |
ToolCallingAdvisor
 |
ToolCallingManager
 |
Java Tool
 |
Backend API / DB
 |
Tool Result
 |
LLM
 |
Final Answer
```

Critical security principle:

> The model requests a tool. The application owns the actual execution and authorization.

In the current Spring AI 2.x architecture, ChatClient's tool-calling loop is handled by ToolCallingAdvisor.

## 15. Chat Memory

Conversation memory stores interaction history.

```text
Conversation ID
   |
   +-- User message
   +-- Assistant response
   +-- User message
   +-- Assistant response
   |
Memory Repository
```

Memory is different from RAG:

- Memory = conversation history
- RAG = external/domain knowledge retrieval

They can be combined.

## 16. MCP

Model Context Protocol standardizes interoperability with external tools, resources, and prompts.

```text
Spring AI Application
        |
       MCP
   +----+-----+------+
   |          |      |
GitHub      Jira   Slack
```

Spring AI can consume MCP server capabilities and expose Spring-based capabilities through an MCP server.

## 17. RAG Fundamentals

### Ingestion

```text
PDF / DOC / HTML
       |
Document Reader
       |
Transformer
       |
Text Splitter
       |
EmbeddingModel
       |
VectorStore
```

### Query

```text
User Question
      |
Query Embedding
      |
Vector Search
      |
Relevant Context
      |
Prompt + Context
      |
Chat Model
      |
Grounded Answer
```

RAG can ground answers in external data, but it does not guarantee correctness. Retrieval quality, authorization, chunking, ranking, prompts, and model behavior still matter.

## 18. Observability

Monitor more than HTTP requests:

- Total latency
- Time to first token
- Model/provider
- Token usage
- Error rate
- Retry count
- Retrieval latency
- Tool-call count/failures
- Cost
- Correlation/trace ID

```text
Spring AI
   |
Micrometer / OpenTelemetry
   |
Metrics + Traces
   |
Prometheus / Grafana / Jaeger / App Insights / Datadog
```

## 19. Security

Treat model input and output as untrusted.

Key risks:
- Prompt injection
- Data leakage
- Excessive tool permissions
- Cross-tenant retrieval
- Secrets in prompts
- Unsafe generated actions

Secure flow:

```text
User
 |
Authentication
 |
Authorization
 |
Spring AI
 |
Tool Permission Check
 |
Tool
 |
Domain Authorization
 |
Database / Service
```

Never rely on an LLM prompt as the security boundary.

## 20. Production Readiness Checklist

### Model
- Model selected for quality, cost, latency
- Fallback defined
- Provider outage strategy

### Prompt
- Prompt versioned
- User input isolated from policy
- Injection risks reviewed

### RAG
- Retrieval authorization
- Chunking strategy
- Evaluation dataset

### Tools
- Least privilege
- Input validation
- Idempotency
- Audit logs
- Human approval for destructive actions

### Memory
- Retention policy
- Tenant isolation
- Context-window control

### Observability
- Tracing
- Token/cost metrics
- Provider/model visibility

### Evaluation
- Golden tests
- Regression checks
- Relevance/groundedness checks

## Must-Know Checklist

- [ ] What Spring AI solves
- [ ] ChatModel
- [ ] ChatClient
- [ ] Auto-configuration
- [ ] Provider abstraction
- [ ] Prompt/message model
- [ ] call() vs stream()
- [ ] Structured output
- [ ] Embeddings
- [ ] VectorStore
- [ ] Advisors
- [ ] Tool calling
- [ ] Chat memory
- [ ] RAG
- [ ] MCP
- [ ] Observability
- [ ] Security
- [ ] Production trade-offs

## Official References

- https://docs.spring.io/spring-ai/reference/
- https://docs.spring.io/spring-ai/reference/api/chatclient.html
- https://docs.spring.io/spring-ai/reference/api/chatmodel.html
- https://docs.spring.io/spring-ai/reference/api/advisors.html
- https://docs.spring.io/spring-ai/reference/api/tools.html
