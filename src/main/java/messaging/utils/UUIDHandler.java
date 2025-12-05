package main.java.messaging.utils;

import java.util.UUID;

public class UUIDHandler {

    public static String generateId() {

        return UUID.randomUUID().toString();

    }


    public static boolean validateId(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (Exception e) {
            return false;
        }


    }



}
