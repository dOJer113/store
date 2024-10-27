package ru.techcoredev.store.dbconnect.postgresqldb;

import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.dbconnect.DAOinterfeices.ClientsDAO;
import ru.techcoredev.store.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.dbconnect.DAOinterfeices.DBConfigurator;
import ru.techcoredev.store.dbconnect.DAOinterfeices.UsersDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresDBDAOFactory extends DAOFactory {
    public static final int FIRST_INDEX = 1;
    public static final int SECOND_INDEX = 2;
    public static final int THIRD_INDEX = 3;
    public static final int FOURTH_INDEX = 4;
    public static final int FIFTH_INDEX = 5;
    public static final String FILE_PATH_PROPERTIES = "application.properties";

    private static volatile PostgresDBDAOFactory instance;
    private Connection connection;

    private PostgresDBDAOFactory() {
    }

    public static PostgresDBDAOFactory getInstance() {
        PostgresDBDAOFactory factory = instance;
        if (instance == null) {
            synchronized (PostgresDBDAOFactory.class) {
                instance = factory = new PostgresDBDAOFactory();
                factory.connected();
            }
        }
        return factory;
    }

    private void connected() {
        Properties props = DBConfigurator.getProperties(FILE_PATH_PROPERTIES);
        try {
            connection = DriverManager.getConnection(props.getProperty("db.url"), props);
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception connecting DB", e);
        }
    }

    @Override
    public UsersDAO getUsersDAO() {
        return new PostgresUsersDAO(connection);
    }

    @Override
    public ClientsDAO getClientsDAO() {
        return new PostgresClientsDAO(connection);
    }
}
