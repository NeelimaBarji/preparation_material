# Six-Month Java AI Engineer Roadmap

**Audience:** Experienced Java/Spring Boot developer (~10 years).  
**Pace:** 12 hours/week; adjust the weekly schedule to available time.  
**Approach:** 30% learning, 50% building, 20% revision/interview practice.

## Month 1 — Python, math, and machine-learning foundations

### Topics
- Python syntax, functions, classes, typing, exceptions, modules, virtual environments, package management.
- Lists/dicts/sets, iterators/generators, decorators, context managers, async/await.
- NumPy arrays, vectorization, Pandas dataframes, basic plotting.
- FastAPI, Pydantic, HTTP clients, unit testing with pytest.
- Linear algebra: vectors, matrices, dot products, norms, cosine similarity.
- Probability/statistics: distributions, mean/variance, conditional probability, Bayes intuition, sampling.
- ML workflow: features/labels, train/validation/test split, regression, classification, metrics, overfitting, regularization.
- Neural-network and transformer intuition; attention at a conceptual level.

### Build
- Python FastAPI service with typed request/response models and tests.
- Small classification experiment; document dataset, baseline, metrics, and limitations.
- Embedding similarity notebook comparing cosine similarity for sample sentences.

### Exit criteria
- Write and test a small Python API without following a tutorial line-by-line.
- Explain training vs inference, overfitting, precision/recall, vectors, and cosine similarity.

## Month 2 — LLM application engineering

### Topics
- Tokens, tokenization, context windows, model parameters, inference.
- Transformer architecture and self-attention at a practical conceptual level.
- System/user messages, prompt templates, few-shot examples, structured output.
- Temperature, top-p, max output tokens, model selection and trade-offs.
- API integration, streaming, tool/function calling, JSON schema outputs.
- Conversation state, context management, prompt versioning.
- Model provider abstractions, fallbacks, retries, timeouts, rate limits.
- Java integration with Spring AI; Python integration with provider SDKs.

### Build
- Document summarizer with structured output and tests.
- Spring Boot API that calls an LLM, validates output, streams responses, and handles timeouts/rate limits.
- Chat endpoint with bounded conversation history.

### Exit criteria
- Explain how an LLM request flows from prompt to token generation.
- Build an LLM-enabled Java service with tests and safe error handling.

## Month 3 — RAG and vector databases

### Topics
- RAG architecture: ingestion and query pipelines.
- Parsing PDFs, HTML and Markdown; cleaning and normalization.
- Chunking: fixed-size, recursive, semantic, overlap, parent-child.
- Embeddings and model selection.
- PostgreSQL pgvector, Azure AI Search, Pinecone (compare capabilities rather than learning all deeply).
- Similarity metrics, ANN, metadata filtering.
- Hybrid keyword + vector retrieval and reranking.
- Grounded generation, citations, no-answer behavior and query rewriting.
- Retrieval and answer evaluation: recall@k, MRR, nDCG, faithfulness, relevance.
- Tenant boundaries, document-level ACLs, prompt injection through retrieved text.

### Build
- Enterprise knowledge assistant for Markdown/PDF technical documents.
- Add citations, source metadata, access filtering and no-answer behavior.
- Create a labeled evaluation set and benchmark retrieval.

### Exit criteria
- Explain end-to-end RAG and diagnose ingestion, retrieval and generation failures separately.
- Demonstrate retrieval quality with metrics, not only screenshots.

## Month 4 — Agentic AI and orchestration

### Topics
- Agent vs chatbot vs deterministic workflow.
- Tool/function calling, schemas, validation, authorization and allowlists.
- Planning, bounded execution loops, state, memory and checkpointing.
- LangGraph nodes, edges, conditional routes, persistence and human approval.
- Hugging Face smolagents concepts and implementation.
- Single-agent vs multi-agent patterns: supervisor, handoff, parallel specialists.
- MCP client/server concepts and secure tool integration.
- Agent evaluation, tool accuracy, task success, safety and traceability.

### Build
- Agentic DevOps RCA assistant: GitLab pipeline status + logs + evidence-based analysis + report.
- Use read-only tools first; add human approval before any remediation.
- Capture traces and test failure paths, loops, malformed tool arguments and missing evidence.

### Exit criteria
- Explain when agents are appropriate and when a deterministic workflow is safer.
- Demonstrate bounded steps, least privilege, checkpointing and approval gates.

## Month 5 — Production AI engineering

### Topics
- Java orchestration and Python AI service boundaries.
- API contracts, asynchronous jobs, queues and idempotency.
- Kafka/event-driven integration where appropriate.
- Timeouts, bounded retries, circuit breakers, bulkheads and fallbacks.
- Authentication, authorization, tenant isolation, PII handling and secrets.
- Prompt injection and tool misuse defenses.
- LLM tracing and monitoring with Langfuse or equivalent.
- Offline evaluation datasets, regression testing and online feedback.
- Token/cost budgets, caching, latency, concurrency and throughput.
- Docker, Kubernetes, CI/CD and Azure deployment patterns.

### Build
- Containerize the RAG/agent services.
- Add CI tests, configuration/secrets management, traces, metrics and alerts.
- Write a deployment/runbook document with rollback and failure handling.

### Exit criteria
- Explain how to operate the application: security, cost, latency, quality, observability and recovery.

## Month 6 — System design and interview readiness

### Topics
- Enterprise RAG and agent platform architecture.
- AI service capacity planning, reliability, consistency, caching and failover.
- Java 17/21, collections, concurrency, JVM, Spring Boot, microservices and data access revision.
- DSA: arrays, strings, maps, sliding window, two pointers, trees, graphs and complexity.
- AI security, governance and responsible AI.
- Behavioral stories: architecture decisions, mentoring, incidents, trade-offs and delivery.

### Build and deliver
- Polish two or three portfolio projects.
- Add architecture diagrams, setup instructions, tests, evaluation results and known limitations.
- Prepare concise project walkthroughs and resume bullets.
- Complete mock interviews: coding, Java/backend, AI deep dive, system design and behavioral.

### Exit criteria
- Explain and defend 2–3 projects end-to-end.
- Design an enterprise AI application and articulate trade-offs and failure modes.
- Answer senior-level Java and AI application engineering questions clearly.

## Weekly schedule (12 hours)

| Day | Focus | Time |
|---|---|---:|
| Monday | Concepts and notes | 1.5 hrs |
| Tuesday | Python or Java coding | 1.5 hrs |
| Wednesday | AI concepts and interview Q&A | 1.5 hrs |
| Thursday | Implementation | 1.5 hrs |
| Friday | Interview practice / revision | 1 hr |
| Saturday | Project build | 3 hrs |
| Sunday | Assessment, documentation and planning | 2 hrs |
| **Total** | | **12 hrs** |

## Weekly assessment

Score each from 1–5: concepts, coding, debugging, interview explanation, documentation. Target average 3/5 or better, with no category below 2. Revisit weak topics before moving on.

## Portfolio milestones

| Month | Deliverable |
|---|---|
| 1 | Python API, tests and ML experiment |
| 2 | Java + Python LLM application |
| 3 | RAG assistant with citations, ACL and benchmark |
| 4 | Agentic DevOps RCA assistant with approval and traces |
| 5 | Deployed monitored application with CI/CD and runbook |
| 6 | 2–3 polished projects, resume and interview package |

## Interview topics checklist

- [ ] Python data structures, async, FastAPI, typing and testing
- [ ] Java 17/21, concurrency, Spring Boot, JVM and microservices
- [ ] ML lifecycle, metrics, overfitting, embeddings and transformers
- [ ] Tokens, context windows, prompting, structured output and tool calling
- [ ] RAG ingestion, chunking, retrieval, hybrid search, reranking and evaluation
- [ ] Agents, state, memory, orchestration, MCP and human approval
- [ ] LLM security, prompt injection, tenant isolation and governance
- [ ] Observability, evaluation, latency, cost, deployment and reliability
- [ ] AI system design and senior-level leadership scenarios

## Learning references

See [RESOURCES.md](./RESOURCES.md) for official documentation and course links.
