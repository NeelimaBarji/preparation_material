# Spring AI Fundamentals Interview Questions

## Beginner -> Intermediate

### 1. What problem does Spring AI solve?
Spring AI provides Spring-friendly abstractions and integrations for common AI application concerns such as chat models, embeddings, vector stores, tools, memory, RAG, MCP, observability, evaluation, and document ETL.

### 2. What is ChatModel?
A lower-level abstraction representing interaction with a chat model/provider.

### 3. What is ChatClient?
A fluent application-facing API for communicating with chat models. It simplifies prompt construction and integrates with Advisors and tool calling.

### 4. Why use ChatClient instead of ChatModel everywhere?
ChatClient gives application developers a more expressive API and composes recurring AI behavior through Advisors and related infrastructure.

### 5. Does Spring AI hide the provider completely?
No. It provides portable abstractions while exposing provider-specific features where needed. Capability differences still matter.

## Senior / Lead

### 6. How would you design a multi-provider Spring AI service?
Discuss provider abstraction, model routing, capability matrix, fallback policy, timeouts/retries, cost controls, quality evaluation, and observability.

### 7. Where should authorization happen?
In the application/domain/tool layer, not in the prompt.

### 8. What is the role of Advisors?
They encapsulate reusable GenAI patterns and cross-cutting logic around AI requests and responses, such as memory, retrieval, and tool calling.

### 9. Memory vs RAG?
Memory is conversation history; RAG retrieves external/domain knowledge.

### 10. How would you troubleshoot high LLM latency?
Check provider latency, model selection, prompt size, retrieval, tool-call latency, retries, network, queueing, and time-to-first-token.

### 11. How do you secure an AI tool?
Use least privilege, authentication/authorization, input validation, output validation, auditing, idempotency, rate limiting, and human approval for destructive actions.

### 12. Why is streaming valuable?
It reduces perceived latency and allows progressive rendering while generation is still running.

### 13. What makes a Spring AI service production-ready?
Secrets management, authorization, model strategy, timeouts/retries, observability, token/cost monitoring, evaluation, secure RAG, safe tools, and graceful degradation.
