package com.daxton.controller.actionmenu.entity;

import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Action_LoggerInfo {

    @FXML
    public TextField messageText;

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            String message = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"m","Message"});
            if(!message.isEmpty()){
                messageText.setText(message);
            }
        }
    }

    public void onMessageEnd(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){

            String output = "LoggerInfo[Message="+messageText.getText()+"]";

            ActionMenuPage.actionContent = output;

            ActionMenuPage.setSelectActionContent();
        }


    }

}
