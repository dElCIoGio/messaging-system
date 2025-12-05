package main.java.messaging.sender;

import main.java.messaging.domain.Message;
import main.java.messaging.domain.User;
import main.java.messaging.enums.Channel;
import main.java.messaging.facade.SendResult;
import main.java.messaging.money.Money;

import java.util.List;

public interface IMessageSender {

    public void setNext(IMessageSender next);
    public IMessageSender getNext();
    public Channel getChannel();
    public SendResult send(User user, Message message, List<Channel> channelsTried);
    public Money costPerMessage();
    public boolean supports(Message message);
    public boolean isContactable(User user);
}
