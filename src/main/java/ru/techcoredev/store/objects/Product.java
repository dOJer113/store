package ru.techcoredev.store.objects;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private double remains;

    public Product(int id, String name, String description, BigDecimal price, double remains) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.remains = remains;
    }

    public Product(String name, String description, BigDecimal price, double remains) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.remains = remains;
    }

    public Product() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getRemains() {
        return this.remains;
    }

    public void setRemains(double remains) {
        this.remains = remains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (getId() != product.getId()) return false;
        if (Double.compare(product.getRemains(), getRemains()) != 0) return false;
        if (getName() != null ? !getName().equals(product.getName()) : product.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(product.getDescription()) : product.getDescription() != null)
            return false;
        return getPrice() != null ? getPrice().equals(product.getPrice()) : product.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        temp = Double.doubleToLongBits(getRemains());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
