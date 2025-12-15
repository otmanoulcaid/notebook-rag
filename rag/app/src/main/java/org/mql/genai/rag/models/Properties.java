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
    private String url;
    @Value("${ollama.chat}")
    private String chatModel;

    @Value("${model.timeout}")
    private String timeout;

	@Value("${oss.key}")
	private String ossApiKey;
	@Value("${oss.url}")
	private String ossApiUrl;

	@Value("${openai.embedding}")
	private String openAiEmbeddingModel;
	@Value("${openai.chat}")
	private String openAiChatModel;
	@Value("${openai.key}")
	private String openAiKey;

	public String getEmbeddingModel() {
		return embeddingModel;
	}

	public String getChatModel() {
		return chatModel;
	}

	public String getUrl() {
		return url;
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
