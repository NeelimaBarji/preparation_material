# Java, Spring Boot & AI Engineering — Interview Preparation

<p align="center">
  <strong>A structured knowledge base for senior Java engineering, backend architecture, system design, and applied AI.</strong>
</p>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk&logoColor=white" />
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-Backend%20Engineering-6DB33F?logo=springboot&logoColor=white" />
  <img alt="AI Engineering" src="https://img.shields.io/badge/AI-LLM%20%7C%20RAG%20%7C%20Agents-7B61FF" />
  <img alt="Content" src="https://img.shields.io/badge/Format-Notes%20%7C%20Code%20%7C%20Diagrams-blue" />
</p>

Welcome! This repository is my organized engineering study space for preparing for senior Java/Spring Boot roles and growing toward AI engineering. It brings together concise explanations, practical examples, visual diagrams, coding exercises, architecture notes, and interview-focused revision material.

> **Best way to explore:** Start with the curated topic guides below. Each section links to its own index and detailed notes.

---

## Start here

| Area | What you'll find | Start |
|---|---|---|
| Java & Spring Boot | Java language features, Spring JDBC, Spring Data JDBC, Spring Data JPA, Spring AI | [Explore Java](./Java/ReadMe.md) |
| Data structures & algorithms | Problem-solving patterns, Java implementations, and practice notes | [Explore DSA](./DSA/README.md) |
| System design & LLD | Scalability, distributed systems, SOLID, design patterns, and low-level design | [Explore System Design](./SystemDesignNotes/README.md) |
| AI engineering | Spring AI, LLM foundations, RAG-related resources, and AI engineering learning notes | [Explore Spring AI](./Java/SpringAI/ReadMe.md) |
| AI career preparation | AI/Java role research, skill mapping, and application workflow notes | [Explore AI Job Preparation](./AI_job_apply/ReadMe.md) |
| Research notes | Research ideas, study plans, protocols, and supporting reading | [Explore Research](./Researches/README.md) |
| Progress log | Ongoing study and learning updates | [Open Notes](./Notes/README.md) |

## Recommended learning paths

### 1. Senior Java / Spring Boot engineer

1. Review [Java 17 features](./Java/JavaFeatures/java17-elaborate-guide-with-questions.md) and [Java 21 features](./Java/JavaFeatures/java21-features-guide-with-qa.md).
2. Study [Spring JDBC](./Java/SpringBoot/SpringJDBC/Spring%20JDBC.md).
3. Compare [Spring Data JDBC](./Java/SpringBoot/SpringJDBC/SpringDataJDBC.md) with [Spring Data JPA](./Java/SpringBoot/SpringJDBC/SpringDataJPA.md).
4. Explore [Spring Boot virtual threads](./Java/JavaFeatures/spring-boot-virtual-threads-realworld.md).
5. Practice algorithms in [DSA](./DSA/README.md) and architecture topics in [System Design](./SystemDesignNotes/README.md).

### 2. Java developer moving into AI engineering

1. Build the foundations with the [Java AI Developer interview preparation plan](./Java/Java_AI_Developer_Interview_Prep_Plan.md).
2. Explore [Spring AI notes and resources](./Java/SpringAI/ReadMe.md).
3. Review the [AI preparation tracker](./Java/Java_AI_Prep_Tracker.xlsx).
4. Connect LLM, RAG, tool-calling, evaluation, and observability concepts to production backend architecture.

### 3. System design interview preparation

1. Start with [system design fundamentals](./SystemDesignNotes/Refer.md).
2. Review [SOLID principles](./SystemDesignNotes/SOLID_Principle.md).
3. Explore [design pattern notes](./SystemDesignNotes/Design%20patterns/Readme.md).
4. Practice low-level design and problem-solving exercises.

---

## Repository map

```text
preparation_material/
├── Java/
│   ├── JavaFeatures/          # Java 17/21 and modern JVM topics
│   ├── Microservices/         # Distributed and event-driven architecture
│   ├── SpringAI/              # Spring AI and LLM application notes
│   ├── SpringBoot/            # Spring Boot topic guides
│   └── Java_AI_Developer...   # AI engineering career preparation
├── DSA/                       # Data structures and algorithm patterns
├── SystemDesignNotes/         # System design, LLD, SOLID, design patterns
├── Researches/                # Research ideas and study material
├── AI_job_apply/              # AI-assisted job search/application notes
├── Notes/                     # Learning progress
├── blind75-main/              # Blind 75 practice reference collection
├── awesome-*-main/            # Additional topic reference collections
└── learn-ai-engineering-main/ # AI engineering reference collection
```

## Featured Spring persistence guides

The Spring JDBC folder contains three complementary guides:

| Guide | Focus |
|---|---|
| [Spring JDBC](./Java/SpringBoot/SpringJDBC/Spring%20JDBC.md) | SQL-first access with JdbcTemplate, RowMapper, batching, transactions, and connection pools |
| [Spring Data JDBC](./Java/SpringBoot/SpringJDBC/SpringDataJDBC.md) | Aggregate-oriented persistence, repositories, explicit saves, and optimistic locking |
| [Spring Data JPA](./Java/SpringBoot/SpringJDBC/SpringDataJPA.md) | Entity relationships, persistence context, dirty checking, fetching, N+1, and ORM performance |

## How this repository is organized

- **Curated notes:** topic guides and explanations maintained for learning and interview revision.
- **Hands-on material:** Java examples, DSA implementations, and practical exercises.
- **Visual learning:** Mermaid diagrams and architecture flows embedded in Markdown files.
- **Reference collections:** directories with names such as `awesome-*-main`, `blind75-main`, and `learn-ai-engineering-main` are retained as reference material. They should be treated as upstream collections rather than original work; check each collection's own license and attribution before reusing or redistributing its contents.

## Contributing to these notes

This is a personal learning repository, but suggestions and corrections are welcome.

- Prefer clear topic-based folders and descriptive file names.
- Use Markdown headings, concise examples, and diagrams where they improve understanding.
- Include trade-offs, production considerations, and interview takeaways—not just definitions.
- Link to official documentation for framework-specific behavior.
- Keep secrets, credentials, personal data, and employer/client-confidential material out of commits.

## Current focus

- Senior Java and Spring Boot engineering
- Microservices, event-driven architecture, and system design
- Data access patterns and persistence performance
- Spring AI, LLM applications, RAG, and agentic workflows
- DSA and interview problem-solving

---

<p align="center">
  <em>Learn deeply. Build thoughtfully. Explain clearly.</em>
</p>
