package io.foxlime;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private String SKU;
    private String name;
    private BigDecimal price;

    public Product(String SKU, String name, BigDecimal price) {
        this.SKU = SKU;
        this.name = name;
        this.price = price;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(SKU, product.SKU) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SKU, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "SKU='" + SKU + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
