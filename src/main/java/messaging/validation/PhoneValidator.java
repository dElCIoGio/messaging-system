package main.java.messaging.validation;
import main.java.messaging.domain.PhoneNumber;

public class PhoneValidator implements IValidator<PhoneNumber> {
    @Override
    public boolean validate(PhoneNumber contactDetails) {
        String phoneNumber = contactDetails.getPhoneNumber();
        String raw = phoneNumber.trim();
        String normalized = raw.replaceAll("(?!^)[^\\d]", "");

        if (raw.startsWith("+44")) {
            normalized = "0" + normalized.substring(3);
        }

        return normalized.matches("^0\\d{10}$");
    }
}
