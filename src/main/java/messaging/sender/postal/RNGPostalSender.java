package messaging.sender.postal;

import messaging.domain.Message;
import messaging.domain.User;
import messaging.enums.Channel;
import messaging.enums.Provider;
import messaging.enums.ResponseCode;
import messaging.facade.SendResult;
import messaging.sender.BaseSender;

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
