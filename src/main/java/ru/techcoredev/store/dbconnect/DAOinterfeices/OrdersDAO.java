package ru.techcoredev.store.dbconnect.DAOinterfeices;

import ru.techcoredev.store.objects.Order;

import java.util.List;

public interface OrdersDAO {
    void createOrder(Order order);

    void deleteOrder(int orderId);

    List<Order> getOrders();

    void updateOrder(Order order);

}