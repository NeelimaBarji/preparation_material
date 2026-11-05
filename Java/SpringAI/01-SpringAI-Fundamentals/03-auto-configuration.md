# Spring Boot Auto-Configuration for Spring AI

## 1. How Configuration Becomes Beans

```mermaid
flowchart TD
    A[Spring AI Starter] --> B[application.yml / properties]
    B --> C[Spring Boot Auto-Configuration]
    C --> D[ChatModel Bean]
    C --> E[ChatClient.Builder]
    C --> F[Integration Infrastructure]
    D --> G[Application]
    E --> G
    F --> G
```

## 2. Secure Configuration

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
```

```mermaid
flowchart LR
    A[Developer] --> B[Environment / Secret Store]
    C[CI/CD] --> D[CI Secret Variables]
    E[Kubernetes] --> F[Secret / External Secret]
    G[Cloud] --> H[Managed Secret Service]
```

**Never commit provider API keys into Git.**

## 3. Good Practices

- Use the appropriate Spring AI starter.
- Inject configured beans instead of constructing provider clients everywhere.
- Externalize environment-specific configuration.
- Keep secrets outside source control.

## Interview Point

Auto-configuration reduces boilerplate, but senior engineers should understand bean creation, configuration resolution, and where application-specific overrides belong.
