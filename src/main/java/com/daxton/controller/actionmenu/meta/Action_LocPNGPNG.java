package com.daxton.controller.actionmenu.meta;

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

public class Action_LocPNGPNG {

    @FXML TextField pngFind;
    @FXML ListView<String> pngList;
    @FXML TextField size;
    @FXML ChoiceBox<String> bast;
    @FXML CheckBox addPitch;
    @FXML CheckBox addYaw;
    @FXML TextField rX;
    @FXML TextField rY;
    @FXML TextField rZ;

    @FXML//初始設定
    void initialize() {
        rX.setText("0");
        rY.setText("0");
        rZ.setText("0");
        bast.getItems().add("Self");
        bast.getItems().add("Target");
        bast.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            bast.getSelectionModel().select(bast.getItems().get(newValue.intValue()));
            onChangeContent();
        });
        FileSearch.getTypeFileName("Png/").forEach(s -> {
            if(s.endsWith(".png"))
            pngList.getItems().add(s.replace(".png", ""));
        });
    }

    public void pngFind(){
        FileSearch.getTypeFileName("Png/").forEach(s -> {
            pngList.getItems().clear();
            if(s.endsWith(".png") && s.contains(pngFind.getText()))
                pngList.getItems().add(s.replace(".png", ""));
        });
    }


    //改變設定時
    public void onChangeContent(){
//        ActionMenuPage.keyValue.clear();
//        ActionMenuPage.keyValue.put("Function", "img");
//        ActionMenuPage.keyValue.put("IMG", StringControl.getValue(pngList));
//        ActionMenuPage.keyValue.put("IMGSize", StringControl.getValue(size));
//
//        if(!StringControl.getValue(bast).isEmpty() && !StringControl.getValue(rX).isEmpty() && !StringControl.getValue(rY).isEmpty() && !StringControl.getValue(rZ).isEmpty())
//            ActionMenuPage.keyValue.put("IMGRotangle", StringControl.getValue(bast)+"|"+StringControl.getValue(addPitch)+"|"+StringControl.getValue(addYaw)+"|"+StringControl.getValue(rX)+"|"+StringControl.getValue(rY)+"|"+StringControl.getValue(rZ));
//
//        ActionMenuPage.changeActionContnet("Particle");
    }



}
