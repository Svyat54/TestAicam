package org.example;

import org.example.DB.DbShopAgent;
import org.example.entities.responseEntities.Record;
import org.example.entities.responseEntities.StatQueryParser;
import org.example.utilities.Reader;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;


public class Main {

    public static void main(String[] args) throws IOException {
        String url = "jdbc:postgresql://localhost:5432/DatabasesTestTask";
        String name = "root";
        String password = "rootroot1";

        DbShopAgent agent = new DbShopAgent(url, name, password);
            //Первый запрос
//        LinkedList<JSONObject> list = Reader.getJsonFromFile("input.json");
//        for(int i = 0; i < list.size(); i++) {
//            System.out.println(agent.getCustomersList(list.get(i)));
//        }

        LinkedList<JSONObject> list = Reader.getJsonFromFile("input1.json");
        LinkedList<Record> allRecordsByPeriod = agent.getRecordsList(list.get(0));
        LinkedList<LinkedList<Record>> recordsList = StatQueryParser.getListOfRecordsByFullName(allRecordsByPeriod);
        for(LinkedList<Record> listOfRecordsByPerson : recordsList) {
            System.out.println(listOfRecordsByPerson);
        }


    }
}