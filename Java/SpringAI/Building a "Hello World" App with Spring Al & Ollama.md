## Page 18: Building a "Hello World" App with Spring AI & Ollama

### What is Ollama?

Ollama is a developer-friendly tool that allows you to run large language models (LLMs) locally on your machine — easily and efficiently. Built for privacy, speed, and offline use.

### Key features of Ollama:

**Local execution:** Run AI models entirely on your machine without internet dependency

**Simple interface:** Easy command-line tool for model management

**Model library:** Access to popular open-source models through a simple download system

**API access:** Provides REST API endpoints so you can integrate models into your applications

**Cross-platform:** Works on macOS, Linux, and Windows

To get started with Ollama, visit https://ollama.com/

---

## Page 19: Building a "Hello World" App with Spring AI & Ollama

**1. Install model in local using ollama** - We can install a model with a simple command like mentioned below,


**2. Add Required Dependencies** - Enables Spring Boot web and ollama integration

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-model-ollama</artifactId>
</dependency>
```

**3. configure properties in Spring AI app**

Configure below properties in application.properties or application.yml. API key is not required as the model is available in local.
spring.ai.model.chat = ollama
spring.ai.model.chat.model = llama3.2:1b
---

## Page 20: Building a "Hello World" App with Spring AI & Ollama

**4. Create the Chat Controller**

```java
@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    String chat(@RequestParam("message") String message) {
        return this.chatClient.prompt(message)
                              .call()
                              .content();
    }
}
```

You now have a minimal working Spring AI app that connects to ollama and responds to your prompts!

---

## Page 21: Building a "Hello World" App with Spring AI & Docker

### 🐳 What is Docker Model Runner?

Docker Model Runner is a general term referring to the practice of running AI models inside Docker containers, often to provide a local, isolated, and portable environment for serving LLMs or other machine learning models.

**It's commonly used to:**

- Run open-source LLMs (e.g., LLaMA2, Mistral, Falcon)
- Deploy tools like Ollama, LM Studio, LocalAI, Text Generation Web UI
- Host custom or fine-tuned models in a containerized setup

For more details, refer below link,

- https://docs.docker.com/ai/model-runner/

---

## Page 22: Building a "Hello World" App with Spring AI & Docker

**1. Install model in local using Docker** - We need to run a model locally using the below docker command,

  docker model run ai/gemma3

---


<img width="1175" height="301" alt="Screenshot 2026-09-21 at 11 41 30 AM" src="https://github.com/user-attachments/assets/5ae0a66f-7fec-49d5-beb7-2f2ae774a5e0" />
<img width="1181" height="567" alt="Screenshot 2026-09-21 at 11 41 02 AM" src="https://github.com/user-attachments/assets/9a6e9267-71f2-4437-8d87-225f5e3fb89e" />


## Page 23: Building a "Hello World" App with Spring AI & Docker

**4. Create the Chat Controller**

```java
@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    String chat(@RequestParam("message") String message) {
        return this.chatClient.prompt(message)
                              .call()
                              .content();
    }
}
```

You now have a minimal working Spring AI app that connects to Docker model and responds to your prompts!

---

## Page 24: Building a "Hello World" App with Spring AI & AWS Bedrock

### ☁️ What is AWS Bedrock?

Amazon Bedrock is a fully managed service offered by Amazon Web Services (AWS) that simplifies building and deploying generative AI applications.

### 🚀 Key Highlights

- No infrastructure to manage
- Access to multiple foundation models (FMs) from multiple vendors
- Use fine-tuning and RAG (Retrieval Augmented Generation) with your own data.
- Fully managed and scalable. Runs on AWS — serverless, secure, and enterprise-ready.

Similar offering is available in Azure(Azure OpenAI Service) and GCP(Google Vertex AI)

---

## Page 25: Building a "Hello World" App with Spring AI & AWS Bedrock

1. **Enable and Setup model, API keys in AWS**

You need to select a model and request access to it. Once the access is granted, configure access keys for an IAM user

**2. Add Required Dependencies** - Enables Spring Boot web and Bedrock integration

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>

<dependency>
  <groupId>org.springframework.ai</groupId>
  <artifactId>spring-ai-starter-model-bedrock-converse</artifactId>
</dependency>
```

---

## Page 26: Building a "Hello World" App with Spring AI & AWS Bedrock

**3. configure properties in Spring AI app**

Configure below properties in application.properties or application.yml. You need to mention a dummy API key


<img width="1211" height="346" alt="Screenshot 2026-09-21 at 11 41 49 AM" src="https://github.com/user-attachments/assets/c6ff29c4-ef24-4894-b60c-4342f186f027" />


---

## Page 27: Building a "Hello World" App with Spring AI & AWS Bedrock

**4. Create the Chat Controller**

```java
@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    String chat(@RequestParam("message") String message) {
        return this.chatClient.prompt(message)
                              .call()
                              .content();
    }
}
```

You now have a minimal working Spring AI app that connects to Bedrock and responds to your prompts!

---

## Page 28: Working with Multiple Chat Models in Spring AI

In real-world applications, it's common to integrate multiple chat models for better flexibility, performance, and user experience. Here are some practical scenarios:

### Why Use Multiple Chat Models?

**✅ Task-Based Model Selection**
Use a powerful model for complex reasoning and a lightweight model for basic queries.

**🔁 Fallback Strategy**
Automatically switch to another model if the primary one is unavailable.

**🧪 A/B Testing**
Test different models or configurations to compare accuracy, latency, and cost.

**👤 User Preference**
Allow users to choose their preferred model for interaction.

**🔧 Specialized Models**
Combine models with different strengths (e.g., one for code, another for creative writing).

---

## Page 29: Working with Multiple Chat Models in Spring AI

### Spring AI Default Behavior

Spring AI auto-configures a single ChatClient.Builder bean.
This is suitable for simple use cases but not sufficient when multiple models are involved.

### What You'll Need to Do

Manually configure multiple ChatClient.Builder beans for each model.
Inject the desired builder where needed based on your business requirements.

You need to disable the ChatClient.Builder autoconfiguration by setting the property `spring.ai.chat.client.enabled=false`

---

## Page 30: Working with Multiple Chat Models in Spring AI

Create multiple ChatClient instances manually for each model. The same client instances can be autowired in the required controller classes.

```java
@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel chatModel) {
        return ChatClient.create(chatModel);
    }

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel chatModel) {
        ChatClient.Builder chatClientBuilder =
                   ChatClient.builder(ollamaChatModel);
        return chatClientBuilder.build();
    }

}
```
