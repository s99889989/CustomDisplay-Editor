package com.daxton.controller.actionmenu.player;

import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_Exp {

    @FXML TextField amount;
    @FXML ListView<String> type;

    @FXML//初始設定
    void initialize() {
        type.getItems().add("");
        FileSearch.getTypeFileName("Class/Level").forEach(s -> type.getItems().add(s.replace(".yml","")));

    }

    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Amount", StringControl.getValue(amount));
        ActionMenuPage.keyValue.put("Type", StringControl.getValue(type));
        ActionMenuPage.changeActionContnet("Exp");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(amount, inputMap, new String[]{"a","Amount"});
            StringControl.setMapValue(type, inputMap, new String[]{"Type"});
        }
    }

}
