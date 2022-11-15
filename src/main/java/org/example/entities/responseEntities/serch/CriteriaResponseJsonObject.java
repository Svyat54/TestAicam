package org.example.entities.responseEntities.serch;

import java.util.LinkedList;

public class CriteriaResponseJsonObject {
    private String type;
    LinkedList<CriteriaResult> results;

    public CriteriaResponseJsonObject(String type, LinkedList<CriteriaResult> results) {
        this.type = type;
        this.results = results;
    }

    @Override
    public String toString() {
        String output = "{" +
                "\n\t\"type\":\"" + type + '\"' +
                ", \n\t\"results\":[";
        for (CriteriaResult result: results)
            output = output.concat("\t\t" + result.toString() + ",");

        output = output.substring(0, output.length() - 1);
        output = output.concat("\n\t]\n}");
        return output;
    }
}
