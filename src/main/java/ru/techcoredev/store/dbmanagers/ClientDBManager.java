package ru.techcoredev.store.dbmanagers;

import ru.techcoredev.store.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.dbconnect.DBType;
import ru.techcoredev.store.objects.Client;

import java.util.List;

public class ClientDBManager {
    public static void updateClient(Client client) {
        DAOFactory.getInstance(DBType.POSTGRES).getClientsDAO().updateClient(client);
    }

    public static List<Client> getClients() {
        return DAOFactory.getInstance(DBType.POSTGRES).getClientsDAO().getClients();
    }

    protected static void addClient(Client client) {
        DAOFactory.getInstance(DBType.POSTGRES).getClientsDAO().insertClient(client);
    }

    protected static void deleteClient(int userId) {
        DAOFactory.getInstance(DBType.POSTGRES).getClientsDAO().deleteClient(userId);
    }


}
