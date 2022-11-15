package org.example.DB;
import org.example.entities.requestEntities.Customer;
import org.example.entities.responseEntities.stat.Record;
import org.json.JSONObject;
import java.sql.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class DbShopAgent {
    private String url;
    private String name;
    private String password;
    private Connection connection;

    public DbShopAgent() {
        this.url = "jdbc:postgresql://localhost:5432/DatabasesTestTask";
        this.name = "root";
        this.password = "rootroot1";
        try{
            this.connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public LinkedList<Customer> getCustomersList(JSONObject object){
        LinkedList<Customer> customersList = new LinkedList<>();
        String query = getQueryFromJson(object);
        try{
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String name = cutString(resultSet.getString("name"));
                String lastName = cutString(resultSet.getString("lastName"));
                customersList.add(new Customer(name, lastName));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customersList;
    }

    public LinkedList<Record> getRecordsList(JSONObject object){
        LinkedList<Record> recordsList = new LinkedList<>();
        String query = getStatQuery(object);
        try{
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String fullName = cutString(resultSet.getString("fullname"));
                String productName = cutString(resultSet.getString("productname"));
                Integer expenses = Integer.valueOf((resultSet.getString("expenses")));
                recordsList.add(new Record(fullName, productName, expenses));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recordsList;
    }

    private String cutString(String str){
        if(!str.contains("   ")) return str;
        else return str.substring(0, str.indexOf("  "));
    }

    public static int getRequestType(JSONObject object){
        Map<String, Object> map = object.toMap();
        Set<String> set = map.keySet();
        if(set.size() == 1){
            if(set.contains("lastName")){
                return 1;
            }else{
                return 4;
            }
        } else if(set.contains("productName")) {
            return 2;
        }else {
            return 3;
        }
    }

    private String getQueryFromJson(JSONObject object){
        if(getRequestType(object) == 1){
            return getType1Query(object);
        } else if(getRequestType(object) == 2){
            return getType2Query(object);
        } else if(getRequestType(object) == 3){
            return getType3Query(object);
        } else if(getRequestType(object) == 4){
            return getType4Query(object);
        }
        return getStatQuery(object);
    }

    public static String getType1Query(JSONObject object){
        return "SELECT lastname, name FROM customers WHERE lastname = " + "'" +object.get("lastName") +"';";
    }

    public static String getType2Query(JSONObject object){
        return "SELECT lastname, name FROM customers WHERE id IN (SELECT customerid FROM (SELECT COUNT(customerid) " +
                "As COUNT, customerid FROM orders WHERE productid =(SELECT id FROM products WHERE " +
                "name = \'" + object.get("productName") + "\') GROUP BY customerid) AS SUB WHERE " +
                "COUNT > " + object.get("minTimes") + ");";
    }

    public static String getType3Query(JSONObject object){
        return "SELECT lastname, name FROM customers WHERE id IN(\n" +
                "SELECT sub.customerid FROM (SELECT customerid, productid, priсe  FROM" +
                " orders JOIN products p on orders.productid = p.id\n" +
                "WHERE customerid IN (SELECT id FROM customers)) AS SUB group by sub.customerid " +
                "HAVING SUM(sub.priсe) BETWEEN "+ object.get("minExpenses") + " AND " + object.get("maxExpenses") +");";
    }

    public static String getType4Query(JSONObject object){
        return "SELECT lastname, name FROM customers WHERE id IN(\n" +
                "SELECT customerid FROM orders group by customerid " +
                "ORDER BY COUNT(customerid) limit " + object.get("badCustomers") + ");";
    }

    public static String getStatQuery(JSONObject object){
        return "SELECT c.lastname || ' ' || c.name as FullName, p.name as productName, COUNT(sub.productid) * p.priсe as expenses FROM\n" +
                "(SELECT * FROM orders WHERE orderdate BETWEEN '" + object.getString("startDate") + "' AND '" +
                "" + object.getString("endDate")+ "' AND customerid in (SELECT id FROM customers) order by productid) as sub\n" +
                "JOIN products p on sub.productid = p.id JOIN customers c on sub.customerid = c.id\n" +
                "group by p.name, p.priсe, sub.customerid, c.name, c.lastname, sub.productid ORDER BY expenses DESC;";
    }

}
