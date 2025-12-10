package messaging.domain;

import messaging.enums.Channel;
import messaging.enums.MessageType;

public class SMSMessage extends Message {

    private int maxLength;

    public SMSMessage(MessageType type, String subject, String content, int maxLength) {
        super(type, subject, content, Channel.SMS);
        this.maxLength = maxLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
