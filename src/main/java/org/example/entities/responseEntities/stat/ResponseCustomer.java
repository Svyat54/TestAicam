package org.example.entities.responseEntities.stat;

import java.util.LinkedList;

public class ResponseCustomer {
    private String name;
    private LinkedList<ResponsePurchase> purchases;
    private Integer totalExpenses;

    public String getName() {
        return name;
    }

    public Integer getTotalExpenses() {
        return totalExpenses;
    }

    public ResponseCustomer(String name, LinkedList<ResponsePurchase> purchases, Integer totalExpenses) {
        this.name = name;
        this.purchases = purchases;
        this.totalExpenses = totalExpenses;
    }

    @Override
    public String toString() {
        return "\n\t\t{" +
                "\n\t\t\t\"name\":\"" + name + '\"' +
                ", \n\t\t\t\"purchases\":" + purchases +
                ", \n\t\t\t\"totalExpenses\":" + totalExpenses +
                "\n\t\t}";
    }
}
