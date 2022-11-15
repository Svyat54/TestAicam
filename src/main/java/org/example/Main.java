package org.example;

import org.example.utilities.Reader;
import org.example.utilities.queryParser.SearchQueryParser;
import org.example.utilities.queryParser.StatQueryParser;
import org.json.JSONObject;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
            //Первый запрос
        LinkedList<JSONObject> list = Reader.getJsonFromFile("input.json");
        for(JSONObject object : list){
            Validation.isInputDataValid(object);
        }
        SearchQueryParser.response(list);
            //Второй запрос
//        LinkedList<JSONObject> list = Reader.getJsonFromFile("input1.json");
//        for(JSONObject object : list){
//            Validation.isInputDataValid(object);
//        }
//        StatQueryParser.response(list);
    }
}