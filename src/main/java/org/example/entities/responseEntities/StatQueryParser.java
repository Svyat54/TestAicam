package org.example.entities.responseEntities;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

public class StatQueryParser {
    public static LinkedList<LinkedList<Record>> getListOfRecordsByFullName(LinkedList<Record> allRecordsByPeriod) {
        LinkedList<LinkedList<Record>> recordsListsByName = new LinkedList<>();
        while (!allRecordsByPeriod.isEmpty())
            recordsListsByName.add(recordsByPersonByPeriod(allRecordsByPeriod));
        recordsListsByName.sort(new SortByExpenses());
        return recordsListsByName;
    }

    private static LinkedList<Record> recordsByPersonByPeriod(LinkedList<Record> allRecordsByPeriod){
        LinkedList<Record> recordsListByPerson = new LinkedList<>();
        recordsListByPerson.add(allRecordsByPeriod.pollFirst());
        for (int i = 0; i < allRecordsByPeriod.size(); i++) {
            if (recordsListByPerson.getFirst().getFullName().equals(allRecordsByPeriod.get(i).getFullName())) {
                recordsListByPerson.add(allRecordsByPeriod.get(i));
                allRecordsByPeriod.remove(i);
                i--;
            }
        }
        return recordsListByPerson;
    }

    static class SortByExpenses implements Comparator<LinkedList<Record>> {
        public int compare(LinkedList<Record> a, LinkedList<Record> b){
            return totalExpensesByPerson(b) - totalExpensesByPerson(a);
        }
    }

    private static int totalExpensesByPerson(LinkedList<Record> list){
        int totalExpenses = 0;
        for(Record record : list){
            totalExpenses += record.getExpenses();
        }
        return totalExpenses;
    }

    private static Record getRecordFromJson(JSONObject jsonObject){
        return new Record(jsonObject.getString("fullName"), jsonObject.getString("productName"),
                jsonObject.getInt("expenses"));
    }
}
