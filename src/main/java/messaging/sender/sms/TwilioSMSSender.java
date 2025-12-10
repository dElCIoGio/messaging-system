package messaging.sender.sms;

import messaging.domain.Message;
import messaging.domain.User;
import messaging.enums.Channel;
import messaging.enums.Provider;
import messaging.enums.ResponseCode;
import messaging.facade.SendResult;
import messaging.sender.BaseSender;

import java.util.List;


public class TwilioSMSSender extends BaseSender {


    public TwilioSMSSender() {
        super(.5, Channel.SMS);
    }

    @Override
    public SendResult send(User user, Message message, List<Channel> channelsTried) {

        ResponseCode code = ResponseCode.OK;

        if (!isContactable(user)){
            code = ResponseCode.INVALID_CONTACT;
        }

        return new SendResult(
                code,
                message.getChannel(),
                message,
                Provider.TwilioSMSSender,
                costPerMessage(),
                channelsTried
        );
    }

}
