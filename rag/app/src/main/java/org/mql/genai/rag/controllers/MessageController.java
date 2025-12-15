package org.mql.genai.rag.controllers;

import org.mql.genai.rag.models.Message;
import org.mql.genai.rag.services.MessageService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/messages")
    public List<Message> readAll() {
        return service.findAll();
    }
}
