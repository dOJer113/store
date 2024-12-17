package ru.techcoredev.store.db.dbconnect.DAOinterfeices;

import ru.techcoredev.store.objects.Product;

import java.util.List;

public interface ProductsDAO {
    List<Product> getProducts();

    void deleteProduct(Product product);

    void updateProduct(Product product);

    int createProduct(Product product);

    Product getProductById(int id);
}
