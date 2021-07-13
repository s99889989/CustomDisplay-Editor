package com.daxton.controller.actionmenu.location;

import com.daxton.Main;
import com.daxton.api.StringControl;
import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Map;


public class Action_Particle {

    @FXML ChoiceBox<String> function;
    @FXML TextField particleFind;
    @FXML ListView<String> particle;
    @FXML TextField dataFind;
    @FXML ListView<String> dataList;
    @FXML TextField data;
    @FXML TextField speed;
    @FXML TextField amount;
    @FXML TextField offX;
    @FXML TextField offY;
    @FXML TextField offZ;

    @FXML//初始設定
    void initialize() {
        function.getItems().add("");
        function.getItems().add("add");
        function.getItems().add("remove");
        function.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            function.getSelectionModel().select(function.getItems().get(newValue.intValue()));
            onChangeContent();
        });
        if(Main.languageConfig.getConfigurationSection("Particle") != null){
            Main.languageConfig.getConfigurationSection("Particle").getKeys(false).forEach(s -> particle.getItems().add(s));
        }
        if(Main.languageConfig.getConfigurationSection("Material") != null){
            Main.languageConfig.getConfigurationSection("Material").getKeys(false).forEach(s -> dataList.getItems().add(s));
        }
    }

    //搜尋粒子時
    public void onParticleFind(){
        if(Main.languageConfig.getConfigurationSection("Particle") != null){
            particle.getItems().clear();
            Main.languageConfig.getConfigurationSection("Particle").getKeys(false).forEach(s -> {
                if(s.contains(particleFind.getText())){
                    particle.getItems().add(s);
                }
            });
        }
    }

    //搜尋附屬性時
    public void onDataFind(){
        if(Main.languageConfig.getConfigurationSection("Material") != null){
            dataList.getItems().clear();
            Main.languageConfig.getConfigurationSection("Material").getKeys(false).forEach(s -> {
                if(s.contains(dataFind.getText())){
                    dataList.getItems().add(s);
                }
            });
        }
    }
    public void onDataChoice(){
        data.setText(dataList.getSelectionModel().getSelectedItem());
        onChangeContent();
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Function", StringControl.getValue(function));
        ActionMenuPage.keyValue.put("Particle", StringControl.getValue(particle));
        ActionMenuPage.keyValue.put("ParticleData", StringControl.getValue(data));
        ActionMenuPage.keyValue.put("Extra", StringControl.getValue(speed));
        ActionMenuPage.keyValue.put("Count", StringControl.getValue(amount));
        if(!StringControl.getValue(offX).isEmpty() && !StringControl.getValue(offY).isEmpty() && !StringControl.getValue(offZ).isEmpty())
            ActionMenuPage.keyValue.put("LocationOffset", StringControl.getValue(offX)+"|"+StringControl.getValue(offY)+"|"+StringControl.getValue(offZ));

        ActionMenuPage.changeActionContnet("Particle");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(function, inputMap, new String[]{"Function", "fc"});
            StringControl.setMapValue(particle, inputMap, new String[]{"Particle", "P"});
            StringControl.setMapValue(data, inputMap, new String[]{"ParticleData", "PData"});
            StringControl.setMapValue(speed, inputMap, new String[]{"Extra", "E"});
            StringControl.setMapValue(amount, inputMap, new String[]{"Count", "c"});

            String offString = StringConversion.getActionKey(inputMap, new String[]{"LocOff", "LocationOffset"});
            if(!offString.isEmpty() && offString.contains("|")){
                String[] mArray = offString.split("\\|");
                if(mArray.length == 3){
                    offX.setText(mArray[0]);
                    offY.setText(mArray[1]);
                    offZ.setText(mArray[2]);
                }
            }

        }
    }

    //轉成粒子圖片介面
    public void imgMode(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            actionMenu.actionMenuControl("ParticlePNG");
        }
    }

    //打開顏色代碼網頁
    public void openColoerWeb(){
        String url = "https://www.rapidtables.com/web/color/RGB_Color.html";
        java.net.URI uri = java.net.URI.create(url);
        java.awt.Desktop dp = java.awt.Desktop.getDesktop();
        if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
            try {
                dp.browse(uri);
            }catch (IOException exception){

            }
        }
    }

}
