package org.example.entities.requestEntities;

public class Product {
    private String name;
    private Integer price;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
