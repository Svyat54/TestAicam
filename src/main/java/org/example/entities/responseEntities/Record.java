package org.example.entities.responseEntities;

import java.util.Comparator;
import java.util.LinkedList;

public class Record {
    private String fullName;
    private String productName;
    private Integer expenses;

    public Record(String fullName, String productName, Integer expenses) {
        this.fullName = fullName;
        this.productName = productName;
        this.expenses = expenses;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getExpenses() {
        return expenses;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setExpenses(Integer expenses) {
        this.expenses = expenses;
    }


    @Override
    public String toString() {
        return "{" +
                "fullName='" + fullName + '\'' +
                ", productName='" + productName + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
