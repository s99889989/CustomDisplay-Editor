package com.daxton.controller.actionmenu.meta;

import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;

public class Action_OrbitalAction {

    @FXML ListView<String> actionType;
    @FXML TextField actionFind;
    @FXML ListView<String> actionList;
    @FXML ListView<String> actionContent;


    @FXML//初始設定
    void initialize() {
        FileSearch.getTypeFileKey("Actions/").forEach(s -> actionType.getItems().add(s.substring(8)));
    }



    //當選擇動作類型
    public void onChoiceType(){
        String type = actionType.getSelectionModel().getSelectedItem();
        if(Manager.file_Config_Map.get("Actions/"+type) != null){
            FileConfiguration actionConfig = Manager.file_Config_Map.get("Actions/"+type);
            if(actionConfig.getConfigurationSection("") != null){
                actionList.getItems().clear();
                actionConfig.getConfigurationSection("").getKeys(false).forEach(s -> actionList.getItems().add(s));
            }
        }
    }

    //尋找有關動作
    public void actionFind(){
        String type = actionType.getSelectionModel().getSelectedItem();
        if(Manager.file_Config_Map.get("Actions/"+type) != null){
            FileConfiguration actionConfig = Manager.file_Config_Map.get("Actions/"+type);
            if(actionConfig.getConfigurationSection("") != null){
                actionList.getItems().clear();
                actionConfig.getConfigurationSection("").getKeys(false).forEach(s -> {
                    if(s.contains(actionFind.getText()))
                        actionList.getItems().add(s);
                });
            }
        }
    }
    //當選擇動作
    public void onChoiceAction(){
        String actionString = actionList.getSelectionModel().getSelectedItem();
        String type = actionType.getSelectionModel().getSelectedItem();
        if(Manager.file_Config_Map.get("Actions/"+type) != null){
            FileConfiguration actionConfig = Manager.file_Config_Map.get("Actions/"+type);
            actionContent.getItems().clear();
            actionConfig.getStringList(actionString+".Action").forEach(s -> actionContent.getItems().add(s));
        }
    }



}
