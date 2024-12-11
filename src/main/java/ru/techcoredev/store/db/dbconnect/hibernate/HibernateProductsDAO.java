package ru.techcoredev.store.db.dbconnect.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.db.dbconnect.DAOinterfeices.ProductsDAO;
import ru.techcoredev.store.objects.Product;

import java.util.List;

public class HibernateProductsDAO implements ProductsDAO {

    private static final Logger logger = LogManager.getLogger(HibernateProductsDAO.class);
    private static final String SELECT_QUERY = "From Product where id = :id";
    private static final String SELECT_PRODUCTS_BY_ID = "FROM Product WHERE id = :id";

    @Override
    public Product getProductById(int id) {
        try (Session session = HibernateDAOFactory.getSession()) {
            Query<Product> query = session.createQuery(SELECT_PRODUCTS_BY_ID, Product.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }

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
