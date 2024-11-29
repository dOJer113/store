package ru.techcoredev.store.db.dbmanagers;

import ru.techcoredev.store.db.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.objects.Order;

import java.util.List;

public class OrdersManger {
    private DBType dbType;

    public OrdersManger(DBType dbType) {
        this.dbType = dbType;
    }

    public void createOrder(Order order) {
        DAOFactory.getInstance(dbType).getOrdersDAO().createOrder(order);
    }

    public List<Order> getOrders() {
        return DAOFactory.getInstance(dbType).getOrdersDAO().getOrders();
    }

    public void updateOrder(Order order) {
        DAOFactory.getInstance(dbType).getOrdersDAO().updateOrder(order);
    }

    public void deleteOrder(Order order) {
        DAOFactory.getInstance(dbType).getOrdersDAO().deleteOrder(order);
    }
}
