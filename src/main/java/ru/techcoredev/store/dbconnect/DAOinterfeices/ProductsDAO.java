package ru.techcoredev.store.dbconnect.DAOinterfeices;

import ru.techcoredev.store.objects.Product;

import java.util.List;

public interface ProductsDAO {
    List<Product> getProducts();

    void deleteProduct(Product product);

    void updateProduct(Product product);

    void createProduct(Product product);
}
