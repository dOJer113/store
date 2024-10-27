package ru.techcoredev.store;

public class ExceptionHandler {
    public static void handleException(String text, Exception e) {
        System.err.println(new StringBuilder(text).append(" :").append(e.getMessage()));
    }
}
