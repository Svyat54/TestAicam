package org.example.entities.responseEntities.stat;

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

    public ResponseJsonObject(String type, Integer totalDays, LinkedList<ResponseCustomer> customers, Integer totalExpenses, Double avgExpenses) {
        this.type = type;
        this.totalDays = totalDays;
        this.customers = customers;
        this.totalExpenses = totalExpenses;
        this.avgExpenses = avgExpenses;
    }

    @Override
    public String toString() {
        return "{" +
                "\n\t\"type\":\"" + type + '\"' +
                ", \n\t\"totalDays\":" + totalDays +
                ", \n\t\"customers\":" + customers +
                ", \n\t\"totalExpenses\":" + totalExpenses +
                ", \n\t\"avgExpenses\":" + avgExpenses +
                "\n}";
    }
}
