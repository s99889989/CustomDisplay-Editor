package com.daxton.controller.actionmenu.player;

import com.daxton.api.StringControl;
import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_Inventory {

    @FXML ChoiceBox<String> function;
    @FXML TextField message;
    @FXML ChoiceBox<String> amount;
    @FXML ListView<String> guiID;

    @FXML//初始設定
    void initialize() {
        guiID.getItems().add("");
        FileSearch.getTypeFileKey("Gui/Menu").forEach(s -> guiID.getItems().add(s.replace("Gui/Menu/","").replace("/","_").replace(".yml","")));
        function.getItems().add("INV");
        function.getItems().add("GUI");
        function.getItems().add("Close");
        function.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            function.getSelectionModel().select(function.getItems().get(newValue.intValue()));
            onChangeContent();
        });

        amount.getItems().add("");
        amount.getItems().add("9");
        amount.getItems().add("18");
        amount.getItems().add("27");
        amount.getItems().add("36");
        amount.getItems().add("45");
        amount.getItems().add("54");
        amount.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            amount.getSelectionModel().select(amount.getItems().get(newValue.intValue()));
            onChangeContent();
        });
    }

    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Function", StringControl.getValue(function));
        ActionMenuPage.keyValue.put("Message", StringControl.getValue(message));
        ActionMenuPage.keyValue.put("Amount", StringControl.getValue(amount));
        ActionMenuPage.keyValue.put("GuiID", StringControl.getValue(guiID));
        ActionMenuPage.changeActionContnet("Inventory");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setValue(function, inputMap, new String[]{"fc", "Function"});
            StringControl.setValue(message, inputMap, new String[]{"m", "Message"});
            StringControl.setValue(amount, inputMap, new String[]{"a", "Amount"});
            StringControl.setValue(guiID, inputMap, new String[]{"GuiID"});

        }
    }

}
