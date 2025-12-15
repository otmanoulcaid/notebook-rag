package org.mql.genai.rag.config;

import java.nio.file.Path;
import java.nio.file.Paths;
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

	private EmbeddingStore<TextSegment> emebedingStore;
	private EmbeddingModel embeddingModel;
	
	public Loader(EmbeddingStore<TextSegment> emebedingStore, EmbeddingModel embeddingModel) {
		this.emebedingStore = emebedingStore;
		this.embeddingModel = embeddingModel;
	}

    public void run(String... args) {

    	var resourceUrl = getClass().getClassLoader().getResource("pdfs");
    	if (resourceUrl == null) {
    	    System.out.println("no path found");
    	    return;
    	}

    	System.out.println(">>>>>>>>>>>>>> " + resourceUrl + " <<<<<<<<<<<<<<<<");
        
    	Path pdfsPath;
    	try {
            pdfsPath = Paths.get(resourceUrl.toURI());
    	} catch (Exception e) {
            throw new RuntimeException("Erreur lors de la conversion de l'URL en Path", e);
    	}
        
    	List<Document> documents = FileSystemDocumentLoader.loadDocuments(
            pdfsPath.toString(),
            new ApachePdfBoxDocumentParser()
    	);
        DocumentByParagraphSplitter splitter = new DocumentByParagraphSplitter(1000, 200);

        List<TextSegment> segments = new Vector<>();
        
        System.out.println(">>>>>>>>>>>>>> embedding start <<<<<<<<<<<<<<<<");
        for (Document document : documents)
        	segments.addAll(splitter.split(document));
        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();
        emebedingStore.addAll(embeddings, segments);
        System.out.println(">>>>>>>>>>>>>> embedding Done <<<<<<<<<<<<<<<<");
    }
}