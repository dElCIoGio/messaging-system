package messaging.routing;

import messaging.domain.Message;
import messaging.enums.Provider;
import messaging.enums.Urgency;
import messaging.factory.MessageSenderFactory;
import messaging.sender.IMessageSender;

import java.util.List;

public class ChainBuilder {

    private final MessageSenderFactory factory;
    private final RoutingPolicy policy;

    public ChainBuilder(MessageSenderFactory factory, RoutingPolicy policy) {
        this.factory = factory;
        this.policy = policy;
    }

    public IMessageSender buildChain(Message message, Urgency urgency) {

        List<Provider> order = policy.orderFor(message, urgency);

        IMessageSender prev = null;

        for (int i = order.size() - 1; i >= 0; i--) {

            IMessageSender sender = factory.createSender(order.get(i));

            if (prev != null) {
                sender.setNext(prev);
            }

            prev = sender;
        }

        return prev;
    }


}
