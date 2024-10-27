package ru.techcoredev.store.dbconnect.postgresqldb;

import ru.techcoredev.store.dbconnect.DAOinterfeices.ClientsDAO;
import ru.techcoredev.store.objects.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresClientsDAO implements ClientsDAO {
    private static final String INSERT_QUERY = "INSERT INTO clients (userid, name, surname, phoneNumber, address) VALUES (?,?, ?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM clients WHERE userId = ?";
    private static final String UPDATE_QUERY = "UPDATE clients SET name = ?, surname = ?, phoneNumber = ?, address = ? WHERE userId = ?";
    private static final String SELECT_QUERY = "SELECT * FROM clients";
    private final Connection connection;


    public PostgresClientsDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertClient(Client client) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(PostgresDBDAOFactory.FIRST_INDEX, client.getUserId());
            statement.setString(PostgresDBDAOFactory.SECOND_INDEX, client.getName());
            statement.setString(PostgresDBDAOFactory.THIRD_INDEX, client.getSurname());
            statement.setString(PostgresDBDAOFactory.FOURTH_INDEX, client.getPhoneNumber());
            statement.setString(PostgresDBDAOFactory.FIFTH_INDEX, client.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(int userId) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(PostgresDBDAOFactory.FIRST_INDEX, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(Client client) {
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(PostgresDBDAOFactory.FIRST_INDEX, client.getName());
            statement.setString(PostgresDBDAOFactory.SECOND_INDEX, client.getSurname());
            statement.setString(PostgresDBDAOFactory.THIRD_INDEX, client.getPhoneNumber());
            statement.setString(PostgresDBDAOFactory.FOURTH_INDEX, client.getAddress());
            statement.setInt(PostgresDBDAOFactory.FIFTH_INDEX, client.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt(PostgresDBDAOFactory.FIRST_INDEX),
                        resultSet.getString(PostgresDBDAOFactory.SECOND_INDEX),
                        resultSet.getString(PostgresDBDAOFactory.THIRD_INDEX),
                        resultSet.getString(PostgresDBDAOFactory.FOURTH_INDEX),
                        resultSet.getString(PostgresDBDAOFactory.FIFTH_INDEX));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}