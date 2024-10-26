package ru.techcoredev.store.dbconnect.DAOinterfeices;

import ru.techcoredev.store.objects.Client;
import ru.techcoredev.store.objects.User;

import java.util.List;

public interface ClientsDAO {
    void insertClient(Client client);

    void deleteClient(int clientId);

    void updateClient(Client client);

    List<Client> getClients();
}
