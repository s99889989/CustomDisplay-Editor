package com.daxton.controller.actionmenu.location;

import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_Light {

    @FXML TextField lightLevel;
    @FXML TextField duration;

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();

        ActionMenuPage.keyValue.put("LightLevel", StringControl.getValue(lightLevel));
        ActionMenuPage.keyValue.put("Duration", StringControl.getValue(duration));

        ActionMenuPage.changeActionContnet("Light");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(lightLevel, inputMap, new String[]{"LightLevel", "LL"});
            StringControl.setMapValue(duration, inputMap, new String[]{"Duration", "dt"});

        }
    }

}
