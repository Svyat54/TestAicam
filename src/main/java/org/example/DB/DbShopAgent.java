package org.example.DB;
import org.example.entities.Customer;
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
//    private LinkedList<Product> productsList;
//    private LinkedList<Order> ordersList;

    public DbShopAgent(String url, String name, String password) {
        this.url = url;
        this.name = name;
        this.password = password;
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

    private String cutString(String str){
       return str.substring(0, str.indexOf(" "));
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
        }

        return null;
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



}
