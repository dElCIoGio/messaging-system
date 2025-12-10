package messaging.sender;

import messaging.domain.Message;
import messaging.domain.User;
import messaging.enums.Channel;
import messaging.facade.SendResult;
import messaging.money.Money;

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
