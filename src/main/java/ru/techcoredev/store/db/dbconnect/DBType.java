package ru.techcoredev.store.db.dbconnect;


import ru.techcoredev.store.db.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.db.dbconnect.postgresqldb.PostgresDBDAOFactory;
import ru.techcoredev.store.db.dbconnect.hibernate.HibernateDAOFactory;

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
