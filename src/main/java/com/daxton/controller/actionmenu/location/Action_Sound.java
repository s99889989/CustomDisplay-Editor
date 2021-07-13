package com.daxton.controller.actionmenu.location;

import com.daxton.Main;
import com.daxton.api.StringControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_Sound {

    @FXML TextField soundFind;
    @FXML ListView<String> soundList;
    @FXML TextField sound;
    @FXML ListView<String> category;
    @FXML TextField volume;
    @FXML TextField pitch;

    @FXML//初始設定
    void initialize() {

        if(Main.languageConfig.getConfigurationSection("Sound") != null){
            Main.languageConfig.getConfigurationSection("Sound").getKeys(false).forEach(s -> soundList.getItems().add(s));
        }
        if(Main.languageConfig.getConfigurationSection("SoundCategory") != null){
            Main.languageConfig.getConfigurationSection("SoundCategory").getKeys(false).forEach(s -> category.getItems().add(s));
        }
    }
    //當輸入聲音關鍵字時
    public void onSoundFind(){
        if(Main.languageConfig.getConfigurationSection("Sound") != null){
            soundList.getItems().clear();
            Main.languageConfig.getConfigurationSection("Sound").getKeys(false).forEach(s -> {
                if(s.contains(soundFind.getText()))
                soundList.getItems().add(s);
            });
        }
    }

    public void onChoiceSound(){
        sound.setText(soundList.getSelectionModel().getSelectedItem());
        onChangeContent();
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Sound", StringControl.getValue(sound));
        ActionMenuPage.keyValue.put("Category", StringControl.getValue(category));
        ActionMenuPage.keyValue.put("Volume", StringControl.getValue(volume));
        ActionMenuPage.keyValue.put("Pitch", StringControl.getValue(pitch));
        ActionMenuPage.changeActionContnet("Sound");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(soundList, inputMap, new String[]{"Sound", "s"});
            StringControl.setMapValue(sound, inputMap, new String[]{"Sound", "s"});
            StringControl.setMapValue(category, inputMap, new String[]{"Category", "c"});
            StringControl.setMapValue(volume, inputMap, new String[]{"Volume", "v"});
            StringControl.setMapValue(pitch, inputMap, new String[]{"Pitch", "p"});


        }
    }

}
