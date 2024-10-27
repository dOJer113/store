package ru.techcoredev.store.dbconnect.postgresqldb;

import ru.techcoredev.store.dbconnect.DAOinterfeices.ClientsDAO;
import ru.techcoredev.store.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.dbconnect.DAOinterfeices.UsersDAO;
import ru.techcoredev.store.dbconnect.pool.ConnectionPool;

import java.sql.Connection;


public class PostgresDBDAOFactory extends DAOFactory {
    public static final int FIRST_INDEX = 1;
    public static final int SECOND_INDEX = 2;
    public static final int THIRD_INDEX = 3;
    public static final int FOURTH_INDEX = 4;
    public static final int FIFTH_INDEX = 5;
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
        this.connection = ConnectionPool.getInstance().retrieve();
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
