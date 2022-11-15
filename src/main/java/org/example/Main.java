package org.example;

import org.example.DB.DbShopAgent;
import org.example.utilities.Reader;
import org.example.utilities.queryParser.SearchQueryParser;
import org.json.JSONObject;
import java.io.IOException;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) throws IOException {
            //Первый запрос
        LinkedList<JSONObject> list = Reader.getJsonFromFile("input.json");
        SearchQueryParser.response(list);
            //Второй запрос
//        LinkedList<JSONObject> list = Reader.getJsonFromFile("input1.json");
//        StatQueryParser.response(list);
    }
}