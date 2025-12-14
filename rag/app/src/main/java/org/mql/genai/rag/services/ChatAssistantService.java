package org.mql.genai.rag.services;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatAssistantService {

    // private final ChatModel chatModel;
    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;

    public ChatAssistantService(ChatModel chatModel,
            EmbeddingStore<TextSegment> embeddingStore,
            EmbeddingModel embeddingModel) {

        // this.chatModel = chatModel;
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
    }

    public String chat(String message) {

        ContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(4)
                .build();

        // Wrap user message in Query
        List<Content> retrievedSegments = retriever.retrieve(new Query(message));

        // Build augmented prompt for logging
        StringBuilder augmentedPrompt = new StringBuilder();
        for (Content segment : retrievedSegments) {
            augmentedPrompt.append(segment.textSegment().text()).append("\n");
        }
        augmentedPrompt.append("\nUser: ").append(message);

        System.out.println("=== Augmented Prompt ===");
        System.out.println(augmentedPrompt.toString());
        System.out.println("========================");
        
        // Assistant assistant = AiServices.builder(Assistant.class)
        //         .chatModel(chatModel)
        //         .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
        //         .contentRetriever(retriever)
        //         .build();
        // return assistant.chat(message);

        return "fake result";
    }
}
