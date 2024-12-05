package ru.techcoredev.store.db.dbconnect.DAOinterfeices;

import ru.techcoredev.store.objects.Order;
import ru.techcoredev.store.objects.ProductsInOrder;

import java.util.List;

public interface OrdersDAO {
    void createOrder(Order order);

    void deleteOrder(Order order);

    List<Order> getOrders();

    List<Order> getOrdersByUserID(int userId);

    Order getOrderByNumber(int number);

    List<ProductsInOrder> getProductsByOrderNumber(int number);

    void updateOrder(Order order);

}
