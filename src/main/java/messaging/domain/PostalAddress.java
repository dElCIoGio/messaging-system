package main.java.messaging.domain;

import main.java.messaging.validation.PostcodeValidator;

public class PostalAddress implements ContactDetails {

    private String line1;
    private String line2;
    private String city;
    private String postalCode;
    private String country;

    public PostalAddress(String line1, String line2, String city, String postalCode, String country) {

        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    @Override
    public boolean validate() {
        PostcodeValidator postcodeValidator = new PostcodeValidator();
        return postcodeValidator.validate(this);
    }

    public String getPostalCode() {
        return postalCode;
    }

}
