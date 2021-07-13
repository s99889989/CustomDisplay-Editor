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

public class Action_ClassAttr {

    @FXML ChoiceBox<String> attrType;
    @FXML ListView<String> typeFile;
    @FXML ListView<String> typeList;
    @FXML TextField type;
    @FXML TextField amount;
    @FXML TextField duration;
    @FXML TextField label;

    @FXML//初始設定
    void initialize() {
        attrType.getItems().add("status");
        attrType.getItems().add("equipment");
        attrType.getItems().add("point");

        attrType.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            attrType.getSelectionModel().select(attrType.getItems().get(newValue.intValue()));
            String attrTypeString = attrType.getSelectionModel().getSelectedItem();
            typeFile.getItems().clear();
            if(attrTypeString.equals("status"))
                FileSearch.getTypeFileKey("Class/Attributes/EntityStats/").forEach(s -> typeFile.getItems().add(s));
            if(attrTypeString.equals("equipment"))
                FileSearch.getTypeFileKey("Class/Attributes/EquipmentStats/").forEach(s -> typeFile.getItems().add(s));
            if(attrTypeString.equals("point"))
                FileSearch.getTypeFileKey("Class/Attributes/Point/").forEach(s -> typeFile.getItems().add(s));
        });
    }

    //當選擇屬性檔案
    public void onChoiceAttrFile(){
        String s = typeFile.getSelectionModel().getSelectedItem();

        if(Manager.file_Config_Map.get(s) != null){
            String key = s;
            while (key.contains("/")){
                key = key.substring(key.indexOf("/")+1);
            }
            key = key.replace(".yml","");

            if(Manager.file_Config_Map.get(s).getConfigurationSection(key) != null){
                typeList.getItems().clear();
                Manager.file_Config_Map.get(s).getConfigurationSection(key).getKeys(false).forEach(s1 -> {
                    typeList.getItems().add(s1);
                });
            }

        }
    }

    //當選擇屬性
    public void onChoiceAttr(){
        type.setText(typeList.getSelectionModel().getSelectedItem());
        onChangeContent();
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Type", StringControl.getValue(attrType));
        ActionMenuPage.keyValue.put("Attributes", StringControl.getValue(type));
        ActionMenuPage.keyValue.put("Label", StringControl.getValue(label));
        ActionMenuPage.keyValue.put("Amount", StringControl.getValue(amount));
        ActionMenuPage.keyValue.put("Duration", StringControl.getValue(duration));
        ActionMenuPage.changeActionContnet("ClassAttr");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(attrType, inputMap, new String[]{"Type", "t"});
            StringControl.setMapValue(type, inputMap, new String[]{"Attributes", "Attr"});
            StringControl.setMapValue(label, inputMap, new String[]{"Label", "L"});
            StringControl.setMapValue(amount, inputMap, new String[]{"Amount", "a"});
            StringControl.setMapValue(duration, inputMap, new String[]{"Duration", "dt"});

        }
    }

}
