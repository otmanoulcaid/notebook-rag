package org.mql.genai.rag.config;

import java.time.Duration;

import org.mql.genai.rag.models.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;

@Configuration
public class OpenAiConfig {

        @Bean
        @Primary
        public EmbeddingModel gptEmbeddingModel(Properties props) {

                return OpenAiEmbeddingModel.builder()
                                .baseUrl(props.getOssUrl())
                                .apiKey(props.getOssApiKey())
                                .modelName(props.getOpenAiEmbeddingModel())
                                .timeout(Duration.ofMinutes(Integer.valueOf(props.getTimeout())))
                                .build();
        }

        @Bean
        @Primary
        public OpenAiChatModel gptOssModel(Properties props) {
                return OpenAiChatModel.builder()
                                .baseUrl(props.getOssUrl())
                                .apiKey(props.getOssApiKey())
                                .modelName(props.getOpenAiChatModel())
                                .timeout(Duration.ofMinutes(Integer.valueOf(props.getTimeout())))
                                .build();
        }
}
