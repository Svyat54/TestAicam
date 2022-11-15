package org.example.entities.responseEntities.serch;

import org.example.entities.requestEntities.Customer;
import org.json.JSONObject;
import java.util.LinkedList;

public class CriteriaResult {
    JSONObject criteria;
    LinkedList<Customer> results;

    public CriteriaResult(JSONObject criteria, LinkedList<Customer> results) {
        this.criteria = criteria;
        this.results = results;
    }

    @Override
    public String toString() {
        String output = "\n\t\t{" +
                "\n\t\t\t\"criteria\":" + criteria +
                ", \n\t\t\t\"results\":[\n";
        for (Customer customer: results)
            output = output.concat("\t\t\t\t" + customer.toString() + ",\n");

        output = output.substring(0, output.length() - 2);
        output = output.concat("\n\t\t\t]\n\t\t}");
        return output;
    }
}
