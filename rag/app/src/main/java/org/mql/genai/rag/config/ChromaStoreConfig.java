package org.mql.genai.rag.config;

import org.mql.genai.rag.models.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;

@Configuration
public class ChromaStoreConfig {

    @Bean
    @Primary
    public EmbeddingStore<TextSegment> embeddingStore(Properties props) {
        var embeddingStore = ChromaEmbeddingStore.builder()
                .baseUrl(props.getChromaUrl())
                .build();        
                return embeddingStore;
    }
}