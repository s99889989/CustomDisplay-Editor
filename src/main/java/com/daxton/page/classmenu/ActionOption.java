package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileSearch;
import com.daxton.controller.classmenu.ActionEdit;
import com.daxton.controller.main.ClassMenu;
import com.daxton.function.Manager;
import javafx.scene.control.SelectionMode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ActionOption {

    public static String actionContent = "";
    public static String targetContent = "";

    public static Map<String,String> keyValue = new LinkedHashMap<>();
    public static String output;
    public static int count;

    //重整動作內容
    public static void setSelectActionContent(){
        ActionEdit actionEdit = (ActionEdit) Manager.controller_Map.get("ActionEdit");
        if(actionEdit != null){
            actionEdit.actionContentText.setText(actionContent + " " + targetContent);
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

    //打開動作增加介面
    public static void addAction(){
        FxmlLoader.display("/page/classmenu/ActionAdd.fxml", Main.secondaryWindow);
    }

    //打開動作編輯介面
    public static void editAction(){
        ActionEdit actionEdit = FxmlLoader.display("/page/classmenu/ActionEdit.fxml", Main.secondaryWindow);
        if(actionEdit != null){
            Manager.controller_Map.put("ActionEdit", actionEdit);
            FileSearch.getTypeFileName("Class/Action/").forEach(s -> actionEdit.actionList.getItems().add(s.replace(".yml", "")));
            actionEdit.actionList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
    }
    //移除使用動作
    public static void removeAction(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            String selectString = classMenu.actionList.getSelectionModel().getSelectedItem();
            classMenu.actionList.getItems().remove(selectString);

            String nowClassFileName = classMenu.classList.getValue();

            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");

            List<String> actionConfigList = classConfig.getStringList(nowClassFileName+".Action");
            actionConfigList.remove(selectString);
            classConfig.set(nowClassFileName+".Action", actionConfigList);

        }
    }

}
