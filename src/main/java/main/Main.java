package main.java.main;

import main.java.messaging.domain.*;
import main.java.messaging.enums.*;
import main.java.messaging.facade.MessagingFacade;
import main.java.messaging.facade.SendResult;
import main.java.messaging.factory.MessageSenderFactory;
import main.java.messaging.money.Money;
import main.java.messaging.routing.ChainBuilder;
import main.java.messaging.routing.RoutingPolicy;
import main.java.messaging.validation.ContactValidator;
import main.java.messaging.validation.EmailValidator;
import main.java.messaging.validation.PhoneValidator;
import main.java.messaging.validation.PostcodeValidator;

import java.util.List;

/**
 * Main class demonstrating the messaging system functionality.
 * Tests various scenarios including different urgency levels, message types, and validation.
 */
public class Main {

    public static void main(String[] args) {

        // 1. Validators (Single Responsibility)
        ContactValidator contactValidator = new ContactValidator(
                new EmailValidator(),
                new PhoneValidator(),
                new PostcodeValidator()
        );

        // 2. Factory + RoutingPolicy + Chain (Factory + Strategy + CoR)
        MessageSenderFactory factory = new MessageSenderFactory();
        RoutingPolicy routingPolicy = new RoutingPolicy();   // inner class below
        ChainBuilder chainBuilder = new ChainBuilder(factory, routingPolicy);

        // 3. Facade (single public entry point)
        MessagingFacade messaging = new MessagingFacade(chainBuilder, contactValidator);

        // 4. Domain: Users and Messages

        // Valid user
        User validUser = new User(
                "Delcio Agostinho",
                new EmailAddress("delcio@example.com"),
                new PostalAddress(
                        "221B Baker Street",
                        "",
                        "London",
                        "NW1 6XE",
                        "UK"
                ),
                new PhoneNumber("+447700900123")

                );

        // Invalid user (for validation demo)
        User invalidUser = new User(
                "Invalid User",
                new EmailAddress("not-an-email"),
                new PostalAddress(
                        "Unknown Street",
                        "",
                        "Nowhere",
                        "BAD_PC",
                        "UK"
                ),
                new PhoneNumber("12345")

                );

        // Messages
        Message otp = new SMSMessage(
                MessageType.OTP,
                "Login code",
                "Your one-time code is 492110",
                160
        );

        Message marketingEmail = new EmailMessage(
                MessageType.MARKETING,
                "Autumn Deals",
                "<h1>Save 20%</h1><p>Only today.</p>",
                true
        );

        Message welcomeLetter = new PostalMessage(
                MessageType.TRANSACTIONAL,
                "Welcome Pack",
                "Thanks for joining us — your card is enclosed.",
                "A4"
        );

        // 5. Run scenarios and print results (no util methods)

        // --- Scenario 1: OTP, CRITICAL ---
        System.out.println("=== OTP (CRITICAL) ===");
        SendResult r1 = messaging.sendMessage(validUser, otp, Urgency.CRITICAL);
        printResult(r1);

        // --- Scenario 2: Marketing, LOW ---
        System.out.println();
        System.out.println("=== Marketing (LOW) ===");
        SendResult r2 = messaging.sendMessage(validUser, marketingEmail, Urgency.LOW);
        printResult(r2);

        // --- Scenario 3: Transactional Letter, NORMAL ---
        System.out.println();
        System.out.println("=== Transactional Letter (NORMAL) ===");
        SendResult r3 = messaging.sendMessage(validUser, welcomeLetter, Urgency.NORMAL);
        printResult(r3);

        // --- Scenario 4: Invalid user (shows validation) ---
        System.out.println();
        System.out.println("=== Invalid User (any message) ===");
        SendResult r4 = messaging.sendMessage(invalidUser, marketingEmail, Urgency.NORMAL);
        printResult(r4);
    }

    public static void printResult(SendResult r) {

        ResponseCode code1 = r.getCode();
        String errorMessage1 = r.getErrorMessage();
        Message msg = r.getMessage();
        Provider provider = r.getProvider();
        List<Channel> tried = r.getChannelsTried();
        Money cost1 = r.getTotalCost();

        System.out.println("Code: " + code1.toString());
        if (errorMessage1 != null) {
            System.out.println("Error: " + errorMessage1);
        }
        System.out.println("Message: " + (msg == null || msg.getContent().isBlank() ? "-" : msg.getContent()));
        System.out.println("Channels tried: " + (tried == null || tried.isEmpty() ? "-" : tried));
        System.out.println("Provider used: " + (provider == null ? "-" : provider));
        if (cost1 != null) {
            System.out.println("Total cost: " + cost1.getAmount() + " " + cost1.getCurrency());
        } else {
            System.out.println("Total cost: -");
        }

    }

}
