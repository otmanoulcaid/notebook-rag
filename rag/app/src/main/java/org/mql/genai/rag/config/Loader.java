package org.mql.genai.rag.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Vector;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;

@Component
public class Loader implements CommandLineRunner {

	private EmbeddingStore<TextSegment> embeddingStore;

	private EmbeddingModel embeddingModel;

	public Loader(EmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
		this.embeddingStore = embeddingStore;
		this.embeddingModel = embeddingModel;
	}

	public void run(String... args) {

		var resourceUrl = getClass().getClassLoader().getResource("pdfs");
		if (resourceUrl == null) {
			System.out.println("no path found");
			return;
		}

		Path pdfsPath;
		try {
			pdfsPath = Paths.get(resourceUrl.toURI());
		} catch (Exception e) {
			throw new RuntimeException("Erreur lors de la conversion de l'URL en Path", e);
		}

		List<Document> documents = FileSystemDocumentLoader.loadDocuments(
				pdfsPath.toString(),
				new ApachePdfBoxDocumentParser());
		DocumentByParagraphSplitter splitter = new DocumentByParagraphSplitter(500, 30);

		List<TextSegment> pdfSegments = new Vector<>();
		for (Document document : documents) {
			List<TextSegment> segments = splitter.split(document);
			pdfSegments.addAll(segments);
		}

		int wordCount = 0;

		for (TextSegment segment : pdfSegments) {
			if (segment.text() == null || segment.text().isBlank()) continue;
			String[] words = segment.text().trim().split("\\s+");
			wordCount += words.length;
		}
		System.out.println(wordCount);
		Instant now = Instant.now();
		System.out.println(">>>>>>>>>>>>>>>>>>>>> start the emmbedding <<<<<<<<<<<<<<<<<<<<<<");
		List<Embedding> embeddings = embeddingModel.embedAll(pdfSegments).content();
		embeddingStore.addAll(embeddings, pdfSegments);
		long time = Duration.between(now, Instant.now()).toMinutes();
		System.out.println(">>>>>>>>>>>>>>>>>>>>> Done from the emmbeddingin : "+ time + " <<<<<<<<<<<<<<<<<<<<<<");
	}
}
