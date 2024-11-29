package ru.techcoredev.store.db.dbconnect.postgresqldb;

import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.db.dbconnect.DAOinterfeices.ProductsDAO;
import ru.techcoredev.store.objects.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresProductsDAO implements ProductsDAO {

    private static final String INSERT_QUERY = "insert into products(name, description, price, remains) VALUES (?,?,?,?)";
    private static final String DELETE_QUERY = "delete from  products where id = ?";
    private static final String UPDATE_QUERY = "update products set name = ?,description = ?,price = ?,remains = ? where id = ?";
    private static final String SELECT_QUERY = "select * from products";
    private final Connection connection;

    public PostgresProductsDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt(PostgresDBDAOFactory.FIRST_INDEX),
                        resultSet.getString(PostgresDBDAOFactory.SECOND_INDEX),
                        resultSet.getString(PostgresDBDAOFactory.THIRD_INDEX),
                        resultSet.getBigDecimal(PostgresDBDAOFactory.FOURTH_INDEX),
                        resultSet.getDouble(PostgresDBDAOFactory.FIFTH_INDEX));
                products.add(product);
            }
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception getting products from db", e);
        }
        return products;
    }

    @Override
    public void deleteProduct(Product product) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(PostgresDBDAOFactory.FIRST_INDEX, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception deleting product from db", e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE_QUERY)) {

            statement.executeUpdate();
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception updating product", e);
        }
    }

    @Override
    public void createProduct(Product product) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(PostgresDBDAOFactory.FIRST_INDEX, product.getName());
            statement.setString(PostgresDBDAOFactory.SECOND_INDEX, product.getDescription());
            statement.setBigDecimal(PostgresDBDAOFactory.THIRD_INDEX, product.getPrice());
            statement.setDouble(PostgresDBDAOFactory.FOURTH_INDEX, product.getRemains());
            statement.executeUpdate();
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception inserting product into db", e);
        }
    }
}
