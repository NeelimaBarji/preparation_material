# Spring AI Architecture

## Visualization

```text
+---------------------------+
|     Spring Boot App       |
| Controller / Service      |
+-------------+-------------+
              |
              v
+---------------------------+
|        ChatClient         |
| Application-facing API   |
+-------------+-------------+
              |
              v
+---------------------------+
|      Advisor Chain        |
| Memory / RAG / Tools      |
+-------------+-------------+
              |
              v
+---------------------------+
|        ChatModel          |
| Provider abstraction      |
+-------------+-------------+
              |
      +-------+-------+
      |       |       |
      v       v       v
   OpenAI  Azure    Ollama
      |       |       |
      +-------+-------+
              |
              v
       Foundation Model
```

## Responsibility boundaries

**Application layer**
- Business rules
- API contracts
- Authentication/authorization
- Domain orchestration

**ChatClient**
- Prompt construction
- Application-facing AI interaction

**Advisor layer**
- Reusable GenAI behaviors

**ChatModel**
- Model/provider communication abstraction

**Provider adapter**
- Provider-specific translation

**Model**
- Generates the AI response

## Lead-level rule

Keep business logic independent from vendor SDK classes where practical.

Preferred:

```text
OrderService
   |
ChatClient
   |
ChatModel abstraction
```

Avoid spreading provider-specific request/response types through the domain layer.
