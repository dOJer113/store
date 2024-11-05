package ru.techcoredev.store.dbconnect.postgresqldb;

import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.dbconnect.DAOinterfeices.StatusDAO;
import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresStatusesDAO implements StatusDAO {

    private static final String SELECT_QUERY = "select * from statuses";
    private final Connection connection;

    public PostgresStatusesDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Status> getStatuses() {
        List<Status> statuses = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                statuses.add(Status.valueOf(resultSet.getString(PostgresDBDAOFactory.FIRST_INDEX).toUpperCase()));
            }
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception getting statuses from db", e);
        }
        return statuses;
    }


}
