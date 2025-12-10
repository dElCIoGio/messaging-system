package main.java.messaging.validation;

import java.util.regex.Pattern;
import main.java.messaging.domain.EmailAddress;

public class EmailValidator implements IValidator<EmailAddress> {

    private static final String EMAIL_REGEX =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" +
                    "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public boolean validate(EmailAddress contactDetails) {
        if (contactDetails == null) {
            return false;
        }

        String email = contactDetails.getEmailAddress();
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

}
