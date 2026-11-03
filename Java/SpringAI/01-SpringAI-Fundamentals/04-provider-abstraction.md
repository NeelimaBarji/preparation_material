# Provider Abstraction and Model Portability

## 1. Architecture

`mermaid
flowchart TD
 A[Application] --> B[ChatClient]
 B --> C[ChatModel]
 C --> D[OpenAI]
 C --> E[Azure OpenAI]
 C --> F[Ollama]
 C --> G[Other Providers]
`

## 2. Why Abstract the Provider?

Provider choice can change because of price, latency, availability, data residency, security, capability, quality, or vendor strategy.

## 3. Portability Has Limits

Providers/models may differ in tool calling, streaming, multimodality, structured output, context window, latency, cost, and reasoning quality.

## 4. Lead-Level Model Routing

`mermaid
flowchart TD
 A[AI Routing Layer] --> B[Complex reasoning]
 A --> C[Simple task]
 B --> D[Premium model]
 C --> E[Fast / economical model]
`

## 5. Provider Fallback

`mermaid
flowchart LR
 A[Primary Model] -->|Success| B[Response]
 A -->|Eligible failure| C[Fallback Model]
 C --> B
`

Fallback should be explicit. Verify that the fallback supports required tools, output format, context size, and safety requirements.

## Interview Scenario

**How would you design provider fallback?** Keep application code on Spring AI abstractions, isolate provider-specific configuration, define fallback eligibility, and monitor error rate, latency, cost, and quality.