package org.mql.genai.rag.controllers;

import java.util.Map;

import org.mql.genai.rag.services.GemmaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/gemma")
class GemmaController {

    private GemmaService gemmaService;

    public GemmaController(GemmaService gemmaService) {
        this.gemmaService = gemmaService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody Map<String, String> payload) {
        String prompt = payload.get("prompt");
        long startTime = System.currentTimeMillis();
        String response = gemmaService.chat(prompt);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Temps pris pour Gemma: " + duration + " ms");

        return response;
    }
}
//curl -X POST http://localhost:11434/v1/completions -H "Content-Type: application/json" -d '{"prompt": "Hello Ollama, how are you today?" }'
