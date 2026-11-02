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

## Enterprise backend and cloud-native platform skills

These skills are part of the target profile for a senior Java AI Engineer. The goal is to combine AI engineering with the ability to build and operate reliable enterprise services.

| Area | Topics to master | Practical evidence |
|---|---|---|
| Spring Boot | Spring Boot 3, auto-configuration, DI, profiles, configuration properties, validation, exception handling, Actuator, Spring Security, testing | Secure REST service with health checks, tests and externalized configuration |
| Microservices | Service boundaries, API gateway, service-to-service auth, synchronous/asynchronous communication, distributed tracing, resilience, data ownership | Architecture with independently deployable API, ingestion and AI worker services |
| Event-driven microservices | Kafka, topics, partitions, consumer groups, ordering, delivery semantics, schema evolution, outbox/inbox, idempotency, retries, DLQ, replay, Saga and eventual consistency | Event-driven document ingestion or agent-job workflow with duplicate-safe consumers |
| Docker | Dockerfiles, multi-stage builds, image layers, non-root users, health checks, environment configuration, Compose, image scanning | Containerized Java + Python services and reproducible local environment |
| Kubernetes | Pods, Deployments, Services, ConfigMaps, Secrets, namespaces, ingress, probes, requests/limits, HPA, rolling updates, rollback, RBAC | Deploy the AI application with probes, resource policies and a runbook |
| Production operations | CI/CD, observability, logs/metrics/traces, SLOs, incident response, capacity planning, secrets and configuration management | Dashboards, alerts, deployment pipeline and failure/rollback exercise |

### Recommended platform stack
- Java 17/21 + Spring Boot 3 for APIs and orchestration.
- Spring AI for Java-side model integration.
- Python + FastAPI for AI/ML components where the Python ecosystem is valuable.
- Kafka for event-driven workflows when asynchronous decoupling is justified.
- Docker + Docker Compose for local development and packaging.
- Kubernetes for deployment, scaling, health management and rollout control.
- OpenTelemetry-compatible tracing and metrics; Langfuse for LLM/agent traces where useful.
- Azure services for managed deployment, identity, secrets, monitoring and AI capabilities.

### Architecture principles
- Prefer a modular monolith until independent deployment, scaling, ownership or fault isolation justifies microservices.
- Keep service boundaries aligned with business capabilities and data ownership.
- Treat Kafka delivery as at-least-once in typical designs; make consumers idempotent.
- Use an outbox pattern when a database update and event publication must remain consistent.
- Set explicit timeouts, bounded retries with jitter, circuit breakers and bulkheads.
- Never place credentials in container images or source control; use secret management and workload identity.
- Configure readiness/liveness probes and resource requests/limits; do not rely on defaults in production.
- Scale AI workers based on queue depth, concurrency, provider quotas, latency, memory and cost as well as CPU.
