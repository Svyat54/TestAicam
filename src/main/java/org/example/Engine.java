package org.example;

import org.example.utilities.Reader;
import org.example.utilities.exeption.InputOutputName;
import org.example.utilities.exeption.InputParameterType;
import org.example.utilities.queryParser.SearchQueryParser;
import org.example.utilities.queryParser.StatQueryParser;
import org.json.JSONObject;
import java.io.IOException;
import java.util.LinkedList;

public class Engine {
    public static void execute(String query) throws IOException {
        String type = query.split(" ")[0];
        String inputPath = query.split(" ")[1];
        String outputPath = query.split(" ")[2];

        if(!inputPath.equals("input.json")){
            throw new InputOutputName("Error lock file output");
        }
            if (type.equals("search")) {
                //Первый запрос
                LinkedList<JSONObject> list = Reader.getJsonFromFile("input.json");
                for (JSONObject object : list) {
                    Validation.isInputDataValid(object);
                }
                SearchQueryParser.response(list);
            } else if (type.equals("stat")) {
                //Второй запрос
                LinkedList<JSONObject> list = Reader.getJsonFromFile("input.json");
                for (JSONObject object : list) {
                    Validation.isInputDataValid(object);
                }
                StatQueryParser.response(list);
            } else {
                throw new InputParameterType("Error lock file output");
            }
    }
}
