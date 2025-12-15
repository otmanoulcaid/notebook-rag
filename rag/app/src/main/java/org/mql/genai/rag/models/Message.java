package org.mql.genai.rag.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat_messages")
public class Message {

    @Id
    private String id;

    private Sender sender;
    private String text;

    public enum Sender {
        USER, BOT
    }

    public Message() {}

    public Message(Sender sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Sender getSender() { return sender; }
    public void setSender(Sender sender) { this.sender = sender; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
