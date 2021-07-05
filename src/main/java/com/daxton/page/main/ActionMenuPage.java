package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;

import java.util.HashMap;
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

    public static void setSelectActionContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            actionMenu.selectActionContnet.setText(actionContent + targetContent);
        }

    }

    public static void changeActionContnet(String acitonType , Map<String, String> keyValue){

        output = acitonType+"[";
        count = 1;
        keyValue.forEach((s, s2) -> {
            if(count > 1){
                output += ";";
            }

            output += s+"="+s2;
            count++;
        });

        output += "]";

        actionContent = output;

        setSelectActionContent();

    }

}
