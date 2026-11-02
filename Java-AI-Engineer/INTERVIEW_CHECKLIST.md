# Senior Java AI Engineer Interview Checklist

Use this as a revision syllabus. For every topic, prepare: (1) a definition, (2) an architecture or code example, (3) trade-offs, and (4) a production failure mode.

## Java and backend engineering
- [ ] Java 17/21 language and runtime changes
- [ ] HashMap internals, equals/hashCode, immutable keys
- [ ] Streams, collections, generics and exception design
- [ ] Threads, executors, CompletableFuture, virtual threads
- [ ] Synchronization, locks, atomics and thread safety
- [ ] JVM heap, GC, memory leaks and profiling
- [ ] Spring Boot auto-configuration, dependency injection and configuration
- [ ] Transactions, isolation, JPA fetching, N+1 and JDBC
- [ ] Microservices, API gateways, Kafka, outbox and idempotency
- [ ] Timeouts, retries, circuit breakers, bulkheads and observability

## Python
- [ ] Core collections, comprehensions, generators and iterators
- [ ] OOP, typing, dataclasses and exceptions
- [ ] Async/await and I/O concurrency
- [ ] FastAPI, Pydantic and API contracts
- [ ] pytest, fixtures, mocking and packaging

## ML and LLM fundamentals
- [ ] Supervised vs unsupervised learning
- [ ] Train/validation/test split and data leakage
- [ ] Precision, recall, F1, ROC-AUC and confusion matrix
- [ ] Overfitting, regularization and baselines
- [ ] Vectors, embeddings, cosine similarity and dimensions
- [ ] Transformer and attention intuition
- [ ] Tokens, tokenization, context windows and inference
- [ ] Temperature, top-p and model selection
- [ ] Prompt engineering, structured outputs and tool calling
- [ ] Fine-tuning vs RAG vs prompt engineering

## RAG
- [ ] End-to-end ingestion and query pipelines
- [ ] Parsing, cleaning, metadata, versioning and chunking
- [ ] Embedding model choice and vector indexing
- [ ] Top-k, filtering, approximate nearest neighbors
- [ ] Hybrid retrieval, query rewriting and reranking
- [ ] Citations, grounding and abstention
- [ ] Retrieval metrics: recall@k, MRR, nDCG
- [ ] Answer metrics: faithfulness, relevance, correctness
- [ ] Multi-tenant ACLs, freshness and deletion propagation
- [ ] Prompt injection from user and retrieved content

## Agents and MCP
- [ ] Agent vs deterministic workflow
- [ ] Tool schemas, argument validation and authorization
- [ ] Planning, state, memory and checkpointing
- [ ] Supervisor, handoff and parallel agent patterns
- [ ] LangGraph nodes, edges, conditional routing and persistence
- [ ] smolagents concepts and execution boundaries
- [ ] MCP client/server, tools/resources and security boundaries
- [ ] Human-in-the-loop approval
- [ ] Agent evaluation, tool accuracy and bounded execution

## Production AI
- [ ] Model/API abstraction and fallback strategy
- [ ] Timeouts, retries, rate limits and circuit breakers
- [ ] Prompt/model versioning and regression testing
- [ ] Tracing and monitoring with Langfuse or equivalent
- [ ] Token usage, latency, cost and concurrency management
- [ ] Caching and freshness/privacy trade-offs
- [ ] Docker, Kubernetes, Azure and CI/CD
- [ ] PII minimization, secrets, audit and retention
- [ ] Responsible AI, governance and human oversight

## System design prompts
- [ ] Design a multi-tenant enterprise RAG assistant for millions of documents.
- [ ] Design an agentic DevOps assistant that diagnoses deployment failures safely.
- [ ] Design a secure natural-language-to-SQL assistant over Snowflake.
- [ ] Design a scalable LLM gateway with quotas, routing, fallback and observability.
- [ ] Design a document ingestion and re-indexing pipeline with freshness guarantees.
- [ ] Design a human-approved agent workflow for consequential operations.

## Sample interview answers — concise structures

**Q: RAG or fine-tuning?**  
A: Use RAG when answers depend on changing, private, or traceable knowledge. Use fine-tuning to adapt task behavior, style, or specialized patterns. They can be combined. Decide using freshness, provenance, quality, cost, and evaluation requirements.

**Q: How do you reduce hallucinations in RAG?**  
A: Improve parsing and retrieval first; use hybrid search/reranking where useful; constrain generation to retrieved evidence; require citations; include an abstention path; and evaluate retrieval and answer faithfulness separately. No single prompt guarantees correctness.

**Q: How do you secure agent tools?**  
A: Treat model tool calls as untrusted proposals. Validate schemas, authenticate callers, enforce authorization in the tool gateway, use least privilege, allowlist operations, apply quotas/timeouts, audit calls, and require human approval for high-impact actions.

**Q: How do you operate an LLM app in production?**  
A: Track latency, error rate, token usage/cost, model versions, retrieval quality, and user outcomes. Correlate traces across services, protect sensitive content, maintain offline evaluations, alert on user-impacting regressions, and keep rollback/fallback paths.

**Q: When should you not use an agent?**  
A: When steps are predictable, auditable, and can be expressed as a deterministic workflow. Agents add latency, cost, nondeterminism, and safety complexity; use autonomy only where it creates measurable value.
