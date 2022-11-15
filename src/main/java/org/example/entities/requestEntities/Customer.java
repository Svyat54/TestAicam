package org.example.entities.requestEntities;

public class Customer {
    private String name;
    private String lastName;

    public String getName() {
        return name;
    }

    public Customer(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "\t{ \"lastName\": \"" + lastName + '\"' +
                ", \"firstName\": \"" + name + "\"}";
    }
}
