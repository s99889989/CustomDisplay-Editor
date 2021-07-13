package com.daxton.controller.actionmenu.player;

import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_Title {

    @FXML TextField title;
    @FXML TextField subtitle;
    @FXML TextField fadeIn;
    @FXML TextField duration;
    @FXML TextField fadeOut;

    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Title", StringControl.getValue(title));
        ActionMenuPage.keyValue.put("Subtitle", StringControl.getValue(subtitle));
        ActionMenuPage.keyValue.put("FadeIn", StringControl.getValue(fadeIn));
        ActionMenuPage.keyValue.put("Duration", StringControl.getValue(duration));
        ActionMenuPage.keyValue.put("FadeOut", StringControl.getValue(fadeOut));
        ActionMenuPage.changeActionContnet("Title");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);
            StringControl.setMapValue(title, inputMap, new String[]{"title","t"});
            StringControl.setMapValue(subtitle, inputMap, new String[]{"subtitle","s"});
            StringControl.setMapValue(fadeIn, inputMap, new String[]{"fadeIn","fi"});
            StringControl.setMapValue(duration, inputMap, new String[]{"duration","d"});
            StringControl.setMapValue(fadeOut, inputMap, new String[]{"fadeOut","fo"});
        }
    }

}
