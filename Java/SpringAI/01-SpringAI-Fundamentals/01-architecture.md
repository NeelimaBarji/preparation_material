# Spring AI Architecture

## 1. Big Picture

`mermaid
flowchart TD
 A[Spring Boot Application] --> B[Spring AI]
 B --> C[ChatClient]
 B --> D[EmbeddingModel]
 B --> E[VectorStore]
 C --> F[Advisor Chain]
 F --> G[ChatModel]
 G --> H[Provider Adapter]
 H --> I[OpenAI / Azure OpenAI / Anthropic / Google / Ollama]
 D --> J[Embedding Provider]
 E --> K[(Vector Database)]
`

## 2. Responsibility Boundaries

| Layer | Responsibility |
|---|---|
| Application | Business rules, API contracts, authorization |
| ChatClient | Application-facing AI interaction |
| Advisors | Reusable AI middleware |
| ChatModel | Model/provider abstraction |
| Provider adapter | Provider-specific translation |
| Model | Generates response |

## 3. Lead-Level Architecture Rule

Keep business logic independent from vendor SDK request/response types where practical.

`mermaid
flowchart LR
 A[OrderService] --> B[ChatClient]
 B --> C[ChatModel]
 C --> D[Provider Adapter]
 D --> E[LLM]
`

**Interview takeaway:** Spring AI reduces provider coupling, but provider-specific capabilities still need to be understood and tested.