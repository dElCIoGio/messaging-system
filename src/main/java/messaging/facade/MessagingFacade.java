package messaging.facade;

import messaging.domain.Message;
import messaging.domain.User;
import messaging.enums.Channel;
import messaging.enums.Provider;
import messaging.enums.ResponseCode;
import messaging.enums.Urgency;
import messaging.routing.ChainBuilder;
import messaging.sender.IMessageSender;
import messaging.validation.ContactValidator;
import messaging.validation.ValidationReport;

import java.util.ArrayList;
import java.util.List;

public class MessagingFacade {

    private ChainBuilder chainBuilder;
    private ContactValidator validator;

    public MessagingFacade(ChainBuilder chainBuilder, ContactValidator validator) {
        this.chainBuilder = chainBuilder;
        this.validator = validator;
    }

    public SendResult sendMessage(User user, Message message, Urgency urgency) {

        List<Channel> channelsTried = new ArrayList<Channel>();

        ValidationReport validationReport = validator.validate(user);
        if (!validationReport.isValid()) {
            return new SendResult(
                    ResponseCode.INVALID_CONTACT,
                    message.getChannel(),
                    message,
                    null,
                    null,
                    channelsTried,
                    validationReport.printErrors()
            );
        }

        IMessageSender sender = chainBuilder.buildChain(message, urgency);

        SendResult aux = null;

        for (IMessageSender s = sender; s != null; s = s.getNext()){

            SendResult result = s.send(user, message, channelsTried);

            channelsTried.add(result.getChannel());

            if (result.getCode() == ResponseCode.OK){
                return result;
            }

            aux = result;
        }

        return aux;
    }



}
