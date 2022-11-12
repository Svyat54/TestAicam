package org.example.utilities;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Reader {
    public static StringBuilder readerInput(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String str;
        while ((str = br.readLine()) != null){
            sb.append(str).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb;
    }

    public static LinkedList<JSONObject> getCriterias(String path) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        JSONObject obj = new JSONObject(jsonString);
        return getCriteriasList(obj);
    }

    private static LinkedList<JSONObject> getCriteriasList (JSONObject object){
        LinkedList<JSONObject> criteriasList = new LinkedList<>();
        String criteriasString = object.get("criterias").toString();
        criteriasString = criteriasString.substring(1, criteriasString.length() - 1);
        String[] arrayOfCriterias = criteriasString.split("},");
        for (int i = 0; i < arrayOfCriterias.length - 1; i++) {
            criteriasList.add(new JSONObject(arrayOfCriterias[i].concat("}")));
        }
        criteriasList.add(new JSONObject(arrayOfCriterias[arrayOfCriterias.length - 1]));
        return criteriasList;
    }



}
