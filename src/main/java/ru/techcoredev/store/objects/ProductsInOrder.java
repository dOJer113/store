package ru.techcoredev.store.objects;

import jakarta.persistence.*;

@Entity
@Table(name = "products_in_order")
public class ProductsInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "productid")
    private int productId;

    @Column(name = "count_product")
    private int countProduct;

    public ProductsInOrder() {}

    public ProductsInOrder(int orderNumber, int productId, int countProduct) {
        this.orderNumber = orderNumber;
        this.productId = productId;
        this.countProduct = countProduct;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductsInOrder)) return false;

        ProductsInOrder that = (ProductsInOrder) o;

        if (getId() != that.getId()) return false;
        if (getOrderNumber() != that.getOrderNumber()) return false;
        if (getProductId() != that.getProductId()) return false;
        return getCountProduct() == that.getCountProduct();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getOrderNumber();
        result = 31 * result + getProductId();
        result = 31 * result + getCountProduct();
        return result;
    }

    @Override
    public String toString() {
        return "ProductsInOrder{" +
                "id=" + id +
                ", orderNumber=" + orderNumber +
                ", productId=" + productId +
                ", countProduct=" + countProduct +
                '}';
    }
}