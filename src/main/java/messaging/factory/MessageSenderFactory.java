package messaging.factory;

import messaging.enums.Provider;
import messaging.sender.IMessageSender;
import messaging.sender.email.SESEmailSender;
import messaging.sender.email.SendGridEmailSender;
import messaging.sender.postal.RNGPostalSender;
import messaging.sender.postal.RoyalMailPostalSender;
import messaging.sender.sms.CSoftSMSSender;
import messaging.sender.sms.TwilioSMSSender;

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
