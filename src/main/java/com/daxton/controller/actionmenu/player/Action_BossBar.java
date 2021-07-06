package com.daxton.controller.actionmenu.player;

import com.daxton.Main;
import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_BossBar {

    @FXML TextField message;
    @FXML ListView<String> style;
    @FXML ListView<String> color;
    @FXML TextField progress;
    @FXML ListView<String> flag;
    @FXML CheckBox delete;
    @FXML TextField mark;

    @FXML//初始設定
    void initialize() {

        if(Main.languageConfig.getConfigurationSection("BarStyle") != null){
            style.getItems().add("");
            Main.languageConfig.getConfigurationSection("BarStyle").getKeys(false).forEach(s -> style.getItems().add(s));
        }
        if(Main.languageConfig.getConfigurationSection("BarColor") != null){
            color.getItems().add("");
            Main.languageConfig.getConfigurationSection("BarColor").getKeys(false).forEach(s -> color.getItems().add(s));
        }
        if(Main.languageConfig.getConfigurationSection("BarFlag") != null){
            flag.getItems().add("");
            Main.languageConfig.getConfigurationSection("BarFlag").getKeys(false).forEach(s -> flag.getItems().add(s));
        }

    }

    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Message", StringControl.getValue(message));
        ActionMenuPage.keyValue.put("Style", StringControl.getValue(style));
        ActionMenuPage.keyValue.put("Color", StringControl.getValue(color));
        ActionMenuPage.keyValue.put("Progress", StringControl.getValue(progress));
        ActionMenuPage.keyValue.put("Flag", StringControl.getValue(flag));
        ActionMenuPage.keyValue.put("Delete", StringControl.getValue(delete));
        ActionMenuPage.keyValue.put("Mark", StringControl.getValue(mark));
        ActionMenuPage.changeActionContnet("BossBar");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setValue(message ,inputMap, new String[]{"m","message"});
            StringControl.setValue(style ,inputMap, new String[]{"s","Style"});
            StringControl.setValue(color ,inputMap, new String[]{"c","Color"});
            StringControl.setValue(progress ,inputMap, new String[]{"p","Progress"});
            StringControl.setValue(flag ,inputMap, new String[]{"f","Flag"});
            StringControl.setValue(delete ,inputMap, new String[]{"d","Delete"});
            StringControl.setValue(mark ,inputMap, new String[]{"mk","Mark"});

        }
    }





}
