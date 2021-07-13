package com.daxton.controller.actionmenu.classs;

import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_CustomPoint {

    @FXML TextField amount;
    @FXML ListView<String> typeList;

    @FXML//初始設定
    void initialize() {
        FileSearch.getTypeFileName("Class/Level/").forEach(s -> typeList.getItems().add(s.replace(".yml", "")));
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Type", StringControl.getValue(typeList));
        ActionMenuPage.keyValue.put("Amount", StringControl.getValue(amount));
        ActionMenuPage.changeActionContnet("CustomPoint");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(typeList, inputMap, new String[]{"Type"});
            StringControl.setMapValue(amount, inputMap, new String[]{"Amount"});

        }
    }

}
