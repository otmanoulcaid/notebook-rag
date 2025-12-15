package org.mql.genai.rag.services;

import org.mql.genai.rag.models.Message;
import org.mql.genai.rag.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Message save(Message message) {
        return repository.save(message);
    }

    public List<Message> findAll() {
        return repository.findAll();
    }
}
