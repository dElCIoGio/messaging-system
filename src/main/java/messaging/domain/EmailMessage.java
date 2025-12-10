package messaging.domain;

import messaging.enums.Channel;
import messaging.enums.MessageType;

public class EmailMessage extends Message {

    private final boolean html;

    public EmailMessage(MessageType type, String subject, String content, boolean html) {
        super(type, subject, content, Channel.EMAIL);
        this.html = html;
    }

    public boolean isHtml() {
        return html;
    }

}
