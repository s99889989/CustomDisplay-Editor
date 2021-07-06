package com.daxton.controller.actionmenu.entity;

import com.daxton.Main;
import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Action_PotionEffect {

    @FXML ListView<String> potionList;
    @FXML TextField level;
    @FXML TextField duration;
    @FXML CheckBox ambient;
    @FXML CheckBox particles;

    @FXML
    void initialize(){
        if(Main.languageConfig.getConfigurationSection("PotionEffectType") != null){
            Main.languageConfig.getConfigurationSection("PotionEffectType").getKeys(false).forEach(s -> potionList.getItems().add(s));
        }
    }

    public void changeContent(){

        String potionString = potionList.getSelectionModel().getSelectedItem();
        if(potionString != null){
            ActionMenuPage.keyValue.clear();
            ActionMenuPage.keyValue.put("Potion", potionString);
            if(!level.getText().isEmpty())
            ActionMenuPage.keyValue.put("Amplifir", level.getText());
            if(!duration.getText().isEmpty())
            ActionMenuPage.keyValue.put("Duration", duration.getText());
            ActionMenuPage.keyValue.put("Ambient", String.valueOf(ambient.isSelected()));
            ActionMenuPage.keyValue.put("Particles", String.valueOf(particles.isSelected()));

            ActionMenuPage.changeActionContnet("PotionEffect");
        }

    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            String potionString = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"Potion"});
            if(!potionString.isEmpty()){
                if(potionList.getItems().contains(potionString))
                potionList.getSelectionModel().select(potionString);
                potionList.scrollTo(potionList.getSelectionModel().getSelectedIndex());
            }
            String durationString = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"dt","duration"});
            if(!durationString.isEmpty()){
                duration.setText(durationString);
            }
            String amplifirString = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"ap","amplifir"});
            if(!amplifirString.isEmpty()){
                level.setText(amplifirString);
            }
            String ambientString = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"ab","ambient"});
            if(!ambientString.isEmpty()){
                ambient.setSelected(Boolean.parseBoolean(ambientString));
            }
            String particlesString = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"p","particles"});
            if(!particlesString.isEmpty()){
                particles.setSelected(Boolean.parseBoolean(particlesString));
            }
        }
    }

}
