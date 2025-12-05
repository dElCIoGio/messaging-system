package main.java.messaging.domain;

import main.java.messaging.enums.Channel;
import main.java.messaging.enums.MessageType;
import main.java.messaging.utils.UUIDHandler;

public class Message {

    private final String id;
    private final Channel channel;
    private final MessageType type;
    private String subject;
    private String content;

    public Message(MessageType type, String subject, String content, Channel channel) {
        this.channel = channel;
        this.id = UUIDHandler.generateId();
        this.type = type;
        this.subject = subject;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public MessageType getType() {
        return type;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
