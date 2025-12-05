package main.java.messaging.validation;

import main.java.messaging.domain.User;
import main.java.messaging.exception.ValidationException;

public class ContactValidator {

    private EmailValidator emailValidator;
    private PhoneValidator phoneValidator;
    private PostcodeValidator postcodeValidator;

    public ContactValidator() {
        emailValidator = new EmailValidator();
        phoneValidator = new PhoneValidator();
        postcodeValidator = new PostcodeValidator();
    }

    public ContactValidator(EmailValidator emailValidator, PhoneValidator phoneValidator, PostcodeValidator postcodeValidator) {

        this.emailValidator = emailValidator;
        this.phoneValidator = phoneValidator;
        this.postcodeValidator = postcodeValidator;

    }

    public ValidationReport validate(User user) {
        ValidationReport report = new ValidationReport();

        if (!emailValidator.validate(user.getEmail())) {
            report.addError(new ValidationException("Invalid email address"));
        }

        if (!phoneValidator.validate(user.getPhoneNumber())) {
            report.addError(new ValidationException("Invalid phone number"));
        }

        if (!postcodeValidator.validate(user.getAddress())) {
            report.addError(new ValidationException("Invalid postcode"));
        }

        return report;

    }



}
