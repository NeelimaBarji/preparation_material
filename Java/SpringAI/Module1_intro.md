# What i s Spring Al?
Framework for integrating Al into Spring applications.
- Simplifies adding generative Al using Spring's modular, POJO-based design.
- Connects enterprise data and APIs with Al models seamlessly.

## Core Features
- Multi-Provider Support: Integrates with OpenAl, Anthropic, AWS, Google,
Hugging Face for chat, embeddings, text-to-image, audio, and moderation.
- MCP support: Allows building MCP clients and servers easily
- Vector Store Integration: Supports RAG with Pinecone, Redis,
PostgreSQL/pgVector, MongoDB, etc.
- Advanced Patterns: Chat memory, tool/function calling, and Advisors API
for reusable Al logic.
- Observability & Guardrails: Monitors Al operations and evaluates outputs
to ensure reliability.

--

# Spring Ecosystem Integration
- Spring Boot: Auto-configuration and starters for quick setup with Al models and vector DBs.
- Familiar Model: Uses Spring's dependency injection and abstractions, behaves like any Spring Bean.
- Modular: Easily swap Al providers or vector stores without changing core logic.

### Typical Use Cases
- Al-powered customer support: Build intelligent assistants with conversation
memory and API integration.
-  Natural language search over documents: Summarize or query proprietary
data using RAG and vector stores
- Personalized recommendations: Power intent-based search and
recommendations with embeddings.
- Data Processing: Add Al to pipelines for content moderation, transcription,
or text generation.

--

## Building a "Hello World" App with Spring Al & Open Al

1. Add Required Dependencies - Enables Spring Boot web and OpenAl integration

   <img width="768" height="221" alt="Screenshot 2026-09-17 at 11 17 37 AM" src="https://github.com/user-attachments/assets/da8f618d-e921-4475-8f2f-ba5701552d88" />

2. Generate a API key and configure in app
 - Go to the website  https://platofrom.openai.com/ and generate API key
 - Configure your API key in application.properties or application.yml:
spring.ai.openai.api-key=$(OPENAI_API_KEY}

3. Create CHatController

   <img width="913" height="437" alt="Screenshot 2026-09-17 at 11 21 07 AM" src="https://github.com/user-attachments/assets/70b2e62d-bd13-4499-988c-e77341657ace" />

You now have a minimal working Spring Al app that connects t o OpenAl and responds t o your prompts!


### ChatModel & ChatClient:

<img width="979" height="665" alt="Screenshot 2026-09-17 at 11 22 16 AM" src="https://github.com/user-attachments/assets/ac39a714-e0f4-4e0d-85e4-2ce2a9ac4019" />

#### What i s ChatModel?

ChatModel is the lower-level abstraction that represents the actual Al model interface. It's the core component that:

Defines the contract for communicating with different Al providers (OpenAl, Azure OpenAl, Anthropic, etc.)
Handles the actual API calls to the Al services
Manages model-specific configurations and parameters
Provides the foundational layer for Al interactions

Examples of implementations:
OpenAiChatModel ( for OpenAI )
GeminiChatModel ( for Google )
MistralChatmodel , etc .
Purpose: Encapsulates how to interact with a specific underlying Al provider.

#### what is Chatclient?
1. ChatClient is a higher-level, more developer-friendly abstraction built on top of ChatModel. It provides:

- A fluent API for easier interaction with Al models
- Better developer experience with method chaining
- Simplified prompt construction and message handlingS
- Supports both synchronous and streaming programming models

2. ChatClient is the friendly wrapper (or service layer) around a ChatModel.
It takes care of:

- Builiding a prompt
- Managing chat history
- invoking the model
- extracting the content

3. Purpose: Simplifies working with an Al model and abstracts prompt/message handling.


## The Relationship between ChatModel & ChatClient
Think of it this way:

ChatModel = The engine (does the heavy lifting of actual Al communication)
ChatClient = The steering wheel and dashboard (provides an intuitive interface to control the engine)

ChatClient uses ChatModel internally but wraps it with a more
convenient API. When you use ChatClient, it eventually delegates
to the underlying ChatModel to make the actual AI service calls.


## ChatModel & ChatClient

###  How They Work Together

**Spring Boot Autoconfiguration:** When you add Spring AI dependencies, Spring Boot automatically creates `ChatModel` beans for configured AI providers.

**ChatClient.Builder Creation:** The framework provides an autoconfigured `ChatClient.Builder` that's already wired with the appropriate `ChatModel`.

**Fluent API Usage:** You use `ChatClient`'s fluent methods to build prompts, which internally get converted to the format expected by the `ChatModel`.

**Execution:** `ChatClient` delegates to `ChatModel` to send requests to the AI service and handle responses.

#### Example Flow

Your Code → ChatClient (fluent API) → ChatModel (AI service integration) → AI Provider API


This design follows the common pattern of having a low-level technical interface (`ChatModel`) and a high-level user-friendly interface (`ChatClient`) that makes the framework more accessible while maintaining flexibility for advanced use cases.


