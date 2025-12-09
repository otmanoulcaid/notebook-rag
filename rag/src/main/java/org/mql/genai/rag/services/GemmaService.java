package org.mql.genai.rag.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
@Service
public class GemmaService {
    private final ChatClient chatClient;

    public GemmaService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String chat(String prompt) {
        String response = this.chatClient.prompt()
                .user(prompt)
                .call()
                .content();
        return response;
    }
}
