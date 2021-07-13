package com.daxton.controller.actionmenu.classs;

import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_SetSkillLevel {


    @FXML ListView<String> skillFile;
    @FXML ListView<String> skillList;
    @FXML ChoiceBox<String> skillType;
    @FXML TextField amount;

    @FXML//初始設定
    void initialize() {

        FileSearch.getTypeFileKey("Class/Skill/").forEach(s -> skillFile.getItems().add(s));
        skillType.getItems().add("level");
        skillType.getItems().add("use");
        skillType.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            skillType.getSelectionModel().select(skillType.getItems().get(newValue.intValue()));
            onChangeContent();
        });
    }

    //當選擇技能檔案
    public void onChoiceSkillFile(){
        String s = skillFile.getSelectionModel().getSelectedItem();

        if(Manager.file_Config_Map.get(s) != null){

            if(Manager.file_Config_Map.get(s).getConfigurationSection("Skills") != null){
                skillList.getItems().clear();
                Manager.file_Config_Map.get(s).getConfigurationSection("Skills").getKeys(false).forEach(s1 -> {
                    skillList.getItems().add(s1);
                });
            }

        }
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("SkillName", StringControl.getValue(skillList));
        ActionMenuPage.keyValue.put("Type", StringControl.getValue(skillType));
        ActionMenuPage.keyValue.put("Amount", StringControl.getValue(amount));

        ActionMenuPage.changeActionContnet("SetSkillLevel");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(skillList, inputMap, new String[]{"SkillName"});
            StringControl.setMapValue(skillType, inputMap, new String[]{"Type"});
            StringControl.setMapValue(amount, inputMap, new String[]{"Amount"});

        }
    }

}
