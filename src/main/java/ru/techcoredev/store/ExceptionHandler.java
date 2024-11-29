package ru.techcoredev.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.techcoredev.store.db.dbconnect.pool.ConnectionPool;

public class ExceptionHandler {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    public static void handleException(String text, Exception e) {
        String message = new StringBuilder(text).append(" :").append(e.getMessage()).toString();
        System.err.println(message);
        logger.error(message);
    }
}
