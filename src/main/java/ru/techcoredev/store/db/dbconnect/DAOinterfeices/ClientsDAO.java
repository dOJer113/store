package ru.techcoredev.store.db.dbconnect.DAOinterfeices;

import ru.techcoredev.store.objects.Client;

import java.util.List;

public interface ClientsDAO {
    void insertClient(Client client);

    void deleteClient(Client client);

    void updateClient(Client client);

    List<Client> getClients();
}
