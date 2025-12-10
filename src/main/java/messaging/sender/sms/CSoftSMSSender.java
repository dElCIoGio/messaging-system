package messaging.sender.sms;

import messaging.domain.Message;
import messaging.domain.User;
import messaging.enums.Channel;
import messaging.enums.Provider;
import messaging.enums.ResponseCode;
import messaging.facade.SendResult;
import messaging.money.Money;
import messaging.sender.BaseSender;

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
