package ru.techcoredev.store.dbconnect.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.dbconnect.DAOinterfeices.UsersDAO;
import ru.techcoredev.store.dbconnect.pool.ConnectionPool;
import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.User;

import java.util.ArrayList;
import java.util.List;

public class HibernateUserDao implements UsersDAO {
    private static final String QUERY_GET_ID_BY_EMAIL = "Select u.userId FROM User u where u.email = :email";
    private static final String QUERY_GET_USER_BY_EMAIL_PASSWORD = "FROM User u where u.email = :email and u.password = :password";
    private static final String QUERY_GET_ROLE_BY_EMAIL = "Select u.role FROM User u where u.email = :email";
    private static final String SELECT_QUERY = "From User";
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    @Override
    public List<User> getUsers() {
        logger.debug("Getting users from db by hibernate");
        List<User> userList = new ArrayList<>();
        try (Session session = HibernateDAOFactory.getSession()) {
            userList = session.createQuery(SELECT_QUERY).list();
        } catch (Exception e) {
            ExceptionHandler.handleException("Exception getting users from db by ", e);
        }
        return userList;
    }

    @Override
    public void insertUser(User user) {
        logger.debug("Inserting user with userID: " + user.getUserId());
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            logger.debug("User added");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception adding user ", e);
        }
    }

    @Override
    public void deleteUser(User user) {
        logger.debug("Deleting users with userID: " + user.getUserId());
        if (user.getUserId() == 0) {
            throw new RuntimeException();
        }
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            logger.debug("User was deleted");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception deleting user ", e);
        }
    }

    @Override
    public void updateUser(User user) {
        logger.debug("Updating user with userID: " + user.getUserId());
        if (user.getUserId() == 0) {
            throw new RuntimeException();
        }
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            logger.debug("User was updated");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception updating user ", e);
        }
    }

    @Override
    public int getUserIdByEmail(String email) {
        logger.debug("Getting user id from db by email: " + email);
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
        logger.debug("Getting users from db by email and password: " + emailToSearch + ", " + passwordToSearch);
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
        logger.debug("Getting user from db by email: " + email);
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