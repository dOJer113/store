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
    private Connection connection;

    public PostgresUsersDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO users (email, role, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getRole().toString());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE userId = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET email = ?, role = ?, password = ? WHERE userId = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getRole().toString());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        Role.valueOf(resultSet.getString(4)),
                        resultSet.getString(5));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
