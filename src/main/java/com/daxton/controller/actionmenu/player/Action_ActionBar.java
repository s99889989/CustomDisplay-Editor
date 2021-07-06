package com.daxton.controller.actionmenu.player;

import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_ActionBar {

    @FXML TextField message;
    @FXML CheckBox remove;

    public void onChangeContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            if(!message.getText().isEmpty()){
                String output = "ActionBar[Message="+message.getText()+";Remove="+remove.isSelected()+"]";

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
            Map<String, String> inputMap = FileSearch.setClassAction(input);
            String messageString = StringConversion.getActionKey(inputMap, new String[]{"m","message"});
            if(!messageString.isEmpty()){
                message.setText(messageString);
            }
            String removeString = StringConversion.getActionKey(inputMap, new String[]{"r","Remove"});
            if(!removeString.isEmpty()){
                remove.setSelected(Boolean.parseBoolean(removeString));
            }
        }
    }

}
