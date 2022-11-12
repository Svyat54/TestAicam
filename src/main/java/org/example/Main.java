package org.example;

import org.example.DB.DbShopAgent;
import org.example.utilities.Reader;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public class Main {

    public static void main(String[] args) throws IOException {
        String url = "jdbc:postgresql://localhost:5432/DatabasesTestTask";
        String name = "root";
        String password = "rootroot1";

        DbShopAgent agent = new DbShopAgent(url, name, password);


        LinkedList<JSONObject> list = Reader.getCriterias("input.json");
        for(int i = 0; i < list.size(); i++) {
////            System.out.println(DbShopAgent.getRequestType(list.get(i)));
//            System.out.println(agent.executeQuery(list.get(i)));
            System.out.println(agent.getCustomersList(list.get(i)));
        }

    }
}