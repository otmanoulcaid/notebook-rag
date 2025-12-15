package org.mql.genai.rag.config;

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
    public EmbeddingStore<TextSegment> embeddingStore() {
        var embeddingStore = ChromaEmbeddingStore.builder()
                .baseUrl("http://192.168.0.5:8000")
                .build();        
                return embeddingStore;
    }
}