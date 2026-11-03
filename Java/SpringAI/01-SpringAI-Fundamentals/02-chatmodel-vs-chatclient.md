# ChatModel vs ChatClient

## Mental model

```text
ChatClient
    |
    | High-level fluent API
    v
ChatModel
    |
    | Provider/model abstraction
    v
Provider implementation
    |
    v
LLM
```

## ChatModel

Use when you need:
- lower-level control
- direct model interaction
- custom orchestration
- model/provider-specific handling

## ChatClient

Use when you need:
- fluent application code
- prompt composition
- Advisors
- tool calling
- streaming
- response extraction

## Interview answer

`ChatModel` is the lower-level abstraction for a chat model implementation. `ChatClient` is the application-friendly fluent API used to interact with chat models and compose application behavior. In most Spring applications I would use ChatClient and use ChatModel directly for specialized low-level orchestration.

## Typical code

```java
@RestController
class ChatController {

    private final ChatClient chatClient;

    ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    String chat(@RequestParam String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }
}
```
