# Provider Abstraction and Model Portability

## Architecture

```text
                 Application
                     |
                 ChatClient
                     |
                 ChatModel
          +----------+----------+
          |          |          |
       OpenAI     Azure       Ollama
          |          |          |
        Cloud      Cloud       Local
```

## Why provider abstraction?

Provider choice can change because of price, latency, availability, data residency, security requirements, capability, quality, and vendor strategy.

## Portability has limits

Different providers/models may differ in:
- tool calling
- streaming
- multimodality
- structured output
- context window
- latency
- cost
- reasoning quality

### Lead-level model routing

```text
                 AI Routing Layer
                       |
          +------------+------------+
          |                         |
    Complex reasoning         Simple task
          |                         |
     Premium model             Fast model
```

Use capability tests and evaluation datasets before replacing a production model.

## Interview scenario

**How would you design provider fallback?**

Keep application code on Spring AI abstractions, isolate provider-specific configuration, define explicit fallback eligibility, and verify that the fallback supports the required features. Monitor error rate, latency, cost, and quality after failover.
