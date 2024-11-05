package ru.techcoredev.store.dbconnect.postgresqldb;

import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.dbconnect.DAOinterfeices.RolesDAO;
import ru.techcoredev.store.objects.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresRolesDAO implements RolesDAO {
    private static final String SELECT_QUERY = "select * from roles";
    private final Connection connection;

    public PostgresRolesDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                roles.add(Role.valueOf(resultSet.getString(PostgresDBDAOFactory.FIRST_INDEX).toUpperCase()));
            }
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception getting roles from db", e);
        }
        return roles;
    }
}
