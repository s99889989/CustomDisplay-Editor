package com.daxton.controller.actionmenu.entity;

import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Action_Damage {

    @FXML
    public TextField damageAmount;

    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            String message = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"a","amount"});
            if(!message.isEmpty()){
                damageAmount.setText(message);
            }
        }

    }

    public void onMessageEnd(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");

        String s = actionMenu.actionMenuList.getSelectionModel().getSelectedItem();

        String output = "Damage[Amount="+damageAmount.getText()+"]";

        ActionMenuPage.actionContent = output;

        ActionMenuPage.setSelectActionContent();

    }

}
