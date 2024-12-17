package ru.techcoredev.store.db.dbmanagers;

import ru.techcoredev.store.db.dbconnect.DAOinterfeices.DAOFactory;
import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.objects.Product;

import java.util.List;

public class ProductsManager {
    private DBType dbType;

    public ProductsManager(DBType dbType) {
        this.dbType = dbType;
    }

    public int createProduct(Product product) {
        return DAOFactory.getInstance(dbType).getProductDAO().createProduct(product);
    }

    public  List<Product> getProducts() {
        return DAOFactory.getInstance(dbType).getProductDAO().getProducts();
    }

    public  void updateProduct(Product product) {
        DAOFactory.getInstance(dbType).getProductDAO().updateProduct(product);
    }

    public  void deleteProduct(Product product) {
        DAOFactory.getInstance(dbType).getProductDAO().deleteProduct(
                product
        );
    }

}
