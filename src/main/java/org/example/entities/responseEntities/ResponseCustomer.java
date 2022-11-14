package org.example.entities.responseEntities;

import java.util.LinkedList;

public class ResponseCustomer {
    private String name;
    private LinkedList<ResponsePurchase> purchases;
    private Integer totalExpenses;
}
