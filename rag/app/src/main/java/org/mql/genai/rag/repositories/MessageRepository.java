package org.mql.genai.rag.repositories;

import org.mql.genai.rag.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
