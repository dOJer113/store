package ru.techcoredev.store.dbconnect.hibernate;

import org.hibernate.Session;
import ru.techcoredev.store.dbconnect.DAOinterfeices.ProductsDAO;
import ru.techcoredev.store.objects.Product;

import java.util.List;

public class HibernateProductsDAO implements ProductsDAO {
    @Override
    public void createProduct(Product product) {
        TransactionServices.executeInsideTransaction(session -> session.save(product));
    }

    @Override
    public void deleteProduct(Product product) {
        TransactionServices.executeInsideTransaction(session -> session.delete(product));
    }

    @Override
    public void updateProduct(Product product) {
        TransactionServices.executeInsideTransaction(session -> session.update(product));
    }

    @Override
    public List<Product> getProducts() {
        try (Session session = HibernateDAOFactory.getSession()) {
            return session.createQuery("From Order").list();
        }
    }
}
