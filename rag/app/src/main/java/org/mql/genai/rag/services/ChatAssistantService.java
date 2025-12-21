package org.mql.genai.rag.services;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.service.AiServices;

import org.mql.genai.rag.models.Message;
import org.mql.genai.rag.utils.Assistant;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ChatAssistantService {

    private static final String PROMPT = loadPrompt();
    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;
    private final MessageService messageService;
    private final ChatModel chatModel;

    public ChatAssistantService(
            ChatModel chatModel,
            EmbeddingStore<TextSegment> embeddingStore,
            EmbeddingModel embeddingModel,
            MessageService messageService) {
        this.messageService = messageService;
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

        List<Content> retrievedSegments = retriever.retrieve(new Query(message));

        StringBuilder knowledge = new StringBuilder();
        for (Content segment : retrievedSegments)
            knowledge.append(segment.textSegment().text()).append("\n");

        String augmentedPrompt = String.format(PROMPT, knowledge, message);

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .contentRetriever(retriever)
                .build();
        String chatResponse = assistant.chat(augmentedPrompt);
        messageService.save(new Message(Message.Sender.USER, message));
        messageService.save(new Message(Message.Sender.BOT, chatResponse));
        return chatResponse;
    }

    private static String loadPrompt() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                Thread.currentThread()
                                        .getContextClassLoader()
                                        .getResourceAsStream("static/prompt.txt")),
                        StandardCharsets.UTF_8))) {

            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPrompt() {
        return PROMPT;
    }
}
