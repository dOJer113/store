package ru.techcoredev.store.dbmanagers;

import ru.techcoredev.store.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.dbconnect.DBType;
import ru.techcoredev.store.objects.Product;

import java.util.List;

public class ProductsManager {
    private static DBType dbType;

    public static void getInstance(DBType dbType) {
        ProductsManager.dbType = dbType;
    }
    public static void createProduct(Product product) {
        DAOFactory.getInstance(dbType).getProductDAO().createProduct(product);
    }

    public static List<Product> getProducts() {
        return DAOFactory.getInstance(dbType).getProductDAO().getProducts();
    }

    public static void updateProduct(Product product) {
        DAOFactory.getInstance(dbType).getProductDAO().updateProduct(product);
    }

    public static void deleteProduct(Product product) {
        DAOFactory.getInstance(dbType).getProductDAO().deleteProduct(
                product
        );
    }

}
