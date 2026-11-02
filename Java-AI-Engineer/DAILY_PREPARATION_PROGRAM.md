# Java AI Engineer — Daily Preparation Program

**Profile:** Experienced Java/Spring Boot engineer (~10 years)  
**Duration:** 24 weeks (6 months)  
**Study budget:** 12 hours/week  
**Language strategy:** Java + Python  
**Focus:** Balanced Senior AI Application Engineering

This program assumes a six-day learning week plus a weekly review. Each study day is approximately 1.5–2.5 hours. Use the weekly hours flexibly; consistency and working deliverables matter more than exact weekdays.

## How to use this plan

For each study day:
1. Read/watch the listed concept from the official resources in [RESOURCES.md](./RESOURCES.md).
2. Implement the coding exercise in a small repository or notebook.
3. Write a short note: what you learned, trade-offs, failure modes, and unanswered questions.
4. Answer the interview prompt aloud in 2–4 minutes.
5. Commit the work with a meaningful Git message.

At the end of each week, complete the assessment without notes, review gaps, and update [PROGRESS_TRACKER.md](./PROGRESS_TRACKER.md).

## Weekly time budget

| Activity | Hours/week |
|---|---:|
| Concepts and guided learning | 3 |
| Hands-on implementation | 5 |
| Interview practice | 1.5 |
| Review, notes and assessment | 1.5 |
| Portfolio polish / reading | 1 |
| **Total** | **12** |

## Month 1 — Python, math and ML foundations

### Week 1 — Python for experienced Java developers
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Python syntax, types, control flow, functions; compare Java/Python semantics | Implement a CLI that reads JSON, validates fields, and prints a summary | **Q:** Python list vs tuple? **A:** Lists are mutable; tuples are immutable sequences and can be hashable when their members are hashable. Choose based on mutability and intent. |
| Tue | OOP, dataclasses, inheritance, composition, protocols | Model an order/payment domain with dataclasses and unit tests | **Q:** Composition vs inheritance? **A:** Composition delegates behavior to collaborators and usually reduces coupling; inheritance models a genuine substitutable “is-a” relationship. |
| Wed | Collections, comprehensions, iterators, generators | Process a large JSONL file line-by-line without loading it all into memory | **Q:** What is a generator? **A:** A lazy iterator that yields values on demand, reducing memory use for streaming workloads. |
| Thu | Exceptions, context managers, files, logging | Add robust file handling, structured logs, and exception boundaries | **Q:** Why use context managers? **A:** They guarantee resource cleanup, such as closing files or releasing locks, even when exceptions occur. |
| Fri | Modules, venv, pip, dependency pinning, typing | Create a package with pyproject/requirements, type hints, and a reusable module | **Q:** Why isolate Python environments? **A:** To keep dependency versions reproducible and prevent project conflicts. |
| Sat | pytest, fixtures, mocking, HTTP clients | Build and test a small API client with retryable and non-retryable errors | **Q:** Unit vs integration test? **A:** Unit tests isolate a small unit; integration tests validate collaboration across real boundaries such as DB, HTTP, or messaging. |
| Sun | Weekly review | Refactor, write README, run tests, explain Python runtime differences from JVM | **Assessment:** Build a tested JSONL-processing utility with CLI/configuration. Explain memory behavior and error handling. |

### Week 2 — NumPy, Pandas and API basics
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | NumPy arrays, shapes, dtypes, vectorization | Create arrays and compute row-wise statistics without Python loops | **Q:** Why NumPy over lists for numerical work? **A:** Typed contiguous arrays and vectorized native operations usually improve memory efficiency and performance. |
| Tue | Broadcasting, indexing, matrix operations | Implement dot product and cosine similarity for vectors | **Q:** What is broadcasting? **A:** NumPy aligns compatible dimensions to apply operations without manually copying arrays. |
| Wed | Pandas Series/DataFrame, filtering, joins, missing values | Load a sample CSV, clean nulls, join tables, and summarize groups | **Q:** How do you handle missing data? **A:** First understand why it is missing; then remove, impute, or flag it according to domain meaning and evaluation impact. |
| Thu | Data exploration, distributions, outliers, visualization | Produce a short EDA notebook with charts and written observations | **Q:** Correlation vs causation? **A:** Correlation measures association, not proof that one variable causes another. |
| Fri | HTTP, REST, JSON, FastAPI and Pydantic | Create a typed FastAPI endpoint with validation and error responses | **Q:** Why schema validation? **A:** It rejects malformed inputs at the boundary and makes contracts explicit. |
| Sat | Async basics, HTTP client, config/secrets | Call a public/sample API asynchronously; add timeout and tests | **Q:** When is async useful? **A:** For concurrent I/O-bound work; it does not automatically accelerate CPU-bound computation. |
| Sun | Weekly review | Package the API and notebook; document data assumptions | **Assessment:** Demonstrate a typed API plus data-analysis notebook and tests. |

### Week 3 — Linear algebra, probability and statistics
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Scalars, vectors, dimensions, norms | Implement vector addition, magnitude, normalization | **Q:** What does a vector represent in ML? **A:** A numeric representation of features or learned semantic properties in a defined vector space. |
| Tue | Dot product, cosine similarity, matrix multiplication | Rank sentences by cosine similarity using sample vectors | **Q:** What does cosine similarity measure? **A:** The cosine of the angle between vectors; it emphasizes direction rather than magnitude. |
| Wed | Probability, conditional probability, Bayes intuition | Simulate conditional events with Python | **Q:** What is conditional probability? **A:** The probability of an event given that another event has occurred. |
| Thu | Mean, median, variance, standard deviation, distributions | Analyze a synthetic dataset and explain skew/outliers | **Q:** Why can mean be misleading? **A:** It is sensitive to outliers and may not represent a skewed distribution well. |
| Fri | Sampling, bias, confidence intervals, hypothesis intuition | Simulate repeated samples and compare estimates | **Q:** What is sampling bias? **A:** A systematic selection effect that makes the sample unrepresentative of the population. |
| Sat | Metrics foundations: confusion matrix, precision, recall, F1 | Implement metrics from a confusion matrix | **Q:** Precision vs recall? **A:** Precision asks how many predicted positives are correct; recall asks how many actual positives were found. |
| Sun | Weekly review | Create a math-for-ML cheat sheet with worked examples | **Assessment:** Explain vectors, similarity, probability, and classification metrics with code. |

### Week 4 — ML workflow and neural-network intuition
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | ML problem framing, features/labels, baselines | Define a classification problem and create a simple baseline | **Q:** Why start with a baseline? **A:** It provides a reference point and prevents unnecessary model complexity. |
| Tue | Train/validation/test split, leakage | Split data correctly and demonstrate an example of leakage | **Q:** What is data leakage? **A:** Information unavailable at prediction time leaks into training/evaluation, producing overly optimistic results. |
| Wed | Regression and classification | Train a simple regression and classifier using scikit-learn | **Q:** Regression vs classification? **A:** Regression predicts continuous values; classification predicts categories or class probabilities. |
| Thu | Overfitting, underfitting, regularization | Compare model performance with different model complexity | **Q:** How do you reduce overfitting? **A:** Better data, regularization, simpler models, cross-validation, and appropriate early stopping. |
| Fri | Neural-network layers, activations, loss, gradient descent | Train a tiny neural network or use a guided notebook | **Q:** What is backpropagation? **A:** It computes gradients of the loss through the network using the chain rule. |
| Sat | Transformer overview, attention intuition | Draw a transformer block and annotate data flow | **Q:** Why attention? **A:** It lets each token weigh information from other tokens to build context-dependent representations. |
| Sun | Month 1 review | Publish Python foundations mini-project and learning notes | **Assessment:** Explain the ML lifecycle and demonstrate a reproducible baseline experiment. **Milestone:** Python/API foundations complete. |

## Month 2 — LLM and Generative AI application engineering

### Week 5 — LLM fundamentals and inference
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Tokens, tokenizers, context windows | Use a tokenizer to inspect token counts for varied text | **Q:** Why does token count differ from word count? **A:** Tokenizers split text into model-specific subword units, not necessarily whole words. |
| Tue | Transformer, self-attention, positional information | Draw token-to-token attention flow; inspect a small model example | **Q:** What is self-attention? **A:** A mechanism that computes contextual relationships among tokens in a sequence. |
| Wed | Pretraining, instruction tuning, alignment | Compare base and instruction-following model behavior conceptually | **Q:** Pretraining vs fine-tuning? **A:** Pretraining learns broad patterns from large corpora; fine-tuning adapts model behavior or task performance using additional data. |
| Thu | Temperature, top-p, max output tokens, deterministic behavior | Run controlled prompt experiments and record outputs | **Q:** What does temperature change? **A:** It adjusts sampling randomness; it does not add factual knowledge or guarantee correctness. |
| Fri | Model selection, latency, quality, context/cost trade-offs | Create a model decision matrix for summarization, extraction, and Q&A | **Q:** How choose a model? **A:** Evaluate quality, latency, cost, context limits, availability, privacy and task requirements. |
| Sat | Provider APIs, authentication, timeout handling | Build a Python LLM client abstraction with config and tests | **Q:** Why place a provider adapter behind an interface? **A:** It isolates vendor-specific details and enables testing, switching, and fallback strategies. |
| Sun | Weekly review | Document LLM request/response lifecycle and limitations | **Assessment:** Explain tokens, inference, sampling, context windows, and model selection. |

### Week 6 — Prompt engineering and structured outputs
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | System/user/developer messages; task framing | Write prompts for classification, extraction, and summarization | **Q:** What makes a prompt reliable? **A:** Clear task, context, constraints, examples where useful, and a verifiable output contract. |
| Tue | Few-shot examples, decomposition, delimiters | Compare zero-shot and few-shot performance on a fixed test set | **Q:** When use few-shot prompting? **A:** When examples clarify output format or edge-case behavior and improve consistency. |
| Wed | Structured outputs, JSON schema, validation | Extract typed invoice/order fields into Pydantic models | **Q:** Why validate model JSON? **A:** Model output is untrusted input; schema validation catches missing, malformed, or out-of-range fields. |
| Thu | Tool/function calling | Implement a safe calculator or read-only lookup tool | **Q:** Is tool calling the same as executing code? **A:** No. The model proposes a structured call; application code validates, authorizes, executes, and returns the result. |
| Fri | Prompt injection and untrusted content | Add instruction/data separation and adversarial test cases | **Q:** How defend against prompt injection? **A:** Treat retrieved/user content as untrusted, constrain tools and permissions, validate outputs, and enforce policy in code. |
| Sat | Evaluation datasets and prompt regression | Build 20 representative test cases and score results | **Q:** Why maintain prompt tests? **A:** Prompt or model changes can regress quality, format compliance, and safety. |
| Sun | Weekly review | Publish prompt cookbook and evaluation results | **Assessment:** Produce a validated extraction service with regression tests. |

### Week 7 — Java integration with Spring AI
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Spring AI concepts, model abstraction, configuration | Create a Spring Boot project and configure a supported model provider | **Q:** Why use a framework abstraction? **A:** It reduces provider coupling, standardizes common operations, and integrates with Spring configuration and dependency injection. |
| Tue | ChatClient, prompt templates, typed responses | Create a Java endpoint returning a typed summary | **Q:** Where should prompt construction live? **A:** In a dedicated application/service component with versioning and tests, not scattered across controllers. |
| Wed | Streaming responses and backpressure basics | Implement streaming endpoint; test disconnect and cancellation behavior | **Q:** What is backpressure? **A:** A way for downstream consumers to signal demand and prevent uncontrolled buffering. |
| Thu | Error handling, retries, timeouts, rate limits | Implement bounded retries with exponential backoff and jitter for transient errors | **Q:** Should all LLM errors be retried? **A:** No. Retry transient failures selectively; validation, authentication, and policy errors usually need correction, not repetition. |
| Fri | Conversation history and state | Build a simple conversation endpoint with bounded history | **Q:** Why not send all conversation history forever? **A:** Context and cost grow; irrelevant history can reduce quality. Summarize, retrieve, and trim deliberately. |
| Sat | Tests, mocking model calls, contract tests | Add service tests with mock model responses and error cases | **Q:** How test an LLM-backed service reliably? **A:** Separate deterministic business logic, mock provider boundaries, keep curated evaluation tests, and use limited live integration tests. |
| Sun | Weekly review | Document sequence diagram and operational behavior | **Assessment:** Demo a Java LLM service with validation, streaming, bounded retries, and tests. |

### Week 8 — Production patterns for LLM apps
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Conversation memory, summaries, context management | Implement rolling conversation summaries with token budget | **Q:** What is a context window? **A:** The maximum input/output token context a model can process for a request, subject to provider/model limits. |
| Tue | Caching and semantic cache trade-offs | Add exact-request cache; document invalidation and privacy constraints | **Q:** When is semantic caching risky? **A:** Similar prompts may have different intent, permissions, or freshness needs; validate similarity and scope carefully. |
| Wed | Idempotency, concurrency, async requests | Implement request IDs and safe duplicate handling | **Q:** Why idempotency? **A:** It prevents retries or duplicate submissions from creating repeated side effects. |
| Thu | Streaming UX, cancellation, partial failures | Handle client disconnect and upstream cancellation | **Q:** What happens when a client disconnects mid-stream? **A:** Propagate cancellation where possible and release upstream resources. |
| Fri | Usage telemetry, token and latency tracking | Record request ID, model, tokens, latency, status, and cost estimates without logging secrets | **Q:** What should AI telemetry capture? **A:** Operational metadata and quality signals while minimizing sensitive prompt/response exposure. |
| Sat | End-to-end integration and documentation | Finish a Java + Python LLM-enabled mini application | **Q:** When split Java and Python services? **A:** When Python ecosystem capabilities justify an independent service boundary; avoid splitting without operational need. |
| Sun | Month 2 review | Demo application, record known limitations, capture baseline metrics | **Assessment:** Month 2 capstone: secure, tested LLM application with Java backend and Python utility/service. |

## Month 3 — RAG, retrieval and vector databases

### Week 9 — RAG architecture and document ingestion
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | RAG architecture, ingestion vs query path | Draw and explain ingestion/query sequence | **Q:** What is RAG? **A:** Retrieval-Augmented Generation retrieves relevant external context and supplies it to a model to ground its response. |
| Tue | PDF/HTML/Markdown parsing and normalization | Build parsers for Markdown, HTML, and sample PDFs | **Q:** Why preserve source metadata? **A:** It enables citations, filtering, traceability, access control, and debugging. |
| Wed | Chunking: fixed, sentence, recursive, semantic | Compare chunk sizes and overlap on a sample corpus | **Q:** Why chunk documents? **A:** To retrieve focused context within embedding/model limits; poor chunking can split important relationships. |
| Thu | Metadata, source IDs, document versions | Store source, page, section, timestamp, version, tenant fields | **Q:** Why version documents? **A:** To update or invalidate stale chunks and preserve traceability to the source revision. |
| Fri | Embedding models and dimensions | Generate and persist embeddings for a small corpus | **Q:** What is an embedding? **A:** A dense vector representation that maps content into a learned semantic space. |
| Sat | Ingestion pipeline reliability | Add deduplication, retry, error quarantine, and reprocessing | **Q:** How make ingestion idempotent? **A:** Use stable document/chunk identifiers and upsert/version logic rather than blindly inserting duplicates. |
| Sun | Weekly review | Report ingestion coverage and parsing failures | **Assessment:** Ingest a document collection with metadata and reproducible IDs. |

### Week 10 — Vector databases and retrieval
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Vector index concepts, pgvector | Store vectors in PostgreSQL with pgvector | **Q:** Why use a vector database? **A:** It supports efficient similarity retrieval and metadata-aware search over embeddings. |
| Tue | Similarity metrics and ANN intuition | Compare cosine, dot product, and Euclidean distance | **Q:** Exact vs approximate nearest neighbors? **A:** Exact search evaluates all candidates; ANN trades some recall for improved scale/latency. |
| Wed | Top-k, score thresholds, metadata filters | Implement retrieval with filters and configurable top-k | **Q:** Why can a high similarity score still be wrong? **A:** Similarity is not proof of relevance; score calibration and domain evaluation are necessary. |
| Thu | Hybrid search: lexical + semantic | Combine full-text and vector results | **Q:** Why hybrid search? **A:** Lexical search handles exact terms/IDs; semantic search handles paraphrases and conceptual similarity. |
| Fri | Reranking and query rewriting | Add reranker stage and compare ranked results | **Q:** What does a reranker do? **A:** It re-scores a candidate set with a stronger relevance model, usually improving ordering at extra latency/cost. |
| Sat | Retrieval evaluation: recall@k, MRR, nDCG | Create queries with relevant document labels and calculate metrics | **Q:** What is recall@k? **A:** The fraction of relevant items retrieved within the top k results. |
| Sun | Weekly review | Write retrieval benchmark and analyze failure cases | **Assessment:** Compare baseline vector search with hybrid + reranking using a labeled test set. |

### Week 11 — RAG generation and grounding
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Context assembly and prompt grounding | Build answer prompt with source IDs and bounded context | **Q:** How reduce hallucination in RAG? **A:** Improve retrieval, constrain answers to evidence, require citations, handle no-answer cases, and evaluate factual support. |
| Tue | Citations and traceability | Return answer with document/page/section citations | **Q:** Are citations alone proof of correctness? **A:** No; citations must actually support each claim and be checked. |
| Wed | No-answer behavior and confidence | Add abstention when evidence is weak or absent | **Q:** Why should a RAG system abstain? **A:** To avoid fabricating answers when retrieved context does not support a reliable response. |
| Thu | Query transformation and multi-query retrieval | Generate alternate queries and merge retrieval results | **Q:** What is query expansion? **A:** Rewriting a query into alternate formulations to improve recall, with risk of drift and extra cost. |
| Fri | Parent-child and hierarchical retrieval | Retrieve small chunks while returning parent context | **Q:** Why parent-child retrieval? **A:** Small chunks improve precision; parent context restores surrounding meaning for generation. |
| Sat | RAG evaluation: faithfulness, relevance, answer quality | Build an evaluation set and score retrieval and generated answers separately | **Q:** Why evaluate retrieval and generation separately? **A:** Retrieval failure and generation failure need different diagnosis and remedies. |
| Sun | Weekly review | Demo grounded answers, citations, and abstention | **Assessment:** RAG Q&A over a fixed corpus with traceable evidence. |

### Week 12 — Enterprise RAG architecture and security
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Multi-tenant retrieval and ACL metadata | Add tenant/user scope to ingestion and retrieval | **Q:** Where enforce document access? **A:** Enforce authorization in trusted application/query layers; never rely solely on model instructions. |
| Tue | Prompt injection through retrieved documents | Create malicious document tests and isolate untrusted content | **Q:** Can retrieved text override system instructions? **A:** It should be treated as untrusted data; tool permissions and policies must be enforced outside the model. |
| Wed | PII, secrets, retention and audit | Add redaction tests and retention rules | **Q:** What is data minimization? **A:** Collect and retain only the data needed for the defined purpose. |
| Thu | Scale, partitioning, index updates, freshness | Design document reindexing and stale chunk deletion strategy | **Q:** How handle document updates? **A:** Version content, upsert new chunks, and retire old versions atomically or through a controlled cutover. |
| Fri | RAG failure analysis and architecture trade-offs | Write ADR comparing pgvector, managed search, and hosted vector DB | **Q:** How choose a vector store? **A:** Compare scale, filtering, operational burden, security, integration, cost, and retrieval quality. |
| Sat | RAG capstone polish | Add API, tests, README, architecture diagram, sample dataset | **Q:** How scale a RAG service? **A:** Separate ingestion and serving, cache carefully, tune indexes, partition, use async jobs, and measure bottlenecks. |
| Sun | Month 3 review | Record benchmark results and demo | **Assessment:** Enterprise knowledge assistant with ACL-aware retrieval, citations, and evaluation. **Milestone:** RAG portfolio project v1. |

## Month 4 — Agentic AI and orchestration

### Week 13 — Agents, tools and controlled execution
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Agent vs chatbot vs workflow | Draw deterministic workflow and agent loop; identify when not to use an agent | **Q:** Agent vs workflow? **A:** Workflows follow predefined paths; agents use model-driven decisions to select actions within constraints. |
| Tue | Tool schemas and function calling | Implement typed read-only tools with strict input validation | **Q:** Why validate tool arguments? **A:** Model output is untrusted; validation protects downstream services and enforces contracts. |
| Wed | Tool execution, authorization, allowlists | Add tool registry with per-tool permission checks | **Q:** Should an agent have broad credentials? **A:** No. Use least privilege, scoped identities, and explicit tool allowlists. |
| Thu | Observe-act loops, planning, stopping | Implement bounded plan/execute loop with max steps | **Q:** Why bound agent steps? **A:** To prevent runaway cost, loops, and uncontrolled side effects. |
| Fri | State, scratchpad, durable state | Persist task state and tool results | **Q:** What belongs in agent state? **A:** Minimal task-relevant facts, decisions, tool outputs, and checkpoint data with clear retention boundaries. |
| Sat | Human approval for side effects | Add approval gate before a simulated deployment action | **Q:** Where put human approval? **A:** In the execution control plane before consequential actions, not merely as a prompt instruction. |
| Sun | Weekly review | Document agent threat model and sequence diagram | **Assessment:** Bounded agent can call safe tools and pause for approval. |

### Week 14 — LangGraph and stateful workflows
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Graph nodes, edges, conditional routing | Build a small Python graph workflow | **Q:** Why graph orchestration? **A:** It makes states, transitions, branches, and control flow explicit and testable. |
| Tue | State schema and reducers | Define typed state and safe updates | **Q:** Why typed state? **A:** It clarifies contracts and reduces inconsistent state mutations. |
| Wed | Checkpointing and resumability | Persist state and resume a paused workflow | **Q:** Why checkpoint agents? **A:** To recover from failure, support approvals, and resume long-running tasks without repeating work. |
| Thu | Retry/error branches and timeouts | Add failure nodes and bounded retry paths | **Q:** How prevent repeated harmful actions? **A:** Idempotency keys, tool-side safeguards, checkpoints, and explicit action state. |
| Fri | Multi-step orchestration and subgraphs | Split research, analysis, and reporting into bounded stages | **Q:** When use subgraphs? **A:** To encapsulate reusable workflows with clear input/output and ownership boundaries. |
| Sat | Agent workflow tests | Test paths, state transitions, tool failures, and approval interruption | **Q:** How test non-deterministic agents? **A:** Test deterministic components and invariants; use scenario/evaluation suites for model decisions. |
| Sun | Weekly review | Write architecture notes and failure matrix | **Assessment:** Resumable workflow with checkpoint, error route, and approval gate. |

### Week 15 — Multi-agent patterns and smolagents
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Multi-agent topologies: supervisor, handoff, collaboration | Draw 3 patterns and choose one for a use case | **Q:** When are multiple agents justified? **A:** When roles require distinct tools/context or parallel specialization outweighs coordination complexity and cost. |
| Tue | Hugging Face smolagents basics | Build a small agent with one constrained tool | **Q:** What is a tool in an agent framework? **A:** A typed, callable capability exposed to the agent through a controlled interface. |
| Wed | Agent roles, delegation, shared state | Prototype researcher + reviewer roles | **Q:** What causes multi-agent failure? **A:** Ambiguous responsibility, unbounded delegation, shared-state conflicts, and compounding model errors. |
| Thu | Parallelism and aggregation | Run independent read-only tasks concurrently and merge results | **Q:** When parallelize agents? **A:** Independent tasks with bounded resource use and a clear aggregation strategy. |
| Fri | Memory: short-term, long-term, retrieval memory | Implement task memory with explicit scope and expiry | **Q:** Why not store everything in memory? **A:** It increases privacy, cost, stale-context, and relevance risks. |
| Sat | Agent evaluation and traces | Capture tool calls, state transitions, failures, and outcomes | **Q:** What measure agent success? **A:** Task completion, correctness, safety, tool reliability, latency, and cost—not just fluent responses. |
| Sun | Weekly review | Compare single-agent and multi-agent solution | **Assessment:** Demonstrate evidence that multi-agent design improves a measurable outcome. |

### Week 16 — MCP, integrations and agent capstone
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Model Context Protocol concepts and architecture | Diagram client, server, tools/resources/prompts | **Q:** What problem does MCP address? **A:** A standardized way for AI applications to discover and use external context and tools. |
| Tue | MCP server and client integration | Build a local MCP server exposing safe read-only knowledge tools | **Q:** Does MCP replace authorization? **A:** No. Authentication, authorization, validation, and tool-side safeguards remain essential. |
| Wed | Enterprise connectors and secrets | Connect to a mock Git provider/log endpoint using scoped credentials | **Q:** How protect connector credentials? **A:** Secret manager, short-lived/scoped credentials, rotation, and avoiding secret leakage in logs/prompts. |
| Thu | Agent observability with Langfuse | Trace prompts, generations, tool calls, costs, and errors | **Q:** Why trace agent runs? **A:** To reconstruct decisions and diagnose failures across multi-step execution. |
| Fri | End-to-end DevOps RCA assistant integration | Wire pipeline status, log search, analysis, and report stages | **Q:** How avoid fabricated RCA? **A:** Ground the analysis in retrieved logs/events, cite evidence, separate facts from hypotheses, and allow abstention. |
| Sat | Capstone test and demo | Run failure scenarios; approval before remediation; publish architecture | **Q:** Why keep remediation approval-gated? **A:** Production changes are consequential; human review provides control and accountability. |
| Sun | Month 4 review | Record success metrics, gaps, known limitations | **Assessment:** Demo a bounded agentic DevOps assistant. **Milestone:** Agentic AI portfolio project v1. |

## Month 5 — Production AI engineering, deployment and operations

### Week 17 — AI application architecture and service integration
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Service boundaries: Java orchestration vs Python AI service | Draw deployment and responsibility diagram | **Q:** Why separate Java and Python? **A:** Separate only when AI-library ecosystem, scaling, or ownership warrants the operational cost. |
| Tue | API contracts, schema evolution, auth between services | Define OpenAPI contract and integration tests | **Q:** How evolve AI APIs safely? **A:** Version contracts, support compatibility, validate schemas, and roll out consumers/providers carefully. |
| Wed | Async jobs, queues, long-running tasks | Implement job submission/status/result pattern | **Q:** When async job instead of synchronous request? **A:** Long-running or bursty work where client connection duration and retries make synchronous handling unsuitable. |
| Thu | Kafka/event-driven integration | Emit task events and make consumers idempotent | **Q:** How handle duplicate events? **A:** Idempotent consumers, dedupe keys, and transactional/outbox patterns when needed. |
| Fri | Resilience: timeout, circuit breaker, bulkhead, fallback | Add resilience around provider and retrieval dependencies | **Q:** What is a bulkhead? **A:** Isolation that prevents one overloaded dependency/workload from exhausting shared capacity. |
| Sat | Integration testing and local environment | Docker Compose app + DB + mock model/provider | **Q:** What should be mocked? **A:** External nondeterministic or costly boundaries; retain integration tests for the actual contracts and infrastructure behavior. |
| Sun | Weekly review | Write ADR explaining service boundaries and failure behavior | **Assessment:** End-to-end Java/Python service integration with async workflow. |

### Week 18 — Security, privacy and governance
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Identity, authentication, authorization, RBAC | Add authenticated API and role-scoped endpoint | **Q:** Authentication vs authorization? **A:** Authentication establishes identity; authorization determines allowed actions/resources. |
| Tue | Tenant isolation and data boundaries | Test cross-tenant access denial in RAG and tool paths | **Q:** Why apply ACL filters before retrieval? **A:** Unauthorized content should never enter model context, logs, or generated output. |
| Wed | Prompt injection, tool misuse, output validation | Add adversarial test suite and server-side guardrails | **Q:** Is prompt-only safety enough? **A:** No. Enforce permissions, validation, network controls, and policy in trusted code. |
| Thu | PII/secrets, retention, redaction, audit | Add redaction and safe audit events | **Q:** What should not be logged? **A:** Credentials, secrets, unnecessary sensitive content, and raw prompts/responses without a justified controlled need. |
| Fri | Governance, model/provider risk, human oversight | Create model/provider risk register and approval matrix | **Q:** What is human-in-the-loop? **A:** A control where a person reviews or approves selected decisions/actions before execution. |
| Sat | Threat modeling and security testing | Threat-model RAG ingestion, retrieval, tools, and APIs | **Q:** What is least privilege? **A:** Each component receives only the permissions required for its task. |
| Sun | Weekly review | Publish security checklist and test evidence | **Assessment:** Demonstrate tenant isolation, scoped tools, injection defenses, and auditability. |

### Week 19 — Observability, evaluation and quality
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Logs, metrics, traces, correlation IDs | Add distributed trace/correlation across Java and Python | **Q:** Logs vs metrics vs traces? **A:** Logs record events, metrics summarize measurements, and traces show a request's path across components. |
| Tue | LLM quality evaluation and golden datasets | Create versioned evaluation set with expected outcomes | **Q:** Why use a golden dataset? **A:** It supports repeatable comparison across prompt, model, and retrieval changes. |
| Wed | RAG metrics: recall, MRR, nDCG, faithfulness | Run retrieval and answer evaluation separately | **Q:** What is faithfulness? **A:** Whether the generated answer is supported by the supplied evidence/context. |
| Thu | Agent metrics: task success, tool accuracy, safety | Build scenario-based agent evaluation | **Q:** Why evaluate tool selection? **A:** Correct tool choice and arguments are critical to safe and successful task completion. |
| Fri | Drift, regression, feedback loops | Compare evaluations across two prompt/model versions | **Q:** What is evaluation drift? **A:** Quality changes as data, usage, model behavior, or task distribution changes. |
| Sat | Langfuse dashboards and alerting | Add dashboards and alerts for latency, errors, and token usage | **Q:** What is an actionable AI alert? **A:** A threshold tied to user impact or operational risk, with enough context to investigate. |
| Sun | Weekly review | Publish evaluation report with limitations | **Assessment:** Show baseline metrics, regression checks, and trace-driven RCA. |

### Week 20 — Performance, cost and cloud deployment
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Token budgets, prompt/context compression | Measure prompt size; reduce redundant context without losing evidence | **Q:** How reduce token costs? **A:** Limit context, retrieve selectively, cache safely, select fit-for-purpose models, and track usage. |
| Tue | Latency budgets, streaming, concurrency | Benchmark p50/p95 latency and concurrency limits | **Q:** Why p95? **A:** It reveals slow-tail user experiences hidden by averages. |
| Wed | Docker, container security, configuration | Containerize Java and Python services; externalize secrets/config | **Q:** Why immutable containers? **A:** They improve repeatability and reduce environment drift between deployment stages. |
| Thu | Azure deployment options and managed AI services | Create deployment diagram for selected Azure target | **Q:** What drives cloud service choice? **A:** Security, networking, compliance, model availability, latency, operations, and cost. |
| Fri | Kubernetes basics, probes, autoscaling, resource requests | Write deployment/service/probe manifests or equivalent local setup | **Q:** Why readiness vs liveness probes? **A:** Readiness controls traffic eligibility; liveness detects a process that needs restart. |
| Sat | CI/CD, secrets, rollout and rollback | Build pipeline for tests, image build, scanning, deployment | **Q:** How deploy prompts safely? **A:** Version them, test against evaluation data, canary rollout, monitor, and roll back on regression. |
| Sun | Month 5 review | End-to-end deploy and operations runbook | **Assessment:** Deploy a monitored AI application with reproducible release and rollback. |

## Month 6 — System design, interview readiness and portfolio

### Week 21 — AI system design fundamentals
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Requirements, SLOs, scale and capacity | Define functional/nonfunctional requirements for enterprise assistant | **Q:** What clarify before architecture? **A:** Users, data sensitivity, workload, quality target, latency, scale, availability, and cost constraints. |
| Tue | Design scalable document ingestion | Draw queue-based ingestion with retries and dead-letter handling | **Q:** Why separate ingestion from query serving? **A:** Ingestion is batch/async and resource-heavy; query serving needs predictable low latency. |
| Wed | Design enterprise RAG service | Design multi-tenant RAG with ACL, hybrid retrieval and evaluation | **Q:** How scale retrieval? **A:** Index tuning, partitioning, caching, filtering, replication, and workload-specific capacity planning. |
| Thu | Design enterprise agent platform | Design tool registry, policy enforcement, state, approval, tracing | **Q:** Where enforce action policies? **A:** In a trusted tool gateway/execution layer, independent of model reasoning. |
| Fri | Reliability, consistency, failover, degradation | Create failure-mode matrix and fallback behavior | **Q:** What is graceful degradation? **A:** Preserving core useful behavior when optional dependencies or capabilities fail. |
| Sat | Architecture communication | Present a 15-minute whiteboard design and trade-offs | **Q:** How defend an architecture decision? **A:** Tie it to requirements, quantify trade-offs, state assumptions, and explain alternatives. |
| Sun | Weekly review | Redraw system without notes and compare with prior design | **Assessment:** 45-minute AI system design mock. |

### Week 22 — Java, DSA and backend interview revision
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Collections, HashMap internals, equals/hashCode | Implement frequency/grouping problems and analyze complexity | **Q:** Why immutable HashMap keys? **A:** Stable equality/hash behavior prevents lookup failures after insertion. |
| Tue | Concurrency, locks, atomics, CompletableFuture | Implement bounded concurrent task execution | **Q:** How avoid thread-pool exhaustion? **A:** Bound queues and concurrency, size pools by workload, apply timeouts and backpressure. |
| Wed | JVM, GC, heap, profiling | Analyze a heap/GC scenario and propose evidence-driven tuning | **Q:** How diagnose memory pressure? **A:** Inspect heap, allocation, GC logs, thread dumps, and traffic patterns before tuning. |
| Thu | Spring Boot, transactions, JPA, connection pools | Review transaction boundaries, N+1 and pool sizing | **Q:** Why can a connection pool cause bottlenecks? **A:** Too many concurrent DB operations can overwhelm the database; pool size must match workload and DB capacity. |
| Fri | Microservices, Kafka, idempotency, resilience | Solve a design problem involving event duplication and retries | **Q:** What is the outbox pattern? **A:** Persist business change and event record atomically, then publish reliably from the outbox. |
| Sat | DSA patterns: arrays, maps, two pointers, sliding window, trees | Solve 2 timed medium problems and explain complexity | **Q:** How approach an unfamiliar coding question? **A:** Clarify constraints, derive baseline, optimize, test edge cases, and state complexity. |
| Sun | Weekly review | One Java mock + one behavioral story | **Assessment:** Timed coding + backend fundamentals mock. |

### Week 23 — AI interview deep dive and behavioral leadership
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | RAG trade-offs and failure diagnosis | Diagnose a low-recall/high-hallucination scenario | **Q:** How improve a poor RAG answer? **A:** Determine whether failure is parsing, chunking, retrieval, context, or generation before changing components. |
| Tue | LLM model selection, fine-tuning vs RAG | Write decision matrix for a domain-specific use case | **Q:** When fine-tune instead of RAG? **A:** For behavior/style/task adaptation; for changing factual knowledge and traceable private content, retrieval is often more appropriate. |
| Wed | Agents, MCP, safety and orchestration | Design approval-gated agent with audit and scoped tools | **Q:** How make agents reliable? **A:** Bound steps, constrain tools, validate outputs, checkpoint state, evaluate scenarios, and gate risky actions. |
| Thu | LLM evaluation and observability | Explain a dashboard and diagnose a quality/latency regression | **Q:** How measure AI quality in production? **A:** Combine offline golden-set evaluation, online feedback, operational telemetry, and periodic human review. |
| Fri | Leadership: architecture, mentoring, influence | Prepare examples using situation/action/result and measurable outcome | **Q:** How handle architecture disagreement? **A:** Align on requirements, compare evidence and trade-offs, record decision, and revisit with data. |
| Sat | Incident response and production RCA | Prepare an incident story involving latency, memory, or dependency outage | **Q:** What makes a good RCA? **A:** Timeline, impact, contributing factors, evidence, corrective actions, owners, and prevention measures. |
| Sun | Weekly review | Record two mock interviews and identify gaps | **Assessment:** 60-minute combined AI/backend/behavioral mock. |

### Week 24 — Portfolio, resume and job readiness
| Day | Topics | Hands-on coding / assignment | Interview practice |
|---|---|---|---|
| Mon | Project 1 polish: architecture, demo, tests | Finalize Agentic DevOps RCA Assistant README and demo | **Q:** What problem does your project solve and how do you measure success? **A:** Explain user pain, baseline, workflow, measured outcomes, limitations, and safety controls. |
| Tue | Project 2 polish: RAG evaluation and citations | Finalize Enterprise Knowledge Assistant and benchmark | **Q:** How prove retrieval quality? **A:** Present labeled queries, retrieval metrics, answer evaluation, and failure examples. |
| Wed | Project 3 polish or scope consolidation | Finish Snowflake AI Analyst prototype or deepen existing projects | **Q:** How secure natural-language-to-SQL? **A:** Read-only scoped credentials, SQL parsing/allowlists, query limits, approval, and audit controls. |
| Thu | GitHub portfolio and technical writing | Add diagrams, setup steps, environment examples, tests, screenshots | **Q:** What makes a portfolio credible? **A:** Reproducible setup, clear architecture, tests, metrics, trade-offs, and honest scope. |
| Fri | Resume and profile positioning | Rewrite summary and project bullets around Java + AI application engineering | **Q:** How do you position 10 years of Java experience? **A:** As enterprise architecture, production reliability, integrations, and delivery strengths extended into AI systems. |
| Sat | Full mock interview | 45-min system design, 45-min coding/backend, 30-min AI deep dive | **Q:** Design an enterprise AI assistant end-to-end. **A:** Clarify needs, choose retrieval/model strategy, define components, security, evaluation, reliability, and operations. |
| Sun | Final retrospective and next 90-day plan | Review tracker, choose gaps, publish portfolio index | **Assessment:** Explain two projects end-to-end and defend architecture choices. **Milestone:** Interview-ready portfolio and study plan. |

## Weekly assessment rubric

Score each dimension from 1 to 5 at the end of every week.

| Dimension | 1 | 3 | 5 |
|---|---|---|---|
| Concepts | Cannot explain | Explains with prompts | Explains clearly with trade-offs |
| Coding | Not working | Works on happy path | Tested, robust, documented |
| Debugging | Guesses | Finds common causes | Uses evidence and systematic diagnosis |
| Interview answer | Fragmented | Mostly correct | Structured, concise, senior-level |
| Portfolio | No artifact | Partial artifact | Reproducible, documented deliverable |

**Weekly pass target:** Average 3/5 or higher, with no score below 2. Revisit weak topics before advancing. Do not optimize for checking boxes; optimize for being able to explain and demonstrate the skill.

## Expected portfolio milestones

| When | Deliverable | Evidence to publish |
|---|---|---|
| End of Month 1 | Python + ML foundations | API, tests, notebook, short write-up |
| End of Month 2 | Java/Python LLM application | Source, API contract, tests, prompt evaluation |
| End of Month 3 | Enterprise RAG assistant | Ingestion pipeline, ACL, citations, benchmark |
| End of Month 4 | Agentic DevOps RCA assistant | Agent graph, safe tools, approval, trace screenshots |
| End of Month 5 | Production-style deployment | Docker, CI/CD, deployment diagram, monitoring, runbook |
| End of Month 6 | Portfolio and interview package | 2–3 polished repos, resume bullets, demo scripts, mock feedback |

## Final readiness checklist

- [ ] Can implement a typed Python service and test it.
- [ ] Can explain tokens, embeddings, attention, and model inference.
- [ ] Can integrate an LLM from Spring Boot and Python.
- [ ] Can build a RAG pipeline and measure retrieval quality.
- [ ] Can explain hybrid search, reranking, chunking, and ACL filtering.
- [ ] Can implement a bounded tool-using agent with approval gates.
- [ ] Can explain MCP and agent orchestration trade-offs.
- [ ] Can evaluate LLM/agent quality and trace failures.
- [ ] Can secure and deploy a multi-service AI application.
- [ ] Can design an enterprise AI system at senior level.
- [ ] Can present 2–3 projects with measurable evidence and honest limitations.

**Reminder:** Six months is a structured target, not a guarantee of job readiness. Use assessment scores and project quality to decide when to apply; begin networking and reviewing role requirements before Month 6.

## Cross-cutting backend platform skills — Spring Boot, microservices, event-driven systems, Docker and Kubernetes

These are core skills for a senior Java AI Engineer, not optional deployment extras. Revisit them throughout the 24 weeks and apply them to the RAG and Agentic AI projects. The AI services should run as production-style distributed systems.

### Backend engineering track (woven through the 24 weeks)

| When | Topics to prepare | Hands-on assignment | Senior interview prompt |
|---|---|---|---|
| Weeks 1–2 | Spring Boot 3, dependency injection, auto-configuration, profiles, configuration properties, validation, exception handling, Actuator | Create a Spring Boot service with layered architecture, typed configuration, validation, global error handling and health endpoints | **Q:** How does Spring Boot auto-configuration work? **A:** It uses conditional configuration and classpath/bean/environment conditions to provide defaults that can be overridden by application configuration or user beans. |
| Weeks 3–4 | REST API design, versioning, pagination, idempotency, Spring Security, OAuth2/JWT concepts | Add secured REST endpoints, request validation, API contract tests and consistent error responses | **Q:** How do you make a REST operation idempotent? **A:** Define an idempotency key or stable resource identity, persist request outcome, and ensure retries do not duplicate side effects. |
| Weeks 7–8 | Spring AI inside Spring Boot, ChatClient/service boundaries, resilience, configuration, observability | Keep model integration behind an application service; add timeout, bounded retry, circuit breaker and metrics | **Q:** Where should LLM calls live in a Spring application? **A:** Behind an application/service boundary with provider abstraction, validation, policy, observability and testable business logic. |
| Weeks 9–12 | Microservices boundaries, API gateway, service discovery concepts, synchronous vs asynchronous communication, distributed tracing | Split ingestion and query serving only when justified; define API contracts and correlation IDs | **Q:** How do you choose a microservice boundary? **A:** Align it to cohesive business capability, data ownership, change cadence and independent scaling—not technical layers alone. |
| Weeks 13–16 | Event-driven microservices, Kafka fundamentals, topics/partitions, consumer groups, ordering, delivery semantics, schema evolution, outbox, DLQ | Emit document-ingestion and agent-task events; build idempotent consumers, retry topics/DLQ and an outbox-backed publisher | **Q:** How do you prevent duplicate event processing? **A:** Assume at-least-once delivery, use stable event IDs and idempotent consumers, and coordinate state changes with an outbox/inbox or transactional design. |
| Weeks 17–18 | Distributed transactions, Saga, eventual consistency, retries, circuit breakers, bulkheads, timeouts, rate limiting | Model an async AI job lifecycle with accepted/running/succeeded/failed states and compensating actions where needed | **Q:** When use Saga instead of a distributed transaction? **A:** When a business process spans independently owned services and can tolerate eventual consistency with explicit compensating actions. |
| Weeks 19–20 | Docker, multi-stage builds, image layers, non-root containers, health checks, Compose, Kubernetes Deployments/Services/ConfigMaps/Secrets, probes, requests/limits, autoscaling | Containerize Java and Python services; run dependencies locally with Compose; deploy to Kubernetes with readiness/liveness probes and resource limits | **Q:** Readiness vs liveness? **A:** Readiness determines whether a pod should receive traffic; liveness detects a stuck process that should be restarted. |
| Weeks 20–21 | Kubernetes rollout/rollback, HPA, service networking, ingress, persistent storage, RBAC, namespaces, observability | Add rolling deployment, resource requests/limits, secrets references, service discovery and a rollback runbook | **Q:** How do you scale an AI microservice? **A:** Scale by workload and bottleneck; consider CPU/memory, concurrent model requests, provider quotas, queue depth, latency and cost—not CPU alone. |
| Weeks 22–24 | Architecture trade-offs, production incident response, CI/CD, contract testing, event replay and operational readiness | Present an end-to-end architecture with synchronous APIs, async events, Docker images, Kubernetes deployment and tracing | **Q:** When should you not use microservices or Kafka? **A:** Avoid distributed complexity when a modular monolith or synchronous call meets scale, ownership, latency and reliability requirements. |

### Event-driven AI architecture practice

Apply this reference flow to the RAG and DevOps projects:

1. A Spring Boot API authenticates the request and stores a job/document record.
2. The service writes an event to an outbox in the same database transaction.
3. An outbox publisher sends the event to Kafka (or a local broker for development).
4. An ingestion/AI worker consumes the event, validates it, and processes it idempotently.
5. Long-running work updates job state and emits completion/failure events.
6. A notification service reacts to completion events; clients poll or subscribe for status.
7. Failed events use bounded retries and a dead-letter queue; replay is an explicit, audited operation.

Be ready to explain ordering, partition keys, consumer lag, schema compatibility, poison messages, replay safety, eventual consistency and observability.

### Minimum platform deliverables

- [ ] A Spring Boot service with validation, security boundary, Actuator health and tests.
- [ ] A microservice design with clear ownership and versioned API contracts.
- [ ] An event-driven flow using Kafka (or equivalent), outbox, idempotent consumer, retry and DLQ.
- [ ] Dockerfiles for Java and Python services plus a local Compose environment.
- [ ] Kubernetes manifests for Deployment, Service, ConfigMap/Secret references, probes and resource requests/limits.
- [ ] A deployment/rollback runbook with logs, metrics and distributed trace correlation.

