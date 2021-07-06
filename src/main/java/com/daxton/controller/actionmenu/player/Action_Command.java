package com.daxton.controller.actionmenu.player;

import com.daxton.api.StringControl;
import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_Command {

    @FXML TextField message;
    @FXML CheckBox console;

    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Message", StringControl.getValue(message));
        ActionMenuPage.keyValue.put("Consoles", StringControl.getValue(console));
        ActionMenuPage.changeActionContnet("Command");

    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setValue(message ,inputMap, new String[]{"m","message"});
            StringControl.setValue(console ,inputMap, new String[]{"c","consoles"});

        }
    }

}
