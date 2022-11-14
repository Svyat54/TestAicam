package org.example;

import org.example.utilities.StatQueryParser;
import org.example.utilities.Reader;
import org.json.JSONObject;
import java.io.IOException;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) throws IOException {
            //Первый запрос
//        LinkedList<JSONObject> list = Reader.getJsonFromFile("input.json");
//        for(int i = 0; i < list.size(); i++) {
//            System.out.println(agent.getCustomersList(list.get(i)));
//        }
        LinkedList<JSONObject> list = Reader.getJsonFromFile("input1.json");
        StatQueryParser.response(list);
    }
}