package ru.techcoredev.store.db.dbconnect.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.db.dbconnect.DAOinterfeices.ClientsDAO;
import ru.techcoredev.store.objects.builders.Client;

import java.util.List;

public class HibernateClientsDAO implements ClientsDAO {
    private static final Logger logger = LogManager.getLogger(HibernateClientsDAO.class);
    private static final String SELECT_QUERY = "FROM Client";
    private static final String SELECT_CLIENT_BY_ID = "FROM Client WHERE userId = :id";;

    @Override
    public void insertClient(Client client) {
        logger.debug("Inserting client with userid: " + client.getUserId());
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
            logger.debug("Client added");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception adding client ", e);
        }
    }

    @Override
    public Client getClientById(int id) {
        try (Session session = HibernateDAOFactory.getSession()) {
            Query<Client> query = session.createQuery(SELECT_CLIENT_BY_ID, Client.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }

    @Override
    public void deleteClient(Client client) {
        logger.debug("Deleting users with userID: " + client.getUserId());
        if (client.getUserId() == 0) {
            throw new RuntimeException();
        }
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
            logger.debug("Client was deleted");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception deleting client ", e);
        }
    }

    @Override
    public void updateClient(Client client) {
        logger.debug("Updating client with userID: " + client.getUserId());
        if (client.getUserId() == 0) {
            throw new RuntimeException();
        }
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
            logger.debug("Client was updated");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception updating client ", e);
        }
    }

    @Override
    public List<Client> getClients() {
        try (Session session = HibernateDAOFactory.getSession()) {
            return session.createQuery(SELECT_QUERY, Client.class).list();
        }
    }

}
