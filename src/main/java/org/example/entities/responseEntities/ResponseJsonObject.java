package org.example.entities.responseEntities;

import java.util.LinkedList;

public class ResponseJsonObject {
    private String type;
    private Integer totalDays;
    private LinkedList<ResponseCustomer> customers;
    private Integer totalExpenses;
    private Double avgExpenses;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public LinkedList<ResponseCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(LinkedList<ResponseCustomer> customers) {
        this.customers = customers;
    }

    public Integer getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Integer totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Double getAvgExpenses() {
        return avgExpenses;
    }

    public void setAvgExpenses(Double avgExpenses) {
        this.avgExpenses = avgExpenses;
    }
}
