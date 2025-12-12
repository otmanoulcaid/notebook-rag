package org.mql.genai.rag.services;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;

import org.mql.genai.rag.utils.Assistant;
import org.springframework.stereotype.Service;

@Service
public class ChatAssistantService {

    private final ChatModel chatModel;
    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;

    public ChatAssistantService(ChatModel chatModel,
                                EmbeddingStore<TextSegment> embeddingStore,
                                EmbeddingModel embeddingModel) {

        this.chatModel = chatModel;
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
    }

    public String chat(String message) {

        ContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
        		.embeddingStore(embeddingStore)
        		.embeddingModel(embeddingModel)
        		.maxResults(3)
        		.build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .contentRetriever(retriever)
                .build();

        return assistant.chat(message);
    }
}
