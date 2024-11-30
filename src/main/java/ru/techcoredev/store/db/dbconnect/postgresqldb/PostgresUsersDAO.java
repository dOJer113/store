package ru.techcoredev.store.db.dbconnect.postgresqldb;

import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.db.dbconnect.DAOinterfeices.UsersDAO;
import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.builders.RegistrationUserBuilder;
import ru.techcoredev.store.objects.builders.User;
import ru.techcoredev.store.objects.builders.UserBuilder;

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
    private static final String SELECT_PASS_EMAIL = "select * from users where email = ? and password = ?";
    private static final String SELECT_ROLE = "SELECT role FROM users WHERE email = ?";
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
            ExceptionHandler.handleException("Exception inserting user into db", e);
        }
    }

    @Override
    public void deleteUser(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(PostgresDBDAOFactory.FIRST_INDEX, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception deleting user from db", e);
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
            ExceptionHandler.handleException("Exception updating user", e);
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
            ExceptionHandler.handleException("Exception getting users from db", e);
        }
        return 0;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new RegistrationUserBuilder().id(resultSet.getInt(PostgresDBDAOFactory.FIRST_INDEX))
                        .email(resultSet.getString(PostgresDBDAOFactory.SECOND_INDEX))
                        .role(Role.valueOf(resultSet.getString(PostgresDBDAOFactory.THIRD_INDEX).toUpperCase()))
                        .password(resultSet.getString(PostgresDBDAOFactory.FOURTH_INDEX))
                        .build());
            }
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception getting users from db", e);
        }
        return users;
    }

    @Override
    public User getUserByEmailPassword(String emailToSearch, String passwordToSearch) {
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_PASS_EMAIL)) {
            statement.setString(PostgresDBDAOFactory.FIRST_INDEX, emailToSearch);
            statement.setString(PostgresDBDAOFactory.SECOND_INDEX, passwordToSearch);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new RegistrationUserBuilder().id(resultSet.getInt(PostgresDBDAOFactory.FIRST_INDEX))
                        .email(resultSet.getString(PostgresDBDAOFactory.SECOND_INDEX))
                        .role(Role.valueOf(resultSet.getString(PostgresDBDAOFactory.THIRD_INDEX).toUpperCase()))
                        .password(resultSet.getString(PostgresDBDAOFactory.FOURTH_INDEX))
                        .build();
            }
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception getting user by email and password from db", e);
        }
        return new RegistrationUserBuilder().build();
    }

    @Override
    public Role getRoleByEmail(String email) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE)) {
            preparedStatement.setString(PostgresDBDAOFactory.FIRST_INDEX, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Role.valueOf(rs.getString(PostgresDBDAOFactory.FIRST_INDEX));
            }
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception getting users role by email", e);
        }
        return null;
    }
}
