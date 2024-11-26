package ru.techcoredev.store.dbconnect.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.dbconnect.DAOinterfeices.*;
import ru.techcoredev.store.objects.*;

public class HibernateDAOFactory extends DAOFactory {
    private static SessionFactory sessionFactory;

    public static HibernateDAOFactory getInstance() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(ProductsInOrder.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                ExceptionHandler.handleException("Exception getting session factory for hibernate", e);
            }
        }
        return new HibernateDAOFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public UsersDAO getUsersDAO() {
        return new HibernateUserDao();
    }

    @Override
    public ClientsDAO getClientsDAO() {
        return new HibernateClientsDAO();
    }

    @Override
    public OrdersDAO getOrdersDAO() {
        return new HibernateOrdersDAO();
    }

    @Override
    public ProductsDAO getProductDAO() {
        return new HibernateProductsDAO();
    }


}
