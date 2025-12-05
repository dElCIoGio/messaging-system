package main.java.messaging.validation;


import main.java.messaging.domain.ContactDetails;
import main.java.messaging.domain.EmailAddress;

public class EmailValidator implements IValidator<EmailAddress> {

    @Override
    public boolean validate(EmailAddress contactDetails) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return contactDetails.getEmailAddress().matches(regexPattern);
    }

}
