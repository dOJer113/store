package ru.techcoredev.store.dbconnect.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.dbconnect.DAOinterfeices.ProductsDAO;
import ru.techcoredev.store.dbconnect.pool.ConnectionPool;
import ru.techcoredev.store.objects.Product;

import java.util.List;

public class HibernateProductsDAO implements ProductsDAO {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final String SELECT_QUERY = "From Product";

    @Override
    public void createProduct(Product product) {
        logger.debug("Creating product with name: " + product.getName());
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            logger.debug("Product added");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception creating product ", e);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        logger.debug("Deleting product with name: " + product.getName());
        if (product.getId() == 0) {
            throw new RuntimeException();
        }
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
            logger.debug("Product was deleted");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception deleting product ", e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        logger.debug("Updating product with name: " + product.getName());
        if (product.getId() == 0) {
            throw new RuntimeException();
        }
        Transaction transaction = null;
        try (Session session = HibernateDAOFactory.getSession()) {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
            logger.debug("Product was updated");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            ExceptionHandler.handleException("Exception updating product ", e);
        }
    }

    @Override
    public List<Product> getProducts() {
        try (Session session = HibernateDAOFactory.getSession()) {
            return session.createQuery(SELECT_QUERY).list();
        }
    }
}
