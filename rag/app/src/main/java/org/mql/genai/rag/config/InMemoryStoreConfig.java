package org.mql.genai.rag.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

@Configuration
public class InMemoryStoreConfig {

    @Bean
    public InMemoryEmbeddingStore<TextSegment> embeddingStore() {
        System.out.println("Using InMemoryEmbeddingStore for RAG");
        return new InMemoryEmbeddingStore<>();
    }
}
