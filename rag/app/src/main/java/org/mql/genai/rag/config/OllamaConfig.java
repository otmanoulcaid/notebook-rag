package org.mql.genai.rag.config;

import java.time.Duration;

import org.mql.genai.rag.models.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import dev.langchain4j.model.ollama.OllamaEmbeddingModel;

@Configuration
public class OllamaConfig {
    @Bean
    @Primary
    OllamaEmbeddingModel embeddingModel(Properties props) {
        return OllamaEmbeddingModel.builder()
                .baseUrl(props.getUrl())
                .modelName(props.getEmbeddingModel())
                .timeout(Duration.ofMinutes(Integer.valueOf(props.getTimeout())))
                .build();
    }
}
