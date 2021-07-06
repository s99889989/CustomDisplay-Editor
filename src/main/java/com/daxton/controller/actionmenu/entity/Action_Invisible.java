package com.daxton.controller.actionmenu.entity;

import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import com.daxton.page.main.ServerMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;



public class Action_Invisible {

    @FXML public CheckBox entity;
    @FXML public CheckBox mainHand;
    @FXML public CheckBox offHand;
    @FXML public CheckBox head;
    @FXML public CheckBox chest;
    @FXML public CheckBox legs;
    @FXML public CheckBox feet;
    @FXML public TextField duration;



    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> mm = FileSearch.setClassAction(input);

            String entityString = StringConversion.getActionKeyToLow(mm , new String[]{"e","entity"}, true);
            if(entityString.equals("true")){
                entity.setSelected(true);
            }else {
                entity.setSelected(false);
            }

            String mainHandString = StringConversion.getActionKeyToLow(mm , new String[]{"mh","MainHand"}, true);
            if(mainHandString.equals("true")){
                mainHand.setSelected(true);
            }else {
                mainHand.setSelected(false);
            }

            String offHandString = StringConversion.getActionKeyToLow(mm , new String[]{"oh","OffHand"}, true);
            if(offHandString.equals("true")){
                offHand.setSelected(true);
            }else {
                offHand.setSelected(false);
            }

            String headString = StringConversion.getActionKeyToLow(mm , new String[]{"h","Head"}, true);
            if(headString.equals("true")){
                head.setSelected(true);
            }else {
                head.setSelected(false);
            }

            String chestString = StringConversion.getActionKeyToLow(mm , new String[]{"c","Chest"}, true);
            if(chestString.equals("true")){
                chest.setSelected(true);
            }else {
                chest.setSelected(false);
            }

            String legsString = StringConversion.getActionKeyToLow(mm , new String[]{"l","Legs"}, true);
            if(legsString.equals("true")){
                legs.setSelected(true);
            }else {
                legs.setSelected(false);
            }

            String feetString = StringConversion.getActionKeyToLow(mm , new String[]{"f","Feet"}, true);
            if(feetString.equals("true")){
                feet.setSelected(true);
            }else {
                feet.setSelected(false);
            }

            String durationString = StringConversion.getActionKeyToLow(mm , new String[]{"dt","duration"}, false);
            if(!durationString.isEmpty()){
                duration.setText(durationString);
            }


        }
    }

    public void onCheckEntity(){
        changContent();
    }

    public void onCheckMainHand(){
        changContent();
    }

    public void onCheckOffHand(){
        changContent();
    }

    public void onCheckHead(){
        changContent();
    }

    public void onCheckChest(){
        changContent();
    }

    public void onCheckLegs(){
        changContent();
    }

    public void onCheckFeet(){
        changContent();
    }

    public void onDurationReleased(){
        changContent();
//        ActionMenuPage.actionContent = "TEST";
//        ActionMenuPage.setSelectActionContent();
    }

    public void changContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("Entity", String.valueOf(entity.isSelected()));
        ActionMenuPage.keyValue.put("MainHand", String.valueOf(mainHand.isSelected()));
        ActionMenuPage.keyValue.put("OffHand", String.valueOf(offHand.isSelected()));
        ActionMenuPage.keyValue.put("Head", String.valueOf(head.isSelected()));
        ActionMenuPage.keyValue.put("Chest", String.valueOf(chest.isSelected()));
        ActionMenuPage.keyValue.put("Legs", String.valueOf(legs.isSelected()));
        ActionMenuPage.keyValue.put("Feet", String.valueOf(feet.isSelected()));
        if(!duration.getText().isEmpty())
        ActionMenuPage.keyValue.put("Duration", duration.getText());


        ActionMenuPage.changeActionContnet("Invisible");
    }



}
