package ru.techcoredev.store.dbconnect;


import ru.techcoredev.store.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.dbconnect.hibernate.HibernateDAOFactory;
import ru.techcoredev.store.dbconnect.postgresqldb.PostgresDBDAOFactory;

public enum DBType {
    POSTGRES {
        @Override
        public DAOFactory getDAOFactory() {
            return PostgresDBDAOFactory.getInstance();
        }
    },
    HIBERNATE_POSTGRES {
        @Override
        public DAOFactory getDAOFactory() {
            return HibernateDAOFactory.getInstance();
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
