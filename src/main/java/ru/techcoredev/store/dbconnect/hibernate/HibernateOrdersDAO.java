package ru.techcoredev.store.dbconnect.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.dbconnect.DAOinterfeices.OrdersDAO;
import ru.techcoredev.store.dbconnect.pool.ConnectionPool;
import ru.techcoredev.store.objects.Order;

import java.util.List;

public class HibernateOrdersDAO implements OrdersDAO {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final String SELECT_QUERY = "From Order";

    @Override
    public void createOrder(Order order) {
        logger.debug("Creating order with number: " + order.getNumber());
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            logger.debug("Order added");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception creating order ", e);
        }
    }

    @Override
    public void deleteOrder(Order order) {
        logger.debug("Deleting order with number: " + order.getNumber());
        if (order.getNumber() == 0) {
            throw new RuntimeException();
        }
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
            logger.debug("Order was deleted");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception deleting order ", e);
        }
    }

    @Override
    public void updateOrder(Order order) {
        logger.debug("Updating order with number: " + order.getNumber());
        if (order.getNumber() == 0) {
            throw new RuntimeException();
        }
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
            logger.debug("Order was updated");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception updating order ", e);
        }
    }

    @Override
    public List<Order> getOrders() {
        try (Session session = HibernateDAOFactory.getSession()) {
            return session.createQuery(SELECT_QUERY).list();
        }
    }
}
