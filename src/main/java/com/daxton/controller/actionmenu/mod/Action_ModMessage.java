package com.daxton.controller.actionmenu.mod;

import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_ModMessage {

    @FXML ChoiceBox<String> position;
    @FXML TextField message;

    @FXML//初始設定
    void initialize() {
        position.getItems().add("1");
        position.getItems().add("2");
        position.getItems().add("3");
        position.getItems().add("4");
        position.getItems().add("5");
        position.getItems().add("6");

        position.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            position.getSelectionModel().select(position.getItems().get(newValue.intValue()));
            onChangeContent();
        });
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();

        ActionMenuPage.keyValue.put("Position", StringControl.getValue(position));
        ActionMenuPage.keyValue.put("Message", StringControl.getValue(message));

        ActionMenuPage.changeActionContnet("ModMessage");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(message, inputMap, new String[]{"Message", "m"});
            StringControl.setMapValue(position, inputMap, new String[]{"Position", "p"});

        }
    }

}
