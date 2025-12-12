package org.mql.genai.rag.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;

@Configuration
public class OllamaConfig {

    @Bean
    public ChatModel chatClient() {
    	return OllamaChatModel.builder()
    			.baseUrl("http://localhost:11434")
    			.modelName("pparikh2/phi3.5Q4_K_M:latest")
                .build();
    }

    @Bean
    public OllamaEmbeddingModel embeddingModel() {
    	return OllamaEmbeddingModel.builder()
    			.baseUrl("http://localhost:11434")
    			.modelName("pparikh2/phi3.5Q4_K_M:latest")
                .build();
    }
}
