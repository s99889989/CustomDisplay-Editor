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

public class Action_Teleport {

    @FXML TextField locX;
    @FXML TextField locY;
    @FXML TextField locZ;

    @FXML ChoiceBox<String> vecTarget;
    @FXML CheckBox vecPitch;
    @FXML CheckBox vecYaw;
    @FXML TextField vecAddPitch;
    @FXML TextField vecAddYaw;
    @FXML TextField vecDistance;

    @FXML CheckBox onBlock;

    @FXML
    void initialize(){
        vecTarget.getItems().add("Self");
        vecTarget.getItems().add("Target");
        locX.setText("0");
        locY.setText("0");
        locZ.setText("0");
        vecAddPitch.setText("0");
        vecAddYaw.setText("0");
        vecDistance.setText("0");
        vecTarget.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            vecTarget.getSelectionModel().select(vecTarget.getItems().get(newValue.intValue()));
            changeContent();
        });
    }
    public void changeContent(){
        ActionMenuPage.keyValue.clear();
        if(!locX.getText().isEmpty() && !locY.getText().isEmpty() && !locZ.getText().isEmpty())
            ActionMenuPage.keyValue.put("LocAdd", locX.getText()+"|"+locY.getText()+"|"+locZ.getText());


        String vecTargetString = vecTarget.getValue();
        if(vecTargetString != null){
            String vecPitchString = String.valueOf(vecPitch.isSelected());
            String vecYawString = String.valueOf(vecYaw.isSelected());
            String vecAddPitchString = vecAddPitch.getText();
            String vecAddYawString = vecAddYaw.getText();
            String vecDistanceString = vecDistance.getText();
            ActionMenuPage.keyValue.put("VectorAdd", vecTargetString+"|"+vecPitchString+"|"+vecYawString+"|"+vecAddPitchString+"|"+vecAddYawString+"|"+vecDistanceString);
        }

        ActionMenuPage.keyValue.put("OnBlock", String.valueOf(onBlock.isSelected()));
        ActionMenuPage.changeActionContnet("Teleport", ActionMenuPage.keyValue);
    }
    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            String laString = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"LA","LocAdd"});
            if(!laString.isEmpty()){
                String[] laArray = laString.split("\\|");
                if(laArray.length == 3){
                    locX.setText(laArray[0]);
                    locY.setText(laArray[1]);
                    locZ.setText(laArray[2]);
                }
            }
            String vaString = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"VA","VectorAdd"});
            if(!vaString.isEmpty()){
                String[] vaArray = vaString.split("\\|");
                if(vaArray.length == 6){
                    vecTarget.getSelectionModel().select(vaArray[0]);
                    vecPitch.setSelected(Boolean.parseBoolean(vaArray[1]));
                    vecYaw.setSelected(Boolean.parseBoolean(vaArray[2]));
                    vecAddPitch.setText(vaArray[3]);
                    vecAddYaw.setText(vaArray[4]);
                   vecDistance.setText(vaArray[5]);
                }
            }
            String obString = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"OB","OnBlock"});
            if(!obString.isEmpty()){
                onBlock.setSelected(Boolean.parseBoolean(obString));
            }
        }
    }

}
