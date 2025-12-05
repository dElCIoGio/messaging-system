package main.java.messaging.facade;

import main.java.messaging.domain.Message;
import main.java.messaging.domain.User;
import main.java.messaging.enums.Channel;
import main.java.messaging.enums.Provider;
import main.java.messaging.enums.ResponseCode;
import main.java.messaging.enums.Urgency;
import main.java.messaging.routing.ChainBuilder;
import main.java.messaging.sender.IMessageSender;
import main.java.messaging.validation.ContactValidator;
import main.java.messaging.validation.ValidationReport;

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
