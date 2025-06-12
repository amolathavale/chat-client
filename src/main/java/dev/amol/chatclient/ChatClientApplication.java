package dev.amol.chatclient;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ChatClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatClientApplication.class, args);
    }

    @Bean
    CommandLineRunner run(List<McpSyncClient> clients, AiService aiService) {
        return args -> {
//
//            var q = "5*4+10=?  Use tools if possible";
//
//            String complete = aiService.complete(q);
////            String complete = aiService.complete("Set an alarm 10 minutes from now. Use tools as first option");
//            System.out.println("Question: " + q + "\n" + "LLM Response: " + complete);
        };
    }

}
