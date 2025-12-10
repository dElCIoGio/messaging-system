package messaging.enums;

public enum Provider {

    // SMS
    TwilioSMSSender, CSoftSMSSender,

    // EMAIL
    SendGridEmailSender, SESEmailSender,

    // POSTAL
    RoyalMailPostalSender, RNGPostalSender

}
