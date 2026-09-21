## Unlocking the Power of Spring AI

### What You've Built So Far

- A simple Spring AI app that sends prompts and receives responses
- Great start—but it only scratches the surface!

### 🚀 What's Coming Up

Spring AI can do much more:

- Message roles
- Advisors
- Prompt Templates
- Chat History Management
- RAG (Retrieval-Augmented Generation)
- Function Calling (Tools Integration)
- Building MCP clients & Server

### 📚 Get Ready!

You're about to unlock the full potential of Generative AI with Spring AI!

## Page 32: Understanding Message Roles in LLMs

### What Are Message Roles in LLMs?

When interacting with Large Language Models (LLMs), we send prompts made up of messages. Each message has a role that helps the LLM understand the context and how to respond.

### Common Roles (Used by Most APIs)

| Role | Description |
|---|---|
| 👤 User | What the user says or asks. Example: "Tell me a joke." |
| 🛠️ System | Instructions for how the LLM should behave. Example: "Be formal." |
| 🤖 Assistant | The LLM's response. Example: "Sure! Why did the chicken..." |
| ⚙️ Function/Tool | Special instructions to run a function or fetch data |

### Example Prompt Breakdown:

```
System: "You are a friendly tour guide."
User: "What are the top 3 places to visit in Rome?"
Assistant: "Sure! The top 3 are..."
```

## Page 33: Understanding Message Roles in LLMs

### 💡 Analogy: It's Like a Stage Play 🎭

System: The director giving stage instructions (e.g., "Act like a professional chef")
User: The audience asking a question (e.g., "How do I cook pasta?")
Assistant: The actor replying (e.g., "Boil water and add pasta for 8 minutes.")
Function: A backstage helper fetching ingredients or recipes


### Not All LLM model providers Support All Roles

| Provider | Supported Roles |
|---|---|
| OpenAI | User, System, Assistant, Function |
| Anthropic | User, System, Assistant |
| Mistral AI | User, System, Assistant |
| Google Gemini | No System/Function roles. Only: User, Model (like Assistant) |

### Spring AI Handling Tip

If the System role is not supported (like in Gemini), Spring AI combines the System message into the User message behind the scenes.

## Page 34: Understanding Message Roles in LLMs

### Creating the ChatClient with a Default System Message

```java
this.chatClient = chatClientBuilder
    .defaultSystem("""
        You are an internal HR assistant...
    """).build();
```

- Sets the default behavior/personality of the AI assistant.
- Used for general fallback behavior across requests.

### Overriding System Message (Optional) in Runtime

```java
chatClient.prompt()
    .system("""
        You are an internal IT helpdesk assistant...
    """)
```

- Overrides the default system message.
- Useful for changing the assistant's role dynamically (e.g., HR instead of IT).

---

## Page 35: Understanding Message Roles in LLMs

### Sending a User Message

```java
chatClient.prompt()
    .user(message)
```

- This is the actual input from the user (e.g., "Can I apply for leave during my probation?")

### Return Assistant's Reply

```java
return this.chatClient.prompt()
                .user(message)
                .call().content();
```

- Extracts and returns the LLM's generated response as plain text
