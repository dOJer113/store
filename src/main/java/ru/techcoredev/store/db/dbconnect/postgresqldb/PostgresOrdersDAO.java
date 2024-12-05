package ru.techcoredev.store.db.dbconnect.postgresqldb;

import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.db.dbconnect.DAOinterfeices.OrdersDAO;
import ru.techcoredev.store.objects.Order;
import ru.techcoredev.store.objects.ProductsInOrder;
import ru.techcoredev.store.objects.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresOrdersDAO implements OrdersDAO {
    private static final String INSERT_QUERY = "insert into orders (userid, registrationdate, closingdate, status) VALUES (?,?,?,?)";
    private static final String DELETE_QUERY = "delete from orders where number = ?";
    private static final String UPDATE_QUERY = "update orders set userid = ?, registrationdate = ?, closingdate = ?, status = ? where number = ?";
    private static final String SELECT_QUERY = "select * from orders";
    private final Connection connection;

    public PostgresOrdersDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createOrder(Order order) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(PostgresDBDAOFactory.FIRST_INDEX, order.getUserId());
            statement.setDate(PostgresDBDAOFactory.SECOND_INDEX, order.getRegistrationDate());
            statement.setDate(PostgresDBDAOFactory.THIRD_INDEX, order.getClosingDate());
            statement.setString(PostgresDBDAOFactory.FOURTH_INDEX, order.getStatus().toString().toLowerCase());
            statement.executeUpdate();
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception inserting order into db", e);
        }
    }

    @Override
    public void deleteOrder(Order order) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(PostgresDBDAOFactory.FIRST_INDEX, order.getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception deleting order from db", e);
        }
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order(resultSet.getInt(PostgresDBDAOFactory.FIRST_INDEX),
                        resultSet.getInt(PostgresDBDAOFactory.SECOND_INDEX),
                        resultSet.getDate(PostgresDBDAOFactory.THIRD_INDEX),
                        resultSet.getDate(PostgresDBDAOFactory.FOURTH_INDEX),
                        Status.valueOf(resultSet.getString(PostgresDBDAOFactory.FIFTH_INDEX).toUpperCase()));
                orders.add(order);
            }
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception getting orders from db", e);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByUserID(int userId) {
        return null;
    }

    @Override
    public Order getOrderByNumber(int number) {
        return null;
    }

    @Override
    public List<ProductsInOrder> getProductsByOrderNumber(int number) {
        return null;
    }

    @Override
    public void updateOrder(Order order) {
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(PostgresDBDAOFactory.FIRST_INDEX, order.getUserId());
            statement.setDate(PostgresDBDAOFactory.SECOND_INDEX, order.getRegistrationDate());
            statement.setDate(PostgresDBDAOFactory.THIRD_INDEX, order.getClosingDate());
            statement.setString(PostgresDBDAOFactory.FOURTH_INDEX, order.getStatus().toString().toLowerCase());
            statement.setInt(PostgresDBDAOFactory.FIFTH_INDEX, order.getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception updating order", e);
        }
    }
}
