package main.java.messaging.routing;

import main.java.messaging.domain.Message;
import main.java.messaging.enums.MessageType;
import main.java.messaging.enums.Provider;
import main.java.messaging.enums.Urgency;

import java.util.ArrayList;
import java.util.List;

interface DefaultProviders {

    List<Provider> setDefaultChain(List<Provider> p);

}

public class RoutingPolicy {

    public List<Provider> orderFor(Message message, Urgency urgency) {

        List<Provider> providers = new ArrayList<Provider>();
        MessageType messageType = message.getType();

        DefaultProviders addDefault = (p) -> {
            p.add(Provider.SendGridEmailSender);
            p.add(Provider.SESEmailSender);
            p.add(Provider.TwilioSMSSender);
            return p;
        };

        switch (urgency) {
            case LOW:
                providers.add(Provider.SESEmailSender);
                providers.add(Provider.SendGridEmailSender);
                break;
            case NORMAL:
                if (messageType == MessageType.OTP){
                    providers.add(Provider.TwilioSMSSender);
                    providers.add(Provider.CSoftSMSSender);
                    providers.add(Provider.SendGridEmailSender);
                } else if (messageType == MessageType.TRANSACTIONAL){
                    providers.add(Provider.RoyalMailPostalSender);
                    providers.add(Provider.SendGridEmailSender);
                    providers.add(Provider.TwilioSMSSender);
                } else if (messageType == MessageType.MARKETING){
                    providers = addDefault.setDefaultChain(providers);
                } else if (messageType == MessageType.GENERIC){
                    providers = addDefault.setDefaultChain(providers);
                } else {
                    providers = addDefault.setDefaultChain(providers);
                }
                break;
            case HIGH:
                providers.add(Provider.TwilioSMSSender);
                providers.add(Provider.SendGridEmailSender);
                providers.add(Provider.SESEmailSender);
                break;
            case CRITICAL:
                providers.add(Provider.TwilioSMSSender);
                providers.add(Provider.CSoftSMSSender);
                providers.add(Provider.SESEmailSender);
                providers.add(Provider.SendGridEmailSender);
                break;
        }

        return providers;
    }

}
