package com.daxton.controller.actionmenu.entity;

import com.daxton.Main;
import com.daxton.api.StringConversion;
import com.daxton.config.FileControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class Action_MythicSkill {

    @FXML TextField findKey;
    @FXML ListView<String> mythicSkillList;
    @FXML
    void initialize() {
        if(Main.config.getString("MythicMobs.Folder-Path") != null){
            List<File> files = FileSearch.getFiles(Main.config.getString("MythicMobs.Folder-Path")+"/Skills");
            files.forEach(file -> {
                FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
                fileConfiguration.getConfigurationSection("").getKeys(false).forEach(s -> mythicSkillList.getItems().add(s));

            });
        }

    }
    //當輸入搜尋關鍵字
    public void onKeyReleased(){
        String key = findKey.getText();
        if(!key.isEmpty()){
            mythicSkillList.getItems().clear();
            if(Main.config.getString("MythicMobs.Folder-Path") != null){
                List<File> files = FileSearch.getFiles(Main.config.getString("MythicMobs.Folder-Path")+"/Skills");
                files.forEach(file -> {
                    FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
                    fileConfiguration.getConfigurationSection("").getKeys(false).forEach(s -> {
                        if(s.contains(key))
                        mythicSkillList.getItems().add(s);
                    });

                });
            }
        }else {
            if(Main.config.getString("MythicMobs.Folder-Path") != null){
                List<File> files = FileSearch.getFiles(Main.config.getString("MythicMobs.Folder-Path")+"/Skills");
                files.forEach(file -> {
                    FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
                    fileConfiguration.getConfigurationSection("").getKeys(false).forEach(s -> mythicSkillList.getItems().add(s));

                });
            }
        }
    }
    //當選擇技能
    public void onSelectSkill(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){

            String output = "MythicSkill[Skill="+mythicSkillList.getSelectionModel().getSelectedItem()+"]";

            ActionMenuPage.actionContent = output;

            ActionMenuPage.setSelectActionContent();
        }
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            String message = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"s","Skill"});
            if(!message.isEmpty()){
                if(mythicSkillList.getItems().contains(message)){
                    mythicSkillList.getSelectionModel().select(message);
                    mythicSkillList.scrollTo(mythicSkillList.getSelectionModel().getSelectedIndex());
                }
            }
        }
    }

}
