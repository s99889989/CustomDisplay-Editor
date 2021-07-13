package com.daxton.api;

import com.daxton.page.main.ServerMenuPage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class StringControl {

    //依照類型獲取值
    public static String getValue(Object obj){
        String output = "";
        if(obj instanceof ListView<?>){
            String o = (String) ((ListView<?>) obj).getSelectionModel().getSelectedItem();
            if(o != null){
                return o;
            }
        }
        if(obj instanceof ChoiceBox<?>){
            String o = (String) ((ChoiceBox<?>) obj).getSelectionModel().getSelectedItem();
            if(o != null){
                return o;
            }
        }
        if(obj instanceof TextField){
            String o = ((TextField) obj).getText();
            return o;
        }
        if(obj instanceof CheckBox){
            return String.valueOf(((CheckBox) obj).isSelected());
        }
        return output;
    }
    //依照類型設定值
    public static void setMapValue(Object obj, Map<String, String> inputMap, String[] findKey){
        String messageString = StringConversion.getActionKey(inputMap, findKey);

        if(!messageString.isEmpty()){
            if(obj instanceof ListView<?>){
                ((ListView<String>) obj).getSelectionModel().select(messageString);
            }
            if(obj instanceof ChoiceBox<?>){
                ((ChoiceBox<String>) obj).getSelectionModel().select(messageString);
            }
        }
        if(obj instanceof TextField){
            ((TextField) obj).setText(messageString);
        }
        if(obj instanceof CheckBox){
            ((CheckBox) obj).setSelected(Boolean.parseBoolean(messageString));
        }

    }

    //依照類型設定值
    public static void setValue(Object obj, String value){
        String messageString = value;

        if(!messageString.isEmpty()){
            if(obj instanceof ListView<?>){
                ((ListView<String>) obj).getSelectionModel().select(messageString);
            }
            if(obj instanceof ChoiceBox<?>){
                ((ChoiceBox<String>) obj).getSelectionModel().select(messageString);
            }
        }
        if(obj instanceof TextField){
            ((TextField) obj).setText(messageString);
        }
        if(obj instanceof CheckBox){
            ((CheckBox) obj).setSelected(Boolean.parseBoolean(messageString));
        }

    }

}
