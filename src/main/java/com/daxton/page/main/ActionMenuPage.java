package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


public class ActionMenuPage {

    public static String actionContent = "";
    public static String targetContent = "";

    public static Map<String,String> keyValue = new LinkedHashMap<>();
    public static String output;
    public static int count;

    //打開動作編輯介面
    public static void display(){
        ActionMenu actionMenu = FxmlLoader.display("/page/main/ActionMenu.fxml", Main.mainWindow);
        if(actionMenu != null){
            Manager.controller_Map.put("ActionMenu", actionMenu);
            Main.mainMenuName = "ActionMenu";
            //-----------------------//
            FileSearch.getTypeFileKey("Actions/").forEach(s -> actionMenu.actionTypeList.getItems().add(s.substring(8)));

            Objects.requireNonNull(Main.languageConfig.getConfigurationSection("Actions")).getKeys(false).forEach(s -> actionMenu.actionMenuList.getItems().add(s));

        }

    }
    //重整動作內容
    public static void setSelectActionContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            actionMenu.selectActionContnet.setText(actionContent + " " + targetContent);
        }

    }
    //改變動作內容
    public static void changeActionContnet(String acitonType){

        output = acitonType+"[";
        count = 1;
        keyValue.forEach((s, s2) -> {
            if(!s2.isEmpty()){
                if(count > 1){
                    output += ";";
                }
                output += s+"="+s2;
                count++;
            }
        });

        output += "]";

        actionContent = output;

        setSelectActionContent();

    }

    //依照類型獲取值
    public static String getValue(Object obj){
        String output = "";
        if(obj instanceof ListView<?>){
            String o = (String) ((ListView<?>) obj).getSelectionModel().getSelectedItem();
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
    public static void setValue(Object obj, Map<String, String> inputMap, String[] findKey){
        String messageString = StringConversion.getActionKey(inputMap, findKey);
        if(!messageString.isEmpty()){
            if(obj instanceof ListView<?>){
                ((ListView<String>) obj).getSelectionModel().select(messageString);
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
