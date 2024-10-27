package ru.techcoredev.store.dbconnect;


import ru.techcoredev.store.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.dbconnect.postgresqldb.PostgresDBDAOFactory;

import java.sql.SQLException;

public enum DBType {
    POSTGRES {
        @Override
        public DAOFactory getDAOFactory() {
            return PostgresDBDAOFactory.getInstance();
        }
    };

    public static DBType getTypeByName(String dbType) {
        try {
            return DBType.valueOf(dbType.toUpperCase());
        } catch (Exception e) {
            throw new DBTypeException();
        }
    }

    public abstract DAOFactory getDAOFactory();

}
