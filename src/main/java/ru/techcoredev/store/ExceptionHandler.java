package ru.techcoredev.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class ExceptionHandler {
    private static final Logger logger = LogManager.getLogger(ExceptionHandler.class);

    public static void handleException(String text, Exception e) {
        String message = new StringBuilder(text).append(" :").append(e.getMessage()).toString();
        System.err.println(message);
        logger.error(message);
    }
}
