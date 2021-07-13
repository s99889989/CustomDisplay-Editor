package com.daxton.controller.actionmenu.meta;

import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;

public class Action_Action {

    @FXML ListView<String> actionType;
    @FXML TextField actionFind;
    @FXML ListView<String> actionList;
    @FXML ListView<String> actionContent;
    @FXML TextField mark;
    @FXML TextField count;
    @FXML TextField deley;
    @FXML CheckBox needTarget;
    @FXML CheckBox stop;

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
            onChangeContentFind();
        }
    }

    //改變設定時
    public void onChangeContentFind(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Action", StringControl.getValue(actionList));
        ActionMenuPage.keyValue.put("NeedTarget", StringControl.getValue(needTarget));
        ActionMenuPage.keyValue.put("Count", StringControl.getValue(count));
        ActionMenuPage.keyValue.put("CountPeriod", StringControl.getValue(deley));
        ActionMenuPage.keyValue.put("Mark", StringControl.getValue(mark));
        ActionMenuPage.keyValue.put("Stop", StringControl.getValue(stop));
        ActionMenuPage.changeActionContnet("Action");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);


            StringControl.setMapValue(actionList, inputMap, new String[]{"Action", "a"});
            StringControl.setMapValue(needTarget, inputMap, new String[]{"NeedTarget", "nt"});
            StringControl.setMapValue(count, inputMap, new String[]{"Count", "c"});
            StringControl.setMapValue(deley, inputMap, new String[]{"CountPeriod", "cp"});
            StringControl.setMapValue(mark, inputMap, new String[]{"Mark", "m"});
            StringControl.setMapValue(stop, inputMap, new String[]{"stop", "s"});

        }
    }

}
