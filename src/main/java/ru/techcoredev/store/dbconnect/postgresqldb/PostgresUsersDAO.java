package ru.techcoredev.store.dbconnect.postgresqldb;

import ru.techcoredev.store.dbconnect.DAOinterfeices.UsersDAO;
import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresUsersDAO implements UsersDAO {
    private static final String INSERT_QUERY = "INSERT INTO users (email, role, password) VALUES (?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE userId = ?";
    private static final String UPDATE_QUERY = "UPDATE users SET email = ?, role = ?, password = ? WHERE userId = ?";
    private static final String SELECT_QUERY = "SELECT * FROM users";
    private Connection connection;

    public PostgresUsersDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertUser(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(PostgresDBDAOFactory.FIRST_INDEX, user.getEmail());
            statement.setString(PostgresDBDAOFactory.SECOND_INDEX, user.getRole().toString().toLowerCase());
            statement.setString(PostgresDBDAOFactory.THIRD_INDEX, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(PostgresDBDAOFactory.FIRST_INDEX, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(PostgresDBDAOFactory.FIRST_INDEX, user.getEmail());
            statement.setString(PostgresDBDAOFactory.SECOND_INDEX, user.getRole().toString());
            statement.setString(PostgresDBDAOFactory.THIRD_INDEX, user.getPassword());
            statement.setInt(PostgresDBDAOFactory.FOURTH_INDEX, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getUserIdByEmail(String email) {
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String currentEmail = resultSet.getString(PostgresDBDAOFactory.SECOND_INDEX);
                if (email.equals(currentEmail)) {
                    return resultSet.getInt(PostgresDBDAOFactory.FIRST_INDEX);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(PostgresDBDAOFactory.FIRST_INDEX),
                        resultSet.getString(PostgresDBDAOFactory.SECOND_INDEX),
                        Role.valueOf(resultSet.getString(PostgresDBDAOFactory.THIRD_INDEX)),
                        resultSet.getString(PostgresDBDAOFactory.FOURTH_INDEX));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
