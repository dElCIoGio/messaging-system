package main.java.messaging.sender.sms;

import main.java.messaging.domain.Message;
import main.java.messaging.domain.User;
import main.java.messaging.enums.Channel;
import main.java.messaging.enums.Provider;
import main.java.messaging.enums.ResponseCode;
import main.java.messaging.facade.SendResult;
import main.java.messaging.money.Money;
import main.java.messaging.sender.BaseSender;

import java.util.List;

public class CSoftSMSSender extends BaseSender {


    public CSoftSMSSender() {
        super(0.3, Channel.SMS);
    }

    @Override
    public SendResult send(User user, Message message, List<Channel> channelsTried) {
        
        return new SendResult(
                ResponseCode.OK,
                message.getChannel(),
                message,
                Provider.CSoftSMSSender,
                costPerMessage(),
                channelsTried
        );
    }
}
