# Spring Boot Auto-Configuration for Spring AI

## Flow

```text
Spring AI Starter
       |
       v
application.yml / properties
       |
       v
Spring Boot Auto-Configuration
       |
       +--> ChatModel
       +--> ChatClient.Builder
       +--> Integration infrastructure
       |
       v
Application beans
```

## Good practices
- Use the appropriate Spring AI starter
- Keep provider settings in configuration
- Inject beans instead of manually creating clients everywhere
- Use environment-specific configuration
- Keep secrets outside source control

### Secure configuration

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
```

Production secret flow:

```text
Local -> local secret / environment
CI/CD -> secret variables
Kubernetes -> Secret / External Secret
Cloud -> managed secret service
```

## Interview point

Auto-configuration reduces boilerplate, but senior engineers still need to understand which beans exist, how configuration is resolved, and when to override defaults.
