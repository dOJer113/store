package ru.techcoredev.store.dbconnect.postgresqldb;

import ru.techcoredev.store.dbconnect.DAOinterfeices.ClientsDAO;
import ru.techcoredev.store.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.dbconnect.DAOinterfeices.UsersDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

public class PostgresDBDAOFactory extends DAOFactory {
    public static final String URL = "";
    public static final String USER = "alex";
    public static final String PASSWORD = "12345";
    private static volatile PostgresDBDAOFactory instance;
    private Connection connection;

    private PostgresDBDAOFactory() {
    }

    public static PostgresDBDAOFactory getInstance()
            throws ClassNotFoundException, SQLException {
        PostgresDBDAOFactory factory = instance;
        if (instance == null) {
            synchronized (PostgresDBDAOFactory.class) {
                instance = factory = new PostgresDBDAOFactory();
                factory.connected();
            }
        }
        return factory;
    }

    private void connected() throws SQLException {
        Locale.setDefault(Locale.ENGLISH);
        Properties props = new Properties();
        props.setProperty("user", PostgresDBDAOFactory.USER);
        props.setProperty("password", PostgresDBDAOFactory.PASSWORD);
        connection = DriverManager.getConnection(PostgresDBDAOFactory.URL, props);
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
