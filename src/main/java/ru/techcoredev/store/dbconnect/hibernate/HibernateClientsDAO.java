package ru.techcoredev.store.dbconnect.hibernate;

import org.hibernate.Session;
import ru.techcoredev.store.dbconnect.DAOinterfeices.ClientsDAO;
import ru.techcoredev.store.objects.Client;

import java.util.List;

public class HibernateClientsDAO implements ClientsDAO {

    @Override
    public void insertClient(Client client) {
        TransactionServices.executeInsideTransaction(session -> session.save(client));
    }

    @Override
    public void deleteClient(Client client) {
        TransactionServices.executeInsideTransaction(session -> session.delete(client));
    }

    @Override
    public void updateClient(Client client) {
        TransactionServices.executeInsideTransaction(session -> session.update(client));
    }

    @Override
    public List<Client> getClients() {
        try (Session session = HibernateDAOFactory.getSession()) {
            return session.createQuery("FROM Client", Client.class).list();
        }
    }

}
