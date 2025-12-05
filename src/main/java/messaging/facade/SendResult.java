package main.java.messaging.facade;

import main.java.messaging.domain.Message;
import main.java.messaging.enums.Channel;
import main.java.messaging.enums.Provider;
import main.java.messaging.enums.ResponseCode;
import main.java.messaging.money.Money;

import java.util.ArrayList;
import java.util.List;


public class SendResult {

    private ResponseCode code;
    private Channel channel;
    private Message message;
    private Provider provider;
    private Money totalCost;
    private List<Channel> channelsTried;
    private String errorMessage;


    public SendResult(
            ResponseCode code,
            Channel channel,
            Message message,
            Provider provider,
            Money totalCost,
            List<Channel> channelsTried
    ) {
        this.code = code;
        this.message = message;
        this.provider = provider;
        this.totalCost = totalCost;
        this.channel = channel;
        this.channelsTried = channelsTried;
        this.errorMessage = null;
    }

    public SendResult(
            ResponseCode code,
            Channel channel,
            Message message,
            Provider provider,
            Money totalCost,
            List<Channel> channelsTried,
            String errorMessage
    ) {
        this.code = code;
        this.message = message;
        this.provider = provider;
        this.totalCost = totalCost;
        this.channel = channel;
        this.channelsTried = channelsTried;
        this.errorMessage = errorMessage;
    }

    public ResponseCode getCode() {
        return code;
    }

    public Message getMessage() {
        return message;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public Channel getChannel() {
        return channel;
    }

    public List<Channel> getChannelsTried() {
        return channelsTried;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
