package com.daxton.controller.actionmenu.entity;

import com.daxton.Main;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Action_Attribute {

    @FXML public ListView<String> attrlist;
    @FXML public TextField label;
    @FXML public TextField amount;
    @FXML public TextField duration;

    @FXML
    void initialize() {

        if(Main.languageConfig.getConfigurationSection("Attribute") != null){
            Main.languageConfig.getConfigurationSection("Attribute").getKeys(false).forEach(s -> {
                attrlist.getItems().add(s);
            });
        }

    }

    //當選擇屬性
    public void onSelectAttr(){
        changeContent();
    }

    //當輸入完標籤
    public void onLabelPeleased(){
        changeContent();
    }
    //當輸入完量
    public void onAmountReleased(){
        changeContent();
    }
    //當輸入完持續時間
    public void onDurationReleased(){
        changeContent();
    }
    //重新改變內容
    public void changeContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String attr = attrlist.getSelectionModel().getSelectedItem();

            String labelString = label.getText();

            String amountString = amount.getText();

            String durationString = duration.getText();

            if(attr != null){
                String output = "Attribute[Attribute="+attr;

                if(!labelString.isEmpty()){
                    output += ";Label="+labelString;
                }
                if(!amountString.isEmpty()){
                    output += ";Amount="+amountString;
                }
                if(!durationString.isEmpty()){
                    output += ";Duration="+durationString;
                }

                output += "]";

                ActionMenuPage.actionContent = output;
                ActionMenuPage.setSelectActionContent();

            }

        }
    }

}
