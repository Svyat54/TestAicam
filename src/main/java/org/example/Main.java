package org.example;

import org.example.DB.DbShopAgent;
import org.example.entities.Customer;
import org.postgresql.Driver;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/DatabasesTestTask";
        String name = "root";
        String password = "rootroot1";

        DbShopAgent agent = new DbShopAgent(url, name, password);
        LinkedList<Customer> list = agent.getCustomersList();
        while (!list.isEmpty()){
            System.out.println(list.pollFirst().toString());
        }



    }
}