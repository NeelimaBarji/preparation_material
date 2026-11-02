# Skills and Technology Map

## Core engineering strengths to leverage
- Java 17/21, collections, concurrency, JVM performance.
- Spring Boot, Spring Security, REST APIs, microservices and event-driven systems.
- Azure, containers, CI/CD, monitoring and production operations.
- SQL, Snowflake, data ingestion and integration patterns.

## Skills to add

| Domain | Learn | Target depth |
|---|---|---|
| Python | typing, OOP, async, FastAPI, Pydantic, pytest | Build and test services |
| ML foundations | NumPy, Pandas, metrics, train/test, overfitting | Explain and run small experiments |
| LLMs | tokens, context, inference, prompting, structured output, tool calling | Integrate and operate applications |
| Java AI | Spring AI, model clients, prompt templates, advisors, vector store integrations | Build AI features in Spring Boot |
| RAG | parsing, chunking, embeddings, vector search, hybrid search, reranking, evaluation | Build grounded enterprise search |
| Agents | LangGraph, smolagents, state, tools, checkpoints, approvals | Build bounded agent workflows |
| Interoperability | MCP concepts and secure clients/servers | Integrate tools/context safely |
| Data stores | PostgreSQL/pgvector; compare managed vector search options | Model, filter and evaluate retrieval |
| Observability | Langfuse or equivalent; traces, metrics, evaluations | Diagnose quality and runtime failures |
| Cloud/operations | Docker, Kubernetes, Azure, CI/CD, secrets, monitoring | Deploy and operate safely |

## Framework strategy
- Primary Java path: Spring AI.
- Python path: FastAPI plus provider SDKs; learn LangGraph for stateful workflows.
- Agent learning: smolagents as a compact hands-on framework; compare patterns with LangGraph.
- Retrieval: start with PostgreSQL + pgvector to understand fundamentals; evaluate managed search only when requirements justify it.
- Do not attempt to master every framework. Demonstrate concepts and be able to explain framework trade-offs.

## Engineering principles interviewers should hear
- Model output is untrusted input; validate it.
- The model should not own authorization or policy decisions.
- Use least-privilege tools and scoped identities.
- Evaluate retrieval separately from generation.
- Use bounded retries, idempotency and explicit timeouts.
- Measure quality, latency, throughput, and cost together.
- Prefer deterministic workflows when the task does not need agent autonomy.
