package org.example.utilities.queryParser;

import org.example.DB.DbShopAgent;
import org.example.entities.responseEntities.stat.Record;
import org.example.entities.responseEntities.stat.ResponseCustomer;
import org.example.entities.responseEntities.stat.ResponseJsonObject;
import org.example.entities.responseEntities.stat.ResponsePurchase;
import org.example.utilities.Writer;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.LinkedList;

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

    public static LinkedList<ResponseCustomer> getOutputList(LinkedList<LinkedList<Record>> list){
        LinkedList<ResponseCustomer> outputList = new LinkedList<>();
        for(LinkedList<Record> customer : list){
            outputList.add(getResponseCustomerFromRecordList(customer));
        }
        return outputList;
    }

    private static ResponseCustomer getResponseCustomerFromRecordList(LinkedList<Record> list){
        String fullName = list.get(0).getFullName();
        int totalExpenses = totalExpensesByPerson(list);
        LinkedList<ResponsePurchase> purchases = new LinkedList<>();
        for(Record record : list){
            purchases.add(new ResponsePurchase(record.getProductName(), record.getExpenses()));
        }
        return new ResponseCustomer(fullName, purchases, totalExpenses);
    }

    public static void response(LinkedList<JSONObject> list) throws IOException {
        String url = "jdbc:postgresql://localhost:5432/DatabasesTestTask";
        String name = "root";
        String password = "rootroot1";
        DbShopAgent agent = new DbShopAgent(url, name, password);

        ResponseJsonObject jsonObject = getFinalObject(agent, list);
        Writer.writeJsonToFile(jsonObject);
    }

    private static ResponseJsonObject getFinalObject(DbShopAgent agent, LinkedList<JSONObject> list) {
        LinkedList<Record> allRecordsByPeriod = agent.getRecordsList(list.get(0));
        LinkedList<LinkedList<Record>> recordsList = StatQueryParser.getListOfRecordsByFullName(allRecordsByPeriod);
        String type = "stat";
        int totalDays = getTotalDaysFromJson(list.get(0));
        LinkedList<ResponseCustomer> customers = StatQueryParser.getOutputList(recordsList);
        int totalExpenses = getTotalExpensesByAllCustomers(customers);
        double avgExpenses = (double) totalExpenses / customers.size();
        return new ResponseJsonObject(type, totalDays, customers, totalExpenses, avgExpenses);
    }

    public static int getTotalExpensesByAllCustomers(LinkedList<ResponseCustomer> customers){
        int totalExpenses = 0;
        for(ResponseCustomer customer : customers){
            totalExpenses += customer.getTotalExpenses();
        }
        return totalExpenses;
    }

    private static int getTotalDaysFromJson(JSONObject object){
        LocalDate startDate = LocalDate.parse(object.getString("startDate"));
        LocalDate endDate = LocalDate.parse(object.getString("endDate"));
        return Math.abs(Period.between(startDate, endDate).getDays()) + 1;
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
}
