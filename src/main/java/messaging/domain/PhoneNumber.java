package main.java.messaging.domain;

import main.java.messaging.validation.PhoneValidator;

public class PhoneNumber implements ContactDetails {

    private String value;

    public PhoneNumber(String value) {
        this.value = value;
    }

    public String getPhoneNumber() {
        return value;
    }

    public void setPhoneNumber(String value) {
        this.value = value;
    }

    @Override
    public boolean validate() {
        PhoneValidator phoneValidator = new PhoneValidator();
        return phoneValidator.validate(this);
    }
}
