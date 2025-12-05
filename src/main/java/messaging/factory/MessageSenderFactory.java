package main.java.messaging.factory;

import main.java.messaging.enums.Provider;
import main.java.messaging.sender.IMessageSender;
import main.java.messaging.sender.email.SESEmailSender;
import main.java.messaging.sender.email.SendGridEmailSender;
import main.java.messaging.sender.postal.RNGPostalSender;
import main.java.messaging.sender.postal.RoyalMailPostalSender;
import main.java.messaging.sender.sms.CSoftSMSSender;
import main.java.messaging.sender.sms.TwilioSMSSender;

public class MessageSenderFactory {

    public IMessageSender createSender(Provider provider) {

        return switch (provider) {
            case TwilioSMSSender -> new TwilioSMSSender();
            case CSoftSMSSender -> new CSoftSMSSender();
            case RNGPostalSender -> new RNGPostalSender();
            case RoyalMailPostalSender -> new RoyalMailPostalSender();
            case SESEmailSender -> new SESEmailSender();
            case SendGridEmailSender -> new SendGridEmailSender();
            default -> null;
        };

    }

}
