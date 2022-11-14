package org.example.entities.responseEntities;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Objects;

public class StatQueryParser {
    public static LinkedList<LinkedList<Record>> getListOfRecordsByFullName(LinkedList<Record> allRecordsByPeriod) {
        LinkedList<LinkedList<Record>> recordsListsByName = new LinkedList<>();
        while (!allRecordsByPeriod.isEmpty())
            recordsListsByName.add(recordsByPersonByPeriod(allRecordsByPeriod));
        return recordsListsByName;
    }

    private static LinkedList<Record> recordsByPersonByPeriod(LinkedList<Record> allRecordsByPeriod){
        LinkedList<Record> recordsListByPerson = new LinkedList<>();
        recordsListByPerson.add(allRecordsByPeriod.get(0));
        for (int i = 0; i < allRecordsByPeriod.size(); i++) {
            if (recordsListByPerson.getFirst().getFullName().equals(allRecordsByPeriod.get(i).getFullName())) {
                recordsListByPerson.add(allRecordsByPeriod.get(i));
                allRecordsByPeriod.remove(i);
                i--;
            }
        }
        return recordsListByPerson;
    }

    private static Record getRecordFromJson(JSONObject jsonObject){
        return new Record(jsonObject.getString("fullName"), jsonObject.getString("productName"),
                jsonObject.getInt("expenses"));
    }
}
