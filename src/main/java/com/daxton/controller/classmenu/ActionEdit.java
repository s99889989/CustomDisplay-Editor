package com.daxton.controller.classmenu;

import com.daxton.Main;
import com.daxton.api.StringControl;
import com.daxton.config.FileConfig;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ClassMenu;
import com.daxton.function.Manager;
import com.daxton.page.classmenu.ActionOption;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActionEdit {

    @FXML public ListView<String> actionList;
    @FXML TextField actionFileText;
    @FXML public ListView<String> actionContentList;
    @FXML public TextField actionContentText;
    @FXML ListView<String> actionFileList;
    @FXML ListView<String> actionUseList;
    @FXML ListView<String> actionTriggerList;

    @FXML//初始設定
    void initialize() {
        FileSearch.getTypeFileKey("Actions/").forEach(s -> actionFileList.getItems().add(s));
        if(FileConfig.languageConfig.getConfigurationSection("Trigger") != null){
            FileConfig.languageConfig.getConfigurationSection("Trigger").getKeys(false).forEach(s -> actionTriggerList.getItems().add(s));
        }
    }
    //當選擇動作文件
    public void onSelectActionFile(){
        String actionName = actionFileList.getSelectionModel().getSelectedItem();
        if(Manager.file_Config_Map.get(actionName) != null){
            FileConfiguration actionConfig = Manager.file_Config_Map.get(actionName);
            actionUseList.getItems().clear();
            if(actionConfig.getConfigurationSection("") != null){
                actionConfig.getConfigurationSection("").getKeys(false).forEach(s -> actionUseList.getItems().add(s));
            }

        }
    }

    //當選擇動作內容時
    public void onSelectActionContent(){
        String actionKey = actionContentList.getSelectionModel().getSelectedItem();
        actionContentText.setText(actionKey);

        Map<String, String> actionMap = FileSearch.setClassAction(actionKey);
        if(actionMap.get("triggerkey") != null){
            String targetKey = actionMap.get("triggerkey");
            //actionContentText.setText(targetKey);
            StringControl.setValue(actionTriggerList, targetKey);
            actionTriggerList.scrollTo(actionTriggerList.getSelectionModel().getSelectedIndex());
        }
        StringControl.setMapValue(actionUseList, actionMap, new String[]{"Action", "a"});
        actionUseList.scrollTo(actionUseList.getSelectionModel().getSelectedIndex());
    }
    //增加動作
    public void addAction(){
        actionContentList.getItems().add(actionContentText.getText());
        saveConfig();
    }
    //編輯動作
    public void editAction(){
        actionContentList.getItems().set(actionContentList.getSelectionModel().getSelectedIndex(), actionContentText.getText());
        saveConfig();
    }
    //移除動作
    public void removeAction(){
        actionContentList.getItems().remove(actionContentList.getSelectionModel().getSelectedIndex());
        saveConfig();
    }
    //儲存文件
    public void saveConfig(){
        String actionName = actionList.getSelectionModel().getSelectedItem();
        if(Manager.file_Config_Map.get("Class/Action/"+actionName+".yml") != null){
            FileConfiguration actionConfig = Manager.file_Config_Map.get("Class/Action/"+actionName+".yml");
            List<String> aList = new ArrayList<>();
            aList.addAll(actionContentList.getItems());
            actionConfig.set("Action", aList);
        }
    }

    //改變設定時
    public void onChangeContent(){
        ActionOption.keyValue.clear();
        ActionOption.targetContent = StringControl.getValue(actionTriggerList);
        ActionOption.keyValue.put("Action", StringControl.getValue(actionUseList));
        ActionOption.changeActionContnet("Action");
    }

    //-----------------------------------------------------------------------------------------------//

    //當選擇動作列表，顯示動作內容列表
    public void onActionList(){
        String actionName = actionList.getSelectionModel().getSelectedItem();
        if(Manager.file_Config_Map.get("Class/Action/"+actionName+".yml") != null){
            FileConfiguration actionConfig = Manager.file_Config_Map.get("Class/Action/"+actionName+".yml");
            actionContentList.getItems().clear();
            actionConfig.getStringList("Action").forEach(s -> actionContentList.getItems().add(s));
        }
    }

    //添加動作檔案
    public void addActionFile(){
        if(!actionFileText.getText().isEmpty()){
            actionList.getItems().add(actionFileText.getText());
            File file = new File(Main.getOpenPath()+"/Class/Action/"+actionFileText.getText()+".yml");
            if(!file.exists()){
                try {
                    if(file.createNewFile()){
                        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
                        Manager.file_Config_Map.put("Class/Action/"+actionFileText.getText()+".yml", fileConfiguration);
                        Manager.file_Name_Map.put("Class/Action/"+actionFileText.getText()+".yml", actionFileText.getText()+".yml");
                    }
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        }

    }
    //編輯動作檔案
    public void editActionFile(){
        actionList.getItems().set(actionList.getSelectionModel().getSelectedIndex(), actionFileText.getText());
    }
    //移除動作檔案
    public void removeActionFile(){
        if(actionList.getSelectionModel().getSelectedItem() != null){
            actionList.getItems().remove(actionList.getSelectionModel().getSelectedIndex());
            String actionText = actionList.getSelectionModel().getSelectedItem();
            File file = new File(Main.getOpenPath()+"/Class/Action/"+actionText+".yml");
            if(file.exists()){
                if(file.delete()){
                    Manager.file_Config_Map.remove("Class/Action/"+actionText+".yml");
                    Manager.file_Name_Map.remove("Class/Action/"+actionText+".yml");
                }
            }


        }
    }



}
