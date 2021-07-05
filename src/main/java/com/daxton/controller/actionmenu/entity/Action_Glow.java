package com.daxton.controller.actionmenu.entity;

import com.daxton.Main;
import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Action_Glow {

    @FXML public ListView<String> colorList;

    @FXML
    void initialize() {
        if(Main.languageConfig.getConfigurationSection("ChatColor") != null){
            Main.languageConfig.getConfigurationSection("ChatColor").getKeys(false).forEach(s -> colorList.getItems().add(s));
        }
    }

    public void onSelectColor(){

        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            if(colorList.getSelectionModel().getSelectedItem() != null){
                String colorStirng = colorList.getSelectionModel().getSelectedItem();

                String output = "Glow[Color="+colorStirng+"]";
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
            String message = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"c","color"});
            if(!message.isEmpty()){
                colorList.getSelectionModel().select(message);
                colorList.scrollTo(colorList.getSelectionModel().getSelectedIndex());
            }
        }
    }

}
