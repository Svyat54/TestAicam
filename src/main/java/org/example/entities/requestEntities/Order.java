package org.example.entities.requestEntities;

import java.time.LocalDate;

public class Order {
    private int customerId;
    private int productId;
    private LocalDate orderDate;

    @Override
    public String toString() {
        return "Order{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                ", orderDate=" + orderDate +
                '}';
    }
}
