package org.example.entities.responseEntities;

import java.util.LinkedList;

public class ResponseCustomer {
    private String name;
    private LinkedList<ResponsePurchase> purchases;
    private Integer totalExpenses;

    public ResponseCustomer(String name, LinkedList<ResponsePurchase> purchases, Integer totalExpenses) {
        this.name = name;
        this.purchases = purchases;
        this.totalExpenses = totalExpenses;
    }
}
