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

public class Action_FixedPoint {

    @FXML ListView<String> actionType;
    @FXML TextField actionFind;
    @FXML ListView<String> actionList;
    @FXML ListView<String> actionContent;
    @FXML TextField startAction;
    @FXML TextField timeAction;
    @FXML TextField hitAction;
    @FXML TextField endAction;
    @FXML TextField duration;
    @FXML TextField period;
    @FXML TextField hitRange;
    @FXML TextField hitCount;
    @FXML CheckBox death;


    @FXML//初始設定
    void initialize() {
        FileSearch.getTypeFileKey("Actions/").forEach(s -> actionType.getItems().add(s.substring(8)));
    }


    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("onStart", StringControl.getValue(startAction));
        ActionMenuPage.keyValue.put("onTime", StringControl.getValue(timeAction));
        ActionMenuPage.keyValue.put("onHit", StringControl.getValue(hitAction));
        ActionMenuPage.keyValue.put("onEnd", StringControl.getValue(endAction));
        ActionMenuPage.keyValue.put("Duration", StringControl.getValue(duration));
        ActionMenuPage.keyValue.put("Period", StringControl.getValue(period));
        ActionMenuPage.keyValue.put("HitRange", StringControl.getValue(hitRange));
        ActionMenuPage.keyValue.put("HitCount", StringControl.getValue(hitCount));
        ActionMenuPage.keyValue.put("SelfDead", StringControl.getValue(death));
        ActionMenuPage.changeActionContnet("FixedPoint");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(startAction, inputMap, new String[]{"onStart"});
            StringControl.setMapValue(timeAction, inputMap, new String[]{"onTime"});
            StringControl.setMapValue(hitAction, inputMap, new String[]{"onHit"});
            StringControl.setMapValue(endAction, inputMap, new String[]{"onEnd"});
            StringControl.setMapValue(duration, inputMap, new String[]{"duration"});
            StringControl.setMapValue(period, inputMap, new String[]{"period"});
            StringControl.setMapValue(hitRange, inputMap, new String[]{"HitRange"});
            StringControl.setMapValue(hitCount, inputMap, new String[]{"HitCount"});
            StringControl.setMapValue(death, inputMap, new String[]{"selfDead"});

        }
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

    //選擇起始動作
    public void choiceStartAction(){
        startAction.setText(actionList.getSelectionModel().getSelectedItem());
        onChangeContent();
    }
    //選擇運行中動作
    public void choiceTimeAction(){
        timeAction.setText(actionList.getSelectionModel().getSelectedItem());
        onChangeContent();
    }
    //選擇命中動作
    public void choiceHitAction(){
        hitAction.setText(actionList.getSelectionModel().getSelectedItem());
        onChangeContent();
    }
    //選擇最後動作
    public void choiceEndAction(){
        endAction.setText(actionList.getSelectionModel().getSelectedItem());
        onChangeContent();
    }

}
