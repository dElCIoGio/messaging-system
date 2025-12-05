package main.java.messaging.sender.postal;

import main.java.messaging.domain.Message;
import main.java.messaging.domain.User;
import main.java.messaging.enums.Channel;
import main.java.messaging.enums.Provider;
import main.java.messaging.enums.ResponseCode;
import main.java.messaging.facade.SendResult;
import main.java.messaging.sender.BaseSender;

import java.util.List;
import java.util.Random;



public class RNGPostalSender extends BaseSender {

    private static final double FAILURE_RATE = 0.10;

    public RNGPostalSender() {
        super(1, Channel.POSTAL);
    }


    @Override
    public SendResult send(User user, Message message, List<Channel> channelsTried) {

        ResponseCode code = ResponseCode.OK;
        Random rng = new Random();

        if (rng.nextDouble() < FAILURE_RATE) {
            return new SendResult(
                    ResponseCode.FAILED,
                    message.getChannel(),
                    message,
                    Provider.RNGPostalSender,
                    costPerMessage(),
                    channelsTried
            );
        }

        if (!isContactable(user)){
            return new SendResult(
                    ResponseCode.INVALID_CONTACT,
                    message.getChannel(),
                    message,
                    Provider.RNGPostalSender,
                    costPerMessage(),
                    channelsTried
            );
        }

        return new SendResult(
                code,
                message.getChannel(),
                message,
                Provider.RNGPostalSender,
                costPerMessage(),
                channelsTried
        );
    }


}
