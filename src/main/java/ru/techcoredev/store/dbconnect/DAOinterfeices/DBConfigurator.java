package ru.techcoredev.store.dbconnect.DAOinterfeices;

import ru.techcoredev.store.ExceptionHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfigurator {
    private DBConfigurator() {

    }

    public static Properties getProperties(String filePath) {
        Properties properties = new Properties();
        try (InputStream input = DBConfigurator.class.getClassLoader().getResourceAsStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            ExceptionHandler.handleException(new StringBuilder("Exception getting file properties for DB").append(filePath).toString(), e);
        }
        return properties;
    }
}
