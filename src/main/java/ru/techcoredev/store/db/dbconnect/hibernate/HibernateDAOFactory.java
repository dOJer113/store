package ru.techcoredev.store.db.dbconnect.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.db.dbconnect.DAOinterfeices.*;
import ru.techcoredev.store.objects.*;
import ru.techcoredev.store.objects.builders.Client;
import ru.techcoredev.store.objects.builders.User;

public class HibernateDAOFactory extends DAOFactory {
    private static volatile HibernateDAOFactory instance;
    private static SessionFactory sessionFactory;

    private HibernateDAOFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(ProductsInOrder.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            ExceptionHandler.handleException("Exception getting session factory for Hibernate", e);
        }
    }

    public static HibernateDAOFactory getInstance() {
        if (instance == null) {
            synchronized (HibernateDAOFactory.class) {
                if (instance == null) {
                    instance = new HibernateDAOFactory();
                }
            }
        }
        return instance;
    }

    public static Session getSession() {
        if (instance == null || sessionFactory == null) {
            throw new IllegalStateException("HibernateDAOFactory is not initialized. Call getInstance() first.");
        }
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
