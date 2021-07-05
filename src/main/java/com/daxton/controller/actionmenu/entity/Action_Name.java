package com.daxton.controller.actionmenu.entity;

import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Action_Name {

    @FXML
    public TextField showName;
    @FXML public CheckBox alawayShow;
    //改變內容就更改總內容
    public void changeContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            if(!showName.getText().isEmpty()){
                String output = "Name[Message="+showName.getText()+";Always="+alawayShow.isSelected()+"]";

                ActionMenuPage.actionContent = output;

                ActionMenuPage.setSelectActionContent();
            }

        }
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            String message = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"m","Message"});
            if(!message.isEmpty()){
                showName.setText(message);
            }
            String al = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"a","Always"});
            alawayShow.setSelected(Boolean.parseBoolean(al));
        }
    }

}
