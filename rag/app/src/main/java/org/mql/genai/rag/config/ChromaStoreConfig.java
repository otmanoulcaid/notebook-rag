package org.mql.genai.rag.config;

import org.springframework.context.annotation.Configuration;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;

@Configuration
public class ChromaStoreConfig {

//    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
    	System.out.println("#######################################################################################################################");
        var embeddingStore = ChromaEmbeddingStore.builder()
                .baseUrl("http://localhost:8000")
                .collectionName("rag_documents")
                .build();
        System.out.println("#######################################################################################################################");
        return embeddingStore;
    }
}
