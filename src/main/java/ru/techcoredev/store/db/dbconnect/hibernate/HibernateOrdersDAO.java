package ru.techcoredev.store.db.dbconnect.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.db.dbconnect.DAOinterfeices.OrdersDAO;
import ru.techcoredev.store.objects.Order;
import ru.techcoredev.store.objects.ProductsInOrder;
import ru.techcoredev.store.objects.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateOrdersDAO implements OrdersDAO {
    private static final Logger logger = LogManager.getLogger(HibernateOrdersDAO.class);
    private static final String SELECT_QUERY = "From Order";
    private static final String SELECT_BY_USER_ID = "FROM Order WHERE userId = :userId";
    private static final String SELECT_BY_NUMBER = "FROM Order WHERE number = :number";
    private static final String SELECT_PRODUCTS_BY_NUMBER = "FROM ProductsInOrder WHERE orderNumber = :number";
    private static final String SELECT_ORDERS_NUMBERS_BEFORE_DATE = "FROM Order WHERE  registrationDate < :date";
    private static final String DELETE_ORDERS_BEFORE_DATE = "DELETE FROM Order o WHERE registrationDate < :date";
    private static final String DELETE_PRODUCTS_IN_ORDERS_BY_ORDER_NUMBER = "DELETE FROM ProductsInOrder WHERE orderNumber IN (:orderNumbers)";

    @Override
    public void changeOrderStatus(int orderNumber, Status status) {
        logger.debug("Updating order status to " + status + " for order number: " + orderNumber);
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, orderNumber);
            if (order == null) {
                logger.error("Order with number " + orderNumber + " not found");
            }
            order.setStatus(status);
            session.update(order);
            transaction.commit();
            logger.debug("Order status was updated to " + status + " for order number: " + orderNumber);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception updating order status", e);
        }

    }

    @Override
    public void deleteOrdersBeforeDate(LocalDate localDate) {
        logger.debug("Deleting orders before date: " + localDate);
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            List<Order> ordersBeforeDate = this.getOrdersBeforeDate(localDate);
            List<Integer> orderNumbers = ordersBeforeDate.stream()
                    .map(Order::getNumber)
                    .collect(Collectors.toList());
            if (!orderNumbers.isEmpty()) {
                Query deleteProductsInOrdersQuery = session.createQuery(DELETE_PRODUCTS_IN_ORDERS_BY_ORDER_NUMBER);
                deleteProductsInOrdersQuery.setParameter("orderNumbers", orderNumbers);
                deleteProductsInOrdersQuery.executeUpdate();
            }
            Query query = session.createQuery(DELETE_ORDERS_BEFORE_DATE);
            query.setParameter("date", localDate);
            query.executeUpdate();
            transaction.commit();
            logger.debug("Orders was deleted");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception deleting order ", e);
        }
    }

    @Override
    public List<Order> getOrdersBeforeDate(LocalDate localDate) {
        try (Session session = HibernateDAOFactory.getSession()) {
            Query<Order> query = session.createQuery(SELECT_ORDERS_NUMBERS_BEFORE_DATE, Order.class);
            query.setParameter("date", localDate);
            return query.getResultList();
        }
    }

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

    @Override
    public List<Order> getOrdersByUserID(int userId) {
        try (Session session = HibernateDAOFactory.getSession()) {
            Query<Order> query = session.createQuery(SELECT_BY_USER_ID, Order.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        }
    }

    @Override
    public Order getOrderByNumber(int number) {
        try (Session session = HibernateDAOFactory.getSession()) {
            Query<Order> query = session.createQuery(SELECT_BY_NUMBER, Order.class);
            query.setParameter("number", number);
            return query.getResultList().get(0);
        }
    }

    @Override
    public List<ProductsInOrder> getProductsByOrderNumber(int number) {
        try (Session session = HibernateDAOFactory.getSession()) {
            Query<ProductsInOrder> query = session.createQuery(SELECT_PRODUCTS_BY_NUMBER, ProductsInOrder.class);
            query.setParameter("number", number);
            return query.getResultList();
        }
    }
}
