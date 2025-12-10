package messaging.validation;

import messaging.domain.User;
import messaging.exception.ValidationException;

public class ContactValidator {

    private final EmailValidator emailValidator;
    private final PhoneValidator phoneValidator;
    private final PostcodeValidator postcodeValidator;

    public ContactValidator() {
        this(new EmailValidator(), new PhoneValidator(), new PostcodeValidator());
    }

    public ContactValidator(EmailValidator emailValidator, PhoneValidator phoneValidator, PostcodeValidator postcodeValidator) {
        this.emailValidator = emailValidator;
        this.phoneValidator = phoneValidator;
        this.postcodeValidator = postcodeValidator;
    }

    public ValidationReport validate(User user) {
        ValidationReport report = new ValidationReport();

        validateEmail(user, report);
        validatePhone(user, report);
        validatePostcode(user, report);

        return report;
    }

    private void validateEmail(User user, ValidationReport report) {
        if (!emailValidator.validate(user.getEmail())) {
            report.addError(new ValidationException("Invalid email address"));
        }
    }

    private void validatePhone(User user, ValidationReport report) {
        if (!phoneValidator.validate(user.getPhoneNumber())) {
            report.addError(new ValidationException("Invalid phone number"));
        }
    }

    private void validatePostcode(User user, ValidationReport report) {
        if (!postcodeValidator.validate(user.getAddress())) {
            report.addError(new ValidationException("Invalid postcode"));
        }
    }
}
