package main.java.messaging.sender.sms;

import main.java.messaging.domain.Message;
import main.java.messaging.domain.User;
import main.java.messaging.enums.Channel;
import main.java.messaging.enums.Provider;
import main.java.messaging.enums.ResponseCode;
import main.java.messaging.facade.SendResult;
import main.java.messaging.sender.BaseSender;

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
