package dev.amol.chatclient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LLMAiService implements AiService {

    private final ChatClient chatClient;

    public LLMAiService(ChatClient.Builder builder, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = builder
                .defaultToolCallbacks(toolCallbackProvider)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(
                                        MessageWindowChatMemory.builder().build())
                                .build())
                .build();

        Arrays.stream(toolCallbackProvider.getToolCallbacks())
                .forEach(callback -> System.out.println("Registered tool: " + callback.getToolDefinition().name()));
    }


    @Override
    public String complete(String message) {
        // This is a placeholder implementation.
        // In a real application, this method would interact with an LLM service.
        return chatClient.prompt()
                .user(message)
                .call().chatResponse().getResult().getOutput().getText();
//                .content();

//        var ollamaApi = OllamaApi.builder().build();
//
//        var chatModel = OllamaChatModel.builder()
//                .ollamaApi(ollamaApi)
//                .defaultOptions(
//                        OllamaOptions.builder()
//                                .model(OllamaModel.LLAMA3_2)
//                                .temperature(0.2)
//                                .build())
//                .build();
//
//        ChatResponse response = chatModel.call(
//                new Prompt(message));
//
//        return response.getResult().getOutput().getText();
    }
}
