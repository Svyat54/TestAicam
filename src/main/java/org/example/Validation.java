package org.example;

import org.example.exeption.InputParameterDate;
import org.example.exeption.InputParameterName;
import org.example.exeption.InputParameterValue;
import org.json.JSONObject;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class Validation {
    public static void isInputDataValid (JSONObject jsonObject) throws IOException {
        int type = getRequestNameValidation(jsonObject);
        if(type == -1){
            throw new InputParameterName("Error lock file output");
        } else
            getRequestValueParameterValidation(jsonObject, type);
    }

    public static void getRequestValueParameterValidation(JSONObject object, int type) throws IOException {
        if(type == 2){
            try{
                 object.getInt("minTimes");
            } catch (RuntimeException e) {
                throw new InputParameterValue("Error lock file output");
            }
        }else if(type == 3){
            try{
                object.getInt("minExpenses");
                object.getInt("maxExpenses");
            } catch (RuntimeException e) {
                throw new InputParameterValue("Error lock file output");
            }
        } else if(type == 4){
            try{
                object.getInt("badCustomers");
            } catch (RuntimeException e) {
                throw new InputParameterValue("Error lock file output");
            }
        } else if (type == 5){
            try{
                LocalDate.parse(object.getString("startDate"));
                LocalDate.parse(object.getString("endDate"));
            } catch (RuntimeException e) {
                throw new InputParameterDate("Error lock file output");
            }
        }
    }

    public static int getRequestNameValidation(JSONObject object){
        Map<String, Object> map = object.toMap();
        Set<String> set = map.keySet();
        if(set.size() == 1){
            return getRequestSingleParameterNameValidation(set);
        }
        else
            return getRequestDoubleParameterNameValidation(set);
    }
    private static int getRequestSingleParameterNameValidation(Set<String> set) {
        if(set.contains("lastName"))
            return 1;
        else if(set.contains("badCustomers"))
            return 4;
        return -1;
    }
    private static int getRequestDoubleParameterNameValidation(Set<String> set) {
        if(set.contains("productName") && set.contains("minTimes"))
            return 2;
        else if(set.contains("minExpenses") && set.contains("maxExpenses"))
            return 3;
        else if(set.contains("startDate") && set.contains("endDate"))
            return 5;
        return -1;
    }
}
