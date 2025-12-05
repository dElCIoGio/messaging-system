package main.java.messaging.domain;
import main.java.messaging.enums.Channel;
import main.java.messaging.enums.MessageType;

public class PostalMessage extends Message {

    private String paperSize;

    public PostalMessage(MessageType type, String subject, String content, String paperSize) {
        super(type, subject, content, Channel.POSTAL);

        this.paperSize = paperSize;
    }

    public String getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(String paperSize) {
        this.paperSize = paperSize;
    }
}
