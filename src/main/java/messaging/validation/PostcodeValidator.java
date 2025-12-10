package messaging.validation;

import java.util.regex.Pattern;
import messaging.domain.PostalAddress;

public class PostcodeValidator implements IValidator<PostalAddress> {

    private static final String POSTCODE_REGEX =
            "^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|" +
                    "(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|" +
                    "(([A-Za-z][0-9][A-Za-z])|" +
                    "([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z]))))" +
                    "\\s?[0-9][A-Za-z]{2})$";

    private static final Pattern POSTCODE_PATTERN = Pattern.compile(POSTCODE_REGEX);

    @Override
    public boolean validate(PostalAddress contactDetails) {
        if (contactDetails == null) {
            return false;
        }

        String postcode = contactDetails.getPostalCode();
        if (postcode == null) {
            return false;
        }

        return matchesPostcodePattern(postcode.trim());
    }

    private boolean matchesPostcodePattern(String postcode) {
        return POSTCODE_PATTERN.matcher(postcode).matches();
    }
}
