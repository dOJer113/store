package ru.techcoredev.store.db.dbmanagers;

import ru.techcoredev.store.db.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.db.dbconnect.DAOinterfeices.OrdersDAO;
import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.objects.*;
import ru.techcoredev.store.objects.builders.Client;
import ru.techcoredev.store.objects.builders.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class OrdersDBManager {
    private DBType dbType;

    public OrdersDBManager(DBType dbType) {
        this.dbType = dbType;
    }

    public void changeOrderStatus(int orderNumber, Status status) {
        DAOFactory.getInstance(dbType).getOrdersDAO().changeOrderStatus(orderNumber,status);
    }

    public void createOrder(Order order) {
        DAOFactory.getInstance(dbType).getOrdersDAO().createOrder(order);
    }

    public List<Order> getOrders() {
        return DAOFactory.getInstance(dbType).getOrdersDAO().getOrders();
    }

    public List<Order> getOrdersByUserId(int userId) {
        return DAOFactory.getInstance(dbType).getOrdersDAO().getOrdersByUserID(userId);
    }

    public void updateOrder(Order order) {
        DAOFactory.getInstance(dbType).getOrdersDAO().updateOrder(order);
    }

    public void deleteOrder(Order order) {
        DAOFactory.getInstance(dbType).getOrdersDAO().deleteOrder(order);
    }

    public void deleteOrdersBeforeDate(LocalDate localDate) {
        DAOFactory.getInstance(dbType).getOrdersDAO().deleteOrdersBeforeDate(localDate);
    }

    public OrderDetails getAllInfoAboutOrder(int number) {
        DAOFactory daoFactory = DAOFactory.getInstance(dbType);
        OrdersDAO ordersDAO = daoFactory.getOrdersDAO();
        Order order = ordersDAO.getOrderByNumber(number);
        List<ProductsInOrder> productsOrder = ordersDAO.getProductsByOrderNumber(number);
        List<Product> products = new ArrayList<>();
        for (ProductsInOrder productsInOrder : productsOrder) {
            Product product = daoFactory.getProductDAO().getProductById(productsInOrder.getProductId());
            product.setRemains(productsInOrder.getCountProduct());
            products.add(product);
        }
        Client client = daoFactory.getClientsDAO().getClientById(order.getUserId());
        return new OrderDetails(number, order.getRegistrationDate(), order.getClosingDate(), order.getStatus(), client.getName(), client.getSurname(), client.getPhoneNumber(), client.getAddress(), products);
    }
}
