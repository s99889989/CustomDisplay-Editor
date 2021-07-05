package com.daxton.controller.actionmenu.entity;

import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Action_Move {

    @FXML ChoiceBox<String> target;
    @FXML CheckBox addPitch;
    @FXML CheckBox addYaw;
    @FXML TextField pitch;
    @FXML TextField yaw;
    @FXML TextField distance;
    @FXML TextField hight;



    @FXML
    void initialize() {
        target.getItems().add("Self");
        target.getItems().add("Target");
        target.setValue("Self");
        pitch.setText("0");
        yaw.setText("0");
        distance.setText("0");
        hight.setText("0");

        target.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            target.getSelectionModel().select(target.getItems().get(newValue.intValue()));
            changeContent();
        });

    }

    public void changeContent(){
        ActionMenuPage.keyValue.clear();
        String targetString = target.getValue();
        String addPitchString =  String.valueOf(addPitch.isSelected());
        String addYawString =  String.valueOf(addYaw.isSelected());
        String pitchString = pitch.getText();
        String yawString = yaw.getText();
        String distanceString = distance.getText();
        String hightString = hight.getText();

        if(!targetString.isEmpty() && !pitchString.isEmpty() && !yawString.isEmpty() && !distanceString.isEmpty()){
            ActionMenuPage.keyValue.put("DirectionAdd", targetString+"|"+addPitchString+"|"+addYawString+"|"+pitchString+"|"+yawString+"|"+distanceString);
        }
        if(!hightString.isEmpty()){
            ActionMenuPage.keyValue.put("AwayHight", hightString);
        }

        ActionMenuPage.changeActionContnet("Move", ActionMenuPage.keyValue);
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            String daString = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"DA","DirectionAdd"});
            if(!daString.isEmpty()){
                String[] daArray = daString.split("\\|");
                if(daArray.length == 6){
                    target.setValue(daArray[0]);
                    addPitch.setSelected(Boolean.parseBoolean(daArray[1]));
                    addYaw.setSelected(Boolean.parseBoolean(daArray[2]));
                    pitch.setText(daArray[3]);
                    yaw.setText(daArray[4]);
                    distance.setText(daArray[5]);
                }
            }

            String hightString = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"AH","awayHight"});
            if(!hightString.isEmpty()){
                hight.setText(hightString);
            }
        }
    }

}
