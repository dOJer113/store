package ru.techcoredev.store.dbmanagers;

import ru.techcoredev.store.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.dbconnect.DBType;
import ru.techcoredev.store.objects.Product;

import java.util.List;

public class ProductsManager {
    public static void createProduct(Product product) {
        DAOFactory.getInstance(DBType.POSTGRES).getProductDAO().createProduct(product);
    }

    public static List<Product> getProducts() {
        return DAOFactory.getInstance(DBType.POSTGRES).getProductDAO().getProducts();
    }

    public static void updateProduct(Product product) {
        DAOFactory.getInstance(DBType.POSTGRES).getProductDAO().updateProduct(product);
    }

    public static void deleteProduct(int productId) {
        DAOFactory.getInstance(DBType.POSTGRES).getProductDAO().deleteProduct(productId);
    }

}
