package org.mql.genai.rag.controllers;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import org.mql.genai.rag.services.ChatAssistantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChatAssistantController {

    private final ChatAssistantService chatAssistantService;

    public ChatAssistantController(ChatAssistantService chatAssistantService) {
        this.chatAssistantService = chatAssistantService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");

        if (prompt == null || prompt.isEmpty())
        	return "Prompt is empty!";
        Instant now = Instant.now();
        String result = chatAssistantService.chat(prompt);
        double timeProcess = Duration.between(now, Instant.now()).toMillis();

        System.out.println("Temps : " + timeProcess + "ms");
        return result;
    }
}
