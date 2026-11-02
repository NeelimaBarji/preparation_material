# Portfolio Projects

Build fewer, deeper projects. Each should include a clear user problem, architecture diagram, setup instructions, tests, evaluation results, security notes, and limitations.

## Project 1 — Agentic DevOps RCA Assistant

**Goal:** Analyze CI/CD failures and produce evidence-grounded root-cause reports; remediation remains approval-gated.

### Suggested architecture
- Java Spring Boot API/gateway for users, authentication, job orchestration and audit.
- Python AI service for log parsing, retrieval, LLM reasoning and agent workflow.
- Read-only GitLab pipeline and log-search tools.
- Optional RAG index over runbooks and known incidents.
- LangGraph or smolagents orchestration with bounded state and step count.
- Langfuse (or equivalent) for model/tool traces.
- Slack notification adapter; no automatic production changes in initial release.

### Incremental milestones
1. Fetch a pipeline and list failed jobs.
2. Retrieve relevant logs with strict size/time bounds.
3. Extract facts and relevant runbook passages.
4. Generate a report with evidence references and uncertainty labels.
5. Add an evaluation set of known incidents and compare diagnoses.
6. Add human approval for any simulated remediation action.
7. Add tracing, audit, security tests and deployment documentation.

### Acceptance evidence
- Reproducible sample failures and expected diagnosis notes.
- Tool calls are read-only by default and permission-scoped.
- Report distinguishes observed facts, likely causes and hypotheses.
- Evaluation includes false positives and insufficient-evidence cases.
- Approval and audit trail demonstrated for a simulated action.

## Project 2 — Enterprise RAG Knowledge Assistant

**Goal:** Answer technical questions over approved documents with source citations and document-level access controls.

### Suggested architecture
- Java Spring Boot API, authentication and conversation/session boundary.
- Ingestion worker for Markdown/PDF/HTML.
- PostgreSQL + pgvector for metadata and vector retrieval.
- Embedding provider abstraction.
- Hybrid lexical/vector retrieval and optional reranker.
- Generation with citations, no-answer behavior and token limits.
- Evaluation pipeline with labeled query/document pairs.

### Incremental milestones
1. Parse and normalize source documents.
2. Chunk with stable IDs and metadata.
3. Embed and index content.
4. Implement vector retrieval and metadata filters.
5. Add hybrid search and reranking; benchmark.
6. Generate cited answers and abstain when evidence is insufficient.
7. Add tenant/ACL filtering before context construction.
8. Add prompt injection tests, deletion/version handling and tracing.

### Acceptance evidence
- Dataset and ingestion instructions.
- Recall@k/MRR or nDCG retrieval results.
- Citation correctness examples.
- Cross-tenant access denial tests.
- Failure analysis for parsing, retrieval and generation.

## Project 3 — Snowflake AI Data Analyst

**Goal:** Let an authorized user ask questions about approved analytical datasets and receive validated, read-only query results and explanations.

### Suggested architecture
- Java Spring Boot API and identity boundary.
- Python or Java query-planning service.
- Schema/catalog retrieval through RAG.
- SQL parser/validator and allowlisted schema/table access.
- Snowflake read-only, least-privilege role.
- Query limits, timeout, row caps and audit trail.
- LLM explanation of returned aggregate results.

### Incremental milestones
1. Publish approved schema/catalog metadata.
2. Retrieve relevant tables and columns from natural-language questions.
3. Generate SQL against a constrained schema.
4. Parse and validate SQL before execution.
5. Execute with read-only credentials and resource limits.
6. Return results with query explanation and provenance.
7. Test prompt injection, unauthorized table access and unsafe SQL.

### Acceptance evidence
- A fixed set of business questions with expected SQL/result shape.
- SQL validation and negative security tests.
- Query latency and cost notes.
- No unbounded or write-capable execution.

## Portfolio README template

For each project, document:
1. Problem and target user.
2. Scope and non-goals.
3. Architecture diagram and request flow.
4. Technology choices and alternatives.
5. Local setup and configuration (never commit secrets).
6. Tests and evaluation methodology/results.
7. Security and privacy controls.
8. Failure modes, known limitations and future work.
9. Demo walkthrough and sample inputs/outputs.
