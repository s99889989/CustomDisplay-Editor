package com.daxton.api;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class StringConversion {

    //轉成int
    public static int getInt(int i, String inputString){
        int output;
        if(inputString.contains(".")){
            inputString = inputString.substring(0, inputString.indexOf("."));
        }
        try {
            output = Integer.parseInt(inputString);
        }catch (NumberFormatException exception){
            output = i;
        }
        return output;
    }
    //轉成double
    public static double getDoubel(double i, String inputString){
        double output;
        try {
            output = Double.parseDouble(inputString);
        }catch (NumberFormatException exception){
            output = i;
        }
        return output;
    }

    //轉成boolean
    public static boolean getBoolean(String inputString){
        return Boolean.parseBoolean(inputString);
    }

    //判斷是否是數字
    public static boolean isNumber(String inputString){
        boolean output = true;
        try {
            Double.valueOf(inputString);
        }catch (NumberFormatException exception){
            output = false;
        }
        return output;
    }
    //從動作Map獲取內容
    public static String getActionKey(Map<String ,String> stringStringMap, String[] findKey){
        String output = "";
        for(String key : findKey){
            if(stringStringMap.get(key.toLowerCase()) != null){
                output = stringStringMap.get(key.toLowerCase());
            }
        }
        return output;
    }
    //從動作Map獲取內容並設定是否轉成小寫
    public static String getActionKeyToLow(Map<String ,String> stringStringMap, String[] findKey, boolean toLow){

        String output = "";
        for(String key : findKey){
            if(stringStringMap.get(key.toLowerCase()) != null){
                output = stringStringMap.get(key.toLowerCase());
                if(toLow){
                    output = output.toLowerCase();
                }
            }
        }
        return output;

    }




}
