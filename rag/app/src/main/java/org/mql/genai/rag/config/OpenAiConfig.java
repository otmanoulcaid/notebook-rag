package org.mql.genai.rag.config;

import java.time.Duration;

import org.mql.genai.rag.models.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import dev.langchain4j.http.client.spring.restclient.SpringRestClientBuilderFactory;
import dev.langchain4j.model.openai.OpenAiChatModel;

@Configuration
public class OpenAiConfig {

    private final Properties props;

    public OpenAiConfig(Properties props) {
        this.props = props;
    }

    @Bean
    @Primary
    public OpenAiChatModel gptOssModel(Properties props) {
        return OpenAiChatModel.builder()
                .apiKey(this.props.getOssApiKey())
                .baseUrl(this.props.getOssUrl())
                .httpClientBuilder(new SpringRestClientBuilderFactory().create())
                .modelName("gpt-3.5-turbo")
                .timeout(Duration.ofMinutes(5))
                .build();
    }
}
