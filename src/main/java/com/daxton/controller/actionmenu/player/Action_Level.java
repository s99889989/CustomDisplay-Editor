package com.daxton.controller.actionmenu.player;

import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_Level {

    @FXML ListView<String> levelType;
    @FXML ChoiceBox<String> levelFunction;
    @FXML TextField levelAmount;

    @FXML//初始設定
    void initialize() {
        levelType.getItems().add("");
        FileSearch.getTypeFileName("Class/Level").forEach(s -> levelType.getItems().add(s.replace(".yml","")));

        levelFunction.getItems().add("");
        levelFunction.getItems().add("add");
        levelFunction.getItems().add("set");

        levelFunction.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            levelFunction.getSelectionModel().select(levelFunction.getItems().get(newValue.intValue()));
            onChangeContent();
        });
    }

    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Type", StringControl.getValue(levelType));
        ActionMenuPage.keyValue.put("Function", StringControl.getValue(levelFunction));
        ActionMenuPage.keyValue.put("Amount", StringControl.getValue(levelAmount));
        ActionMenuPage.changeActionContnet("Level");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);
            StringControl.setMapValue(levelType, inputMap, new String[]{"Type"});
            StringControl.setMapValue(levelFunction, inputMap, new String[]{"fc", "Function"});
            StringControl.setMapValue(levelAmount, inputMap, new String[]{"a", "Amount"});
        }
    }

}
