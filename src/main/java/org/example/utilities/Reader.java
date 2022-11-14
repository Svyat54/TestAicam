package org.example.utilities;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

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

    public static LinkedList<JSONObject> getJsonFromFile(String path) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        JSONObject obj = new JSONObject(jsonString);
        Map<String, Object> map = obj.toMap();
        Set<String> set = map.keySet();
        if(set.size() == 1){
            return getCriteriasList(obj);
        }else if(set.size() == 2) {
            return getRangeList(obj);
        }else return null;
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

    private static LinkedList<JSONObject> getRangeList(JSONObject object){
        LinkedList<JSONObject> statList = new LinkedList<>();
        statList.add(object);
        return statList;
    }



}
