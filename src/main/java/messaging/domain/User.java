package main.java.messaging.domain;

import main.java.messaging.enums.Channel;
import main.java.messaging.utils.UUIDHandler;

public class User {

    private final String id;
    private String name;

    private EmailAddress email;
    private PostalAddress address;
    private PhoneNumber phoneNumber;

    public User(String name, EmailAddress email, PostalAddress address, PhoneNumber phoneNumber) {
        this.id = UUIDHandler.generateId();
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public void setEmail(EmailAddress email) {
        this.email = email;
    }

    public PostalAddress getAddress() {
        return address;
    }

    public void setAddress(PostalAddress address) {
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isContactableOn(Channel channel) {
        boolean contactable = false;
        switch (channel) {
            case EMAIL:
                if (email == null || email.getEmailAddress() == null){
                    break;
                }
                contactable = email.validate();
                break;

            case POSTAL:
                if (address == null || address.getPostalCode() == null){
                    break;
                }
                contactable = address.validate();
                break;

            case SMS:
                if (phoneNumber == null || phoneNumber.getPhoneNumber() == null){
                    break;
                }
                contactable = phoneNumber.validate();
        }

        return contactable;
    }


}
