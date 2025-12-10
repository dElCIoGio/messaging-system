package messaging.sender.postal;

import messaging.domain.Message;
import messaging.domain.User;
import messaging.enums.Channel;
import messaging.enums.Provider;
import messaging.enums.ResponseCode;
import messaging.facade.SendResult;
import messaging.sender.BaseSender;

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
