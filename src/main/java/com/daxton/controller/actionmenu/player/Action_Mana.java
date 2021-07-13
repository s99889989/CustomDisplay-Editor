package com.daxton.controller.actionmenu.player;

import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_Mana {

    @FXML TextField manaAmount;

    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Amount", StringControl.getValue(manaAmount));
        ActionMenuPage.changeActionContnet("Mana");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);
            StringControl.setMapValue(manaAmount, inputMap, new String[]{"Amount","a"});
        }
    }

}
