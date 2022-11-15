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
    public static void response(LinkedList<JSONObject> list) throws IOException {
        String url = "jdbc:postgresql://localhost:5432/DatabasesTestTask";
        String name = "root";
        String password = "rootroot1";
        DbShopAgent agent = new DbShopAgent(url, name, password);

        String type = "search";
        LinkedList<CriteriaResult> results = new LinkedList<>();
        for(JSONObject object : list){
            LinkedList<Customer> resultsCustomers = agent.getCustomersList(object);
            results.add(new CriteriaResult(object, resultsCustomers));
        }
        CriteriaResponseJsonObject object = new CriteriaResponseJsonObject(type, results);
        Writer.writeJsonToFile(object);
    }
}
