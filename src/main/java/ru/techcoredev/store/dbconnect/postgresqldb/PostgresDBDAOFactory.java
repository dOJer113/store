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
    public static final String URL = "jdbc:postgresql://localhost:5432/store";
    public static final String USER = "postgres";
    public static final String PASSWORD = "12345";
    public static final int FIRST_INDEX = 1;
    public static final int SECOND_INDEX = 2;
    public static final int THIRD_INDEX = 3;
    public static final int FOURTH_INDEX = 4;
    public static final int FIFTH_INDEX = 5;

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
