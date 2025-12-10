package messaging.domain;

import messaging.validation.EmailValidator;

public class EmailAddress implements ContactDetails {

    private String emailAddress;

    public EmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean validate() {
        EmailValidator emailValidator = new EmailValidator();
        return emailValidator.validate(this);
    }
}
