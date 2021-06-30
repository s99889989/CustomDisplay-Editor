package com.daxton.api;

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

    public static String getActionKey(Map<String ,String> stringStringMap, String[] findKey){
        String output = "";
        for(String key : findKey){
            if(stringStringMap.get(key) != null){
                output = stringStringMap.get(key);
            }
        }
        return output;
    }

}
