# Request Lifecycle

## Synchronous

```text
HTTP Request
    |
Controller
    |
Service
    |
ChatClient.prompt(...)
    |
Advisor Chain
 |      |       |
Memory  RAG    Tools
    |
ChatModel
    |
Provider
    |
LLM
    |
ChatResponse
    |
Application
    |
HTTP Response
```

## Streaming

```text
Client
  |
SSE / streaming transport
  |
ChatClient.stream()
  |
Advisor Chain
  |
ChatModel
  |
Provider
  |
LLM
  |
Partial output
  |
Client UI
```

## Production metrics
- Total latency
- Time to first token
- Provider latency
- Model
- Token usage
- Retry count
- Tool count
- Retrieval latency
- Error category
- Correlation ID
