package main.java.messaging.sender.postal;

import main.java.messaging.domain.Message;
import main.java.messaging.domain.User;
import main.java.messaging.enums.Channel;
import main.java.messaging.enums.Provider;
import main.java.messaging.enums.ResponseCode;
import main.java.messaging.facade.SendResult;
import main.java.messaging.sender.BaseSender;

import java.util.List;

public class RoyalMailPostalSender extends BaseSender {


    public RoyalMailPostalSender() {
        super(1, Channel.POSTAL);
    }

    @Override
    public SendResult send(User user, Message message, List<Channel> channelsTried) {
        return new SendResult(
                ResponseCode.OK,
                message.getChannel(),
                message,
                Provider.RoyalMailPostalSender,
                costPerMessage(),
                channelsTried
        );
    }
}
