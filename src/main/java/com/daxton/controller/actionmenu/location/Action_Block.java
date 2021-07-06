package com.daxton.controller.actionmenu.location;

import com.daxton.Main;
import com.daxton.api.StringControl;
import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_Block {

    @FXML TextField blockFind;
    @FXML ListView<String> block;
    @FXML ListView<String> png;
    @FXML ChoiceBox<String> bast;
    @FXML CheckBox addPitch;
    @FXML CheckBox addYaw;
    @FXML TextField rX;
    @FXML TextField rY;
    @FXML TextField rZ;

    @FXML//初始設定
    void initialize() {
        if(Main.languageConfig.getConfigurationSection("") != null){
            Main.languageConfig.getConfigurationSection("Material").getKeys(false).forEach(s -> block.getItems().add(s));
        }

        FileSearch.getTypeFileName("Png/").forEach(s -> png.getItems().add(s.replace(".png","")));


        bast.getItems().add("");
        bast.getItems().add("Self");
        bast.getItems().add("Target");
        bast.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            bast.getSelectionModel().select(bast.getItems().get(newValue.intValue()));
            onChangeContent();
        });
        rX.setText("0");
        rY.setText("0");
        rZ.setText("0");
    }

    //改變設定時
    public void onChangeContentFind(){
        if(Main.languageConfig.getConfigurationSection("Material") != null){
            block.getItems().clear();
            Main.languageConfig.getConfigurationSection("Material").getKeys(false).forEach(s -> {
                if(s.contains(blockFind.getText())){
                    block.getItems().add(s);
                }
            });
        }
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Blockdata", StringControl.getValue(block));
        ActionMenuPage.keyValue.put("IMG", StringControl.getValue(png));
        String bastString = bast.getSelectionModel().getSelectedItem();
        String rXString = rX.getText();
        String rYString = rY.getText();
        String rZString = rZ.getText();
        if(bastString != null && !bastString.isEmpty() && !rXString.isEmpty() && !rYString.isEmpty() && !rZString.isEmpty()){
            ActionMenuPage.keyValue.put("IMGRotangle", bastString+"|"+addPitch.isSelected()+"|"+addYaw.isSelected()+"|"+rXString+"|"+rYString+"|"+rZString);
        }
        ActionMenuPage.changeActionContnet("Block");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setValue(block, inputMap, new String[]{"bdata", "blockdata"});
            StringControl.setValue(png, inputMap, new String[]{"img"});

            String messageString = StringConversion.getActionKey(inputMap, new String[]{"ira", "imgrotangle"});
            if(!messageString.isEmpty()){
                String[] mArray = messageString.split("\\|");
                if(mArray.length == 6){
                    bast.getSelectionModel().select(mArray[0]);
                    addPitch.setSelected(Boolean.parseBoolean(mArray[1]));
                    addYaw.setSelected(Boolean.parseBoolean(mArray[2]));
                    rX.setText(mArray[3]);
                    rY.setText(mArray[4]);
                    rZ.setText(mArray[5]);
                }
            }

        }
    }

}
