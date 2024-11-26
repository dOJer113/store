package ru.techcoredev.store.dbconnect.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.techcoredev.store.ExceptionHandler;

public class TransactionServices {
    public static void executeInsideTransaction(SessionAction action) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateDAOFactory.getSession();
            transaction = session.beginTransaction();
            action.execute(session);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            ExceptionHandler.handleException("Exception executing transaction", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @FunctionalInterface
    public interface SessionAction {
        void execute(Session session);
    }
}
