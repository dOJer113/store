package ru.techcoredev.store.dbconnect.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.dbconnect.DAOinterfeices.UsersDAO;
import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.User;

import java.util.List;

public class HibernateUserDao implements UsersDAO {
    private static final String QUERY_GET_ID_BY_EMAIL = "Select u.userId FROM User u where u.email = :email";
    private static final String QUERY_GET_USER_BY_EMAIL_PASSWORD = "FROM User u where u.email = :email and u.password = :password";
    private static final String QUERY_GET_ROLE_BY_EMAIL = "Select u.role FROM User u where u.email = :email";

    @Override
    public List<User> getUsers() {
        try (Session session = HibernateDAOFactory.getSession()) {
            return session.createQuery("From User").list();
        }
    }

    @Override
    public void insertUser(User user) {
        TransactionServices.executeInsideTransaction(session -> session.save(user));
    }

    @Override
    public void deleteUser(User user) {
        TransactionServices.executeInsideTransaction(session -> session.delete(user));
    }

    @Override
    public void updateUser(User user) {
        TransactionServices.executeInsideTransaction(session -> session.update(user));
    }

    @Override
    public int getUserIdByEmail(String email) {
        Session session = HibernateDAOFactory.getSession();
        Transaction tx1 = session.beginTransaction();
        Query<Integer> query = session.createQuery(QUERY_GET_ID_BY_EMAIL);
        query.setParameter("email", email);
        int id = 0;
        try {
            id = query.list().get(0);
        } catch (NullPointerException e) {
            ExceptionHandler.handleException("Exception getting user`s id from db by email", e);
        }
        tx1.commit();
        return id;
    }

    @Override
    public User getUserByEmailPassword(String emailToSearch, String passwordToSearch) {
        Session session = HibernateDAOFactory.getInstance().getSession();
        Query<User> query = null;
        try {
            query = session.createQuery(QUERY_GET_USER_BY_EMAIL_PASSWORD);
            query.setParameter("email", emailToSearch);
            query.setParameter("password", passwordToSearch);
        } catch (IllegalArgumentException | NullPointerException e) {
            ExceptionHandler.handleException("Exception getting user from db by email and password", e);
        }
        User user = new User();
        try {
            user = query.list().get(0);
        } catch (NullPointerException e) {
            ExceptionHandler.handleException("Exception getting user from db by email and password", e);
        }
        return user;
    }


    @Override
    public Role getRoleByEmail(String email) {
        Session session = HibernateDAOFactory.getSession();
        Query<Role> query = session.createQuery(QUERY_GET_ROLE_BY_EMAIL);
        query.setParameter("email", email);
        Role role = Role.NO_USER;
        try {
            role = query.list().get(0);
        } catch (NullPointerException e) {
            ExceptionHandler.handleException("Exception getting user`s role from db by email ", e);
        }
        return role;
    }

}