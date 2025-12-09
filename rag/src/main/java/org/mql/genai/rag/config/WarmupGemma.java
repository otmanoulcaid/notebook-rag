package org.mql.genai.rag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WarmupGemma implements CommandLineRunner {

    private final ChatClient chatClient;

    public WarmupGemma(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public void run(String... args) {
        System.out.println(
                chatClient.prompt()
                        .user("ping")
                        .call()
                        .content());
    }
}

