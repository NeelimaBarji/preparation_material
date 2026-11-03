# Request Lifecycle

## 1. Synchronous Request

`mermaid
sequenceDiagram
 participant C as Client
 participant S as Service
 participant CC as ChatClient
 participant A as Advisors
 participant M as ChatModel
 participant P as Provider
 participant L as LLM
 C->>S: HTTP request
 S->>CC: prompt()
 CC->>A: Process request
 A->>M: Invoke model
 M->>P: Provider API
 P->>L: Generate
 L-->>P: Response
 P-->>M: ChatResponse
 M-->>A: Result
 A-->>CC: Final response
 CC-->>S: Content
 S-->>C: HTTP response
`

## 2. Streaming Request

`mermaid
flowchart LR
 A[Client UI] --> B[ChatClient.stream()]
 B --> C[Advisor Chain]
 C --> D[ChatModel]
 D --> E[Provider]
 E --> F[LLM]
 F --> G[Partial tokens]
 G --> A
`

## 3. What to Monitor

`mermaid
flowchart TD
 A[AI Request] --> B[Total Latency]
 A --> C[Time to First Token]
 A --> D[Token Usage]
 A --> E[Retrieval Latency]
 A --> F[Tool Latency / Failures]
 A --> G[Provider / Model]
 A --> H[Retries / Errors]
 A --> I[Cost]
`

## Interview Takeaway

When troubleshooting latency, break the request into provider latency, prompt size, retrieval, tool calls, retries, network time, and time-to-first-token instead of treating the LLM as one opaque step.