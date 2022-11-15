package org.example.entities.responseEntities.stat;

public class ResponsePurchase {
    private String name;
    private Integer expenses;

    public ResponsePurchase(String name, Integer expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "\n\t\t\t\t\t{" +
                "\n\t\t\t\t\t\t\"name\":\"" + name + '\"' +
                ", \n\t\t\t\t\t\t\"expenses\":" + expenses +
                "\n\t\t\t\t\t}";
    }
}
