package ru.techcoredev.store.dbconnect.hibernate;

import org.hibernate.Session;
import ru.techcoredev.store.dbconnect.DAOinterfeices.OrdersDAO;
import ru.techcoredev.store.objects.Order;

import java.util.List;

public class HibernateOrdersDAO implements OrdersDAO {
    @Override
    public void createOrder(Order order) {
        TransactionServices.executeInsideTransaction(session -> session.save(order));
    }

    @Override
    public void deleteOrder(Order order) {
        TransactionServices.executeInsideTransaction(session -> session.delete(order));
    }

    @Override
    public void updateOrder(Order order) {
        TransactionServices.executeInsideTransaction(session -> session.update(order));
    }

    @Override
    public List<Order> getOrders() {
        try (Session session = HibernateDAOFactory.getSession()) {
            return session.createQuery("From Order").list();
        }
    }
}
