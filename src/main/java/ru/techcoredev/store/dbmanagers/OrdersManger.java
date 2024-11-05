package ru.techcoredev.store.dbmanagers;

import ru.techcoredev.store.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.dbconnect.DBType;
import ru.techcoredev.store.objects.Order;

import java.util.List;

public class OrdersManger {
    public static void createOrder(Order order) {
        DAOFactory.getInstance(DBType.POSTGRES).getOrdersDAO().createOrder(order);
    }

    public static List<Order> getOrders() {
        return DAOFactory.getInstance(DBType.POSTGRES).getOrdersDAO().getOrders();
    }

    public static void updateOrder(Order order) {
        DAOFactory.getInstance(DBType.POSTGRES).getOrdersDAO().updateOrder(order);
    }

    public static void deleteOrder(int orderId) {
        DAOFactory.getInstance(DBType.POSTGRES).getOrdersDAO().deleteOrder(orderId);
    }
}
