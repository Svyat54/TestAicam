package org.example.utilities.queryParser;

import org.example.DB.DbShopAgent;
import org.example.entities.requestEntities.Customer;
import org.example.entities.responseEntities.serch.CriteriaResponseJsonObject;
import org.example.entities.responseEntities.serch.CriteriaResult;
import org.example.utilities.Writer;
import org.json.JSONObject;
import java.io.IOException;
import java.util.LinkedList;

public class SearchQueryParser {
    private static CriteriaResult getCriteriaResult(JSONObject object){
        LinkedList<Customer> resultsCustomers = new DbShopAgent().getCustomersList(object);
        return new CriteriaResult(object, resultsCustomers);
    }

    public static void response(LinkedList<JSONObject> list) throws IOException {
        Writer.writeJsonToFile(getFinalObject(list));
    }

    private static CriteriaResponseJsonObject getFinalObject(LinkedList<JSONObject> list){
        LinkedList<CriteriaResult> results = new LinkedList<>();
        for(JSONObject object : list){
           results.add(getCriteriaResult(object));
        }
        return new CriteriaResponseJsonObject("search", results);
    }
}