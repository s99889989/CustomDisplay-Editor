package com.daxton.controller.actionmenu.meta;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
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

public class Action_LocPng {

    @FXML ListView<String> actionType;
    @FXML TextField actionFind;
    @FXML ListView<String> actionList;
    @FXML ListView<String> actionContent;
    @FXML TextField startAction;
    @FXML TextField timeAction;
    @FXML TextField endAction;
    @FXML TextField duration;
    @FXML TextField period;

    @FXML//初始設定
    void initialize() {
        FileSearch.getTypeFileKey("Actions/").forEach(s -> actionType.getItems().add(s.substring(8)));
    }

    public void openPNGSettings(){
        Action_LocPNGPNG actionMenu = FxmlLoader.display("/page/actionmenu/meta/Action_LocPngPNG.fxml", Main.secondaryWindow);
        if(actionMenu != null){
            Manager.controller_Map.put("Action_LocPngPNG", actionMenu);
        }
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("onStart", StringControl.getValue(startAction));
        ActionMenuPage.keyValue.put("onTime", StringControl.getValue(timeAction));
        ActionMenuPage.keyValue.put("onEnd", StringControl.getValue(endAction));
        ActionMenuPage.keyValue.put("Duration", StringControl.getValue(duration));
        ActionMenuPage.keyValue.put("Period", StringControl.getValue(period));
        Action_LocPNGPNG actionMenu = (Action_LocPNGPNG) Manager.controller_Map.get("Action_LocPngPNG");
        if(actionMenu != null){
            ActionMenuPage.keyValue.put("IMG", StringControl.getValue(actionMenu.pngList));
            ActionMenuPage.keyValue.put("IMGSize", StringControl.getValue(actionMenu.size));
            if(!StringControl.getValue(actionMenu.bast).isEmpty() && !StringControl.getValue(actionMenu.rX).isEmpty() && !StringControl.getValue(actionMenu.rY).isEmpty() && !StringControl.getValue(actionMenu.rZ).isEmpty())
                ActionMenuPage.keyValue.put("IMGRotangle", StringControl.getValue(actionMenu.bast)+"|"+StringControl.getValue(actionMenu.addPitch)+"|"+StringControl.getValue(actionMenu.addYaw)+"|"+StringControl.getValue(actionMenu.rX)+"|"+StringControl.getValue(actionMenu.rY)+"|"+StringControl.getValue(actionMenu.rZ));
        }
        ActionMenuPage.changeActionContnet("LocPng");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(startAction, inputMap, new String[]{"onStart"});
            StringControl.setMapValue(timeAction, inputMap, new String[]{"onTime"});
            StringControl.setMapValue(endAction, inputMap, new String[]{"onEnd"});
            StringControl.setMapValue(duration, inputMap, new String[]{"duration"});
            StringControl.setMapValue(period, inputMap, new String[]{"period"});


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
    //選擇最後動作
    public void choiceEndAction(){
        endAction.setText(actionList.getSelectionModel().getSelectedItem());
        onChangeContent();
    }

}
