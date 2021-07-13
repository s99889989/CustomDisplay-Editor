package com.daxton.controller.actionmenu.meta;

import com.daxton.Main;
import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.util.Map;

public class Action_Break {

    @FXML ListView<String> conditionType;
    @FXML TextField conditionContent;
    @FXML CheckBox mode;

    @FXML//初始設定
    void initialize() {
        if(Main.languageConfig.getConfigurationSection("Condition") != null){
            Main.languageConfig.getConfigurationSection("Condition").getKeys(false).forEach(s -> conditionType.getItems().add(s));
        }
    }

    //改變設定時
    public void onChangeContentFind(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Mode", StringControl.getValue(mode));
        ActionMenuPage.keyValue.put("ConditionType", StringControl.getValue(conditionType));
        ActionMenuPage.keyValue.put("ConditionContent", StringControl.getValue(conditionContent));
        ActionMenuPage.changeActionContnet("Break");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(mode, inputMap, new String[]{"Mode", "m"});
            StringControl.setMapValue(conditionType, inputMap, new String[]{"ConditionType", "ct"});
            StringControl.setMapValue(conditionContent, inputMap, new String[]{"ConditionContent", "cp"});
        }
    }

}
