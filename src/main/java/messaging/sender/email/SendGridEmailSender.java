package messaging.sender.email;

import messaging.domain.Message;
import messaging.domain.User;
import messaging.enums.Channel;
import messaging.enums.Provider;
import messaging.enums.ResponseCode;
import messaging.facade.SendResult;
import messaging.sender.BaseSender;

import java.util.List;

public class SendGridEmailSender extends BaseSender {

    public SendGridEmailSender() {
        super(0.2, Channel.EMAIL);
    }

    @Override
    public SendResult send(User user, Message message, List<Channel> channelsTried){

        return new SendResult(
                ResponseCode.OK,
                message.getChannel(),
                message,
                Provider.SendGridEmailSender,
                costPerMessage(),
                channelsTried
        );
    }

}
