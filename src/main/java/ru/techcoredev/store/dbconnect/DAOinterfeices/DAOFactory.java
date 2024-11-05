package ru.techcoredev.store.dbconnect.DAOinterfeices;


import ru.techcoredev.store.dbconnect.DBType;

public abstract class DAOFactory {
    public static DAOFactory getInstance(DBType dbType) {
        return dbType.getDAOFactory();
    }

    public abstract UsersDAO getUsersDAO();

    public abstract ClientsDAO getClientsDAO();
    public abstract OrdersDAO getOrdersDAO();
    public abstract ProductsDAO getProductDAO();
    public abstract RolesDAO getRolesDAO();
    public abstract StatusDAO getStatusDAO();

}
