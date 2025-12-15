package org.mql.genai.rag.config;

import java.time.Duration;

import org.mql.genai.rag.models.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;

@Configuration
public class OllamaConfig {

    private final Properties props;
    public OllamaConfig(Properties props) {
        this.props = props;
    }

    @Bean
    ChatModel chatClient() {
        return OllamaChatModel.builder()
                .baseUrl(this.props.getUrl())
                .modelName(this.props.getChatModel())
                .timeout(Duration.ofMinutes(Integer.valueOf(props.getTimeout())))
                .build();
    }

    @Bean
    OllamaEmbeddingModel embeddingModel() {
        return OllamaEmbeddingModel.builder()
                .baseUrl(this.props.getUrl())
                .modelName(this.props.getEmbeddingModel())
                .timeout(Duration.ofMinutes(Integer.valueOf(props.getTimeout())))
                .build();
    }
}
