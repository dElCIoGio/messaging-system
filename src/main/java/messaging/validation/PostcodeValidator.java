package main.java.messaging.validation;

import main.java.messaging.domain.PostalAddress;

public class PostcodeValidator implements IValidator<PostalAddress> {

    @Override
    public boolean validate(PostalAddress contactDetails) {
        String regexPattern = "^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|"
                + "(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|"
                + "(([A-Za-z][0-9][A-Za-z])|"
                + "([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z]))))"
                + "\\s?[0-9][A-Za-z]{2})$";

        String postcode = contactDetails.getPostalCode();
        return postcode != null && postcode.matches(regexPattern);
    }

}
