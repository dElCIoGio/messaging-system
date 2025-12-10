package main.java.messaging.validation;

import java.util.regex.Pattern;
import main.java.messaging.domain.PhoneNumber;

public class PhoneValidator implements IValidator<PhoneNumber> {

    private static final Pattern UK_MOBILE_PATTERN = Pattern.compile("^0\\d{10}$");

    @Override
    public boolean validate(PhoneNumber contactDetails) {
        if (contactDetails == null) {
            return false;
        }

        String phoneNumber = contactDetails.getPhoneNumber();
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }

        String normalized = normalize(phoneNumber);
        return matchesUkFormat(normalized);
    }

    private String normalize(String raw) {
        String trimmed = raw.trim();
        String digitsOnly = removeNonLeadingPlus(trimmed);
        return convertInternationalPrefix(trimmed, digitsOnly);
    }

    private String removeNonLeadingPlus(String value) {
        return value.replaceAll("(?!^)[^\\d]", "");
    }

    private String convertInternationalPrefix(String original, String normalized) {
        if (original.startsWith("+44") && normalized.length() >= 3) {
            return "0" + normalized.substring(3);
        }
        return normalized;
    }

    private boolean matchesUkFormat(String normalized) {
        return UK_MOBILE_PATTERN.matcher(normalized).matches();
    }
}
