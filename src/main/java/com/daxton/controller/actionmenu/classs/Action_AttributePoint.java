package com.daxton.controller.actionmenu.classs;

import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import javafx.fxml.FXML;

import java.util.Map;

public class Action_AttributePoint {

    @FXML//初始設定
    void initialize() {

    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);
            String message = StringConversion.getActionKey(inputMap, new String[]{"m","message"});
            if(!message.isEmpty()){

            }
        }
    }

}
