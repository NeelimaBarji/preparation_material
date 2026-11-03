# Spring AI Fundamentals — Visual Study Map

This companion page is intentionally visual-first, following the style of the repository's Spring Data JPA guide.

## 1. Big Picture

```mermaid
flowchart TD
    A[Spring Boot Application] --> B[Spring AI]
    B --> C[ChatClient]
    B --> D[EmbeddingModel]
    B --> E[VectorStore]
    C --> F[Advisor Chain]
    F --> G[ChatModel]
    G --> H[Provider Adapter]
    H --> I[OpenAI / Azure OpenAI / Anthropic / Google / Bedrock / Ollama]
    D --> J[Embedding Provider]
    E --> K[(Vector Database)]
```

## 2. ChatClient vs ChatModel

```mermaid
flowchart LR
    A[Controller / Service] --> B[ChatClient]
    B --> C[Advisor Chain]
    C --> D[ChatModel]
    D --> E[Provider]
    E --> F[LLM]
```

**Remember:** ChatClient is the application-facing fluent API. ChatModel is the lower-level model abstraction.

## 3. Request Lifecycle

```mermaid
sequenceDiagram
    participant U as User
    participant S as Spring Service
    participant C as ChatClient
    participant A as Advisors
    participant M as ChatModel
    participant P as Provider
    participant L as LLM
    U->>S: Request
    S->>C: prompt()
    C->>A: Process request
    A->>M: Model call
    M->>P: Provider API
    P->>L: Generate
    L-->>P: Response
    P-->>M: Response
    M-->>A: ChatResponse
    A-->>C: Final result
    C-->>S: Content
    S-->>U: Response
```

## 4. RAG in One Picture

```mermaid
flowchart LR
    A[Documents] --> B[Chunking]
    B --> C[Embeddings]
    C --> D[(VectorStore)]
    E[Question] --> F[Query Embedding]
    F --> D
    D --> G[Relevant Chunks]
    G --> H[Prompt + Context]
    H --> I[ChatModel]
    I --> J[Grounded Answer]
```

## 5. Tool Calling

```mermaid
sequenceDiagram
    participant U as User
    participant L as LLM
    participant T as Tool Layer
    participant J as Java Tool
    participant B as Backend
    U->>L: Ask question
    L->>T: Request tool
    T->>J: Validate + execute
    J->>B: Call API / DB
    B-->>J: Result
    J-->>T: Tool result
    T-->>L: Tool result
    L-->>U: Final answer
```

**Security boundary:** the model requests a tool; the application owns authorization and execution.

## 6. Memory vs RAG

```mermaid
flowchart TD
    A[AI Assistant]
    A --> B[Conversation Memory]
    B --> C[Previous conversation turns]
    A --> D[RAG]
    D --> E[External / enterprise knowledge]
```

**Memory:** what was discussed.

**RAG:** what the enterprise knows.

## 7. MCP

```mermaid
flowchart LR
    A[Spring AI Agent] --> B[MCP]
    B --> C[GitHub]
    B --> D[Jira]
    B --> E[Slack]
    B --> F[Database / Enterprise Services]
```

MCP is the interoperability layer for discovering/consuming external capabilities.

## 8. Observability

```mermaid
flowchart LR
    A[Spring AI] --> B[Micrometer / OpenTelemetry]
    B --> C[Metrics]
    B --> D[Traces]
    C --> E[Prometheus / Grafana]
    D --> F[Jaeger / Application Insights / Datadog]
```

Track latency, time-to-first-token, provider/model, token usage, retrieval latency, tool failures, errors, retries, and cost.

## 9. Production Architecture

```mermaid
flowchart TD
    A[Client] --> B[API Gateway]
    B --> C[Spring Boot Service]
    C --> D[Spring AI]
    D --> E[ChatClient]
    D --> F[RAG]
    D --> G[Tools]
    D --> H[Memory]
    D --> I[MCP]
    E --> J[LLM Provider]
    F --> K[(Vector DB)]
    G --> L[Enterprise APIs]
    H --> M[(Redis / DB)]
    I --> N[MCP Servers]
    D --> O[Security]
    D --> P[Observability]
```

## 10. Interview Comparison

| Concept | One-line answer |
|---|---|
| Spring AI | Spring ecosystem abstraction for AI applications |
| ChatClient | High-level fluent application API |
| ChatModel | Lower-level model abstraction |
| EmbeddingModel | Text to vector representation |
| VectorStore | Store/search semantic vectors |
| Advisor | Reusable AI request/response middleware |
| Tool | Controlled backend/application capability |
| Memory | Conversation history |
| RAG | Retrieve relevant knowledge before generation |
| MCP | Standardized interoperability for AI capabilities |

## 11. Lead-Level Questions

1. Why use Spring AI instead of a vendor SDK directly?
2. ChatClient vs ChatModel?
3. Does provider abstraction guarantee feature parity?
4. How do Advisors change request processing?
5. How do you prevent N+1-style retrieval/tool overhead in an AI workflow?
6. How do you secure tools?
7. How do you isolate tenant data in RAG and memory?
8. How do you trace token/cost/latency end to end?
9. How do you design provider fallback?
10. How do you evaluate whether a RAG change actually improved answer quality?

## 12. One-Minute Revision

```text
Spring Boot
    |
    v
ChatClient
    |
Advisors ---- Memory / RAG / Tools
    |
    v
ChatModel
    |
Provider
    |
LLM

RAG: Documents -> Chunks -> Embeddings -> VectorStore -> Context -> LLM

Agent: Goal -> LLM -> Tool -> Result -> LLM -> Next step -> Answer

Production: Security + Observability + Evaluation + Cost + Reliability
```

## References
- https://docs.spring.io/spring-ai/reference/
- https://docs.spring.io/spring-ai/reference/api/chatclient.html
- https://docs.spring.io/spring-ai/reference/api/chatmodel.html
- https://docs.spring.io/spring-ai/reference/api/advisors.html
- https://docs.spring.io/spring-ai/reference/api/tools.html
