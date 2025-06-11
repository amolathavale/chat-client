package dev.amol.chatclient;

import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;
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
    CommandLineRunner run(List<McpSyncClient> clients) {
        return args -> {
            var tools = clients.get(0).listTools();
            tools.tools().forEach(tool -> {
                System.out.println("Tool Name: " + tool.name());
                System.out.println("Tool Description: " + tool.description());
                System.out.println("-----------------------------");
            });

            McpSchema.CallToolResult currentTimeResult = clients.get(0).callTool(new McpSchema.CallToolRequest("getCurrentTime", "{}"));
            currentTimeResult.content().stream().map(Object::toString).forEach(System.out::println);

            var params = """					
                    		{
                    			"number1": 10.0,
                    			"number2": 4.0
                    		}
                    """;

            McpSchema.CallToolResult multResult = clients.get(0).callTool(new McpSchema.CallToolRequest("mult", params));
            currentTimeResult.content().stream().map(Object::toString).forEach(System.out::println);

        };
    }

}
