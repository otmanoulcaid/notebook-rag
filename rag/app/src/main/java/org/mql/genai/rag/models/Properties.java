package org.mql.genai.rag.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * These keys are provided only for development and testing purposes.
 * They are temporary and will NOT be used or deployed in production.
 */
@Component
public class Properties {
    @Value("${ollama.embedding}")
    private String embeddingModel;
    @Value("${ollama.url}")
    private String ollamaUrl;
    @Value("${ollama.chat}")
    private String ollamaChatModel;

    @Value("${chroma.url}")
    private String chromaUrl;

    @Value("${model.timeout}")
    private String timeout;

	@Value("${oss.key}")
	private String ossApiKey;
	@Value("${oss.url}")
	private String ossApiUrl;

	@Value("${openai.embedding:none}")
	private String openAiEmbeddingModel;
	@Value("${openai.chat}")
	private String openAiChatModel;
	@Value("${openai.key:none}")
	private String openAiKey;

	public String getEmbeddingModel() {
		return embeddingModel;
	}

	public String getOllamaChatModel() {
		return ollamaChatModel;
	}

	public String getOllamaUrl() {
		return ollamaUrl;
	}

	public String getChromaUrl() {
		return chromaUrl;
	}

	public String getTimeout() {
		return timeout;
	}

	public String getOssApiKey() {
		return ossApiKey;
	}

	public String getOpenAiKey() {
		return openAiKey;
	}

	public String getOssUrl() {
		return ossApiUrl;
	}
	
	public String getOpenAiEmbeddingModel() {
		return openAiEmbeddingModel;
	}

	public String getOpenAiChatModel() {
		return openAiChatModel;
	}
}
