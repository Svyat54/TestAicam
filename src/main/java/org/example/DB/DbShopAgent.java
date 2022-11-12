package org.example.DB;
import org.example.entities.Customer;
import org.example.entities.Order;
import org.example.entities.Product;

import java.sql.*;
import java.util.LinkedList;

public class DbShopAgent {
    private String url;
    private String name;
    private String password;
    private Connection connection;
    LinkedList<Customer> customersList;
    LinkedList<Product> productsList;
    LinkedList<Order> ordersList;

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

    public LinkedList<Customer> getCustomersList(){
        customersList = new LinkedList<>();
        String query = "SELECT name, lastname FROM customers;";
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

}
