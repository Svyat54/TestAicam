package org.example.entities.requestEntities;

import java.time.LocalDate;

public class Order {
    private int customerId;
    private int productId;
    private LocalDate orderDate;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Order(int customerId, int productId, LocalDate orderDate) {
        this.customerId = customerId;
        this.productId = productId;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                ", orderDate=" + orderDate +
                '}';
    }
}
