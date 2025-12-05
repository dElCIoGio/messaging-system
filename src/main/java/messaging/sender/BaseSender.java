package main.java.messaging.sender;

import main.java.messaging.domain.Message;
import main.java.messaging.domain.User;
import main.java.messaging.enums.Channel;
import main.java.messaging.factory.MoneyFactory;
import main.java.messaging.money.Money;

public abstract class BaseSender implements IMessageSender {

    private IMessageSender next;
    private final double cost;
    private final Channel channel;

    public BaseSender(double cost, Channel channel) {
        this.cost = cost;
        this.channel = channel;
    }

    public void setNext(IMessageSender next) {
        this.next = next;
    }

    public IMessageSender getNext() {
        return next;
    }

    public Channel getChannel() {
        return channel;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean supports(Message message) {
        return message.getChannel() == this.getChannel();
    }

    @Override
    public Money costPerMessage() {
        return MoneyFactory.createGBP(getCost());
    }

    @Override
    public boolean isContactable(User user) {
        return user.isContactableOn(getChannel());
    }


}
