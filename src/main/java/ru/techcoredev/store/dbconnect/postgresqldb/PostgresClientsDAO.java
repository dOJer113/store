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
    private Connection connection;

    public PostgresClientsDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertClient(Client client) {
        String sql = "INSERT INTO clients (userid, name, surname, phoneNumber, address) VALUES (?,?, ?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, client.getClientId());
            statement.setString(2, client.getName());
            statement.setString(3, client.getSurname());
            statement.setString(4, client.getPhoneNumber());
            statement.setString(5, client.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(int userId) {
        String sql = "DELETE FROM clients WHERE userId = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(Client client) {
        String sql = "UPDATE clients SET name = ?, surname = ?, phoneNumber = ?, address = ? WHERE userId = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getPhoneNumber());
            statement.setString(4, client.getAddress());
            statement.setInt(5, client.getClientId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}