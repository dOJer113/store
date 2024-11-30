package ru.techcoredev.store.db.dbmanagers;

import ru.techcoredev.store.db.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.objects.builders.Client;

import java.util.List;

public class ClientDBManager {
    private DBType dbType;

    public ClientDBManager(DBType dbType) {
        this.dbType = dbType;
    }

    public void updateClient(Client client) {
        DAOFactory.getInstance(dbType).getClientsDAO().updateClient(client);
    }

    public List<Client> getClients() {
        return DAOFactory.getInstance(dbType).getClientsDAO().getClients();
    }

    protected void addClient(Client client) {
        DAOFactory.getInstance(dbType).getClientsDAO().insertClient(client);
    }

    protected void deleteClient(Client client) {
        DAOFactory.getInstance(dbType).getClientsDAO().deleteClient(client);
    }


}
