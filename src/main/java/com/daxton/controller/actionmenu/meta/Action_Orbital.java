package com.daxton.controller.actionmenu.meta;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.api.StringControl;
import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_Orbital {

    @FXML TextField startAction;
    @FXML TextField timeAction;
    @FXML TextField timeHitAction;
    @FXML TextField endAction;
    @FXML TextField endHitAction;
    @FXML TextField duration;
    @FXML TextField period;

    @FXML TextField hitRange;
    @FXML TextField hitCount;
    @FXML CheckBox hitStop;
    @FXML TextField speed;
    @FXML CheckBox selfDead;
    @FXML CheckBox targetDead;

    @FXML CheckBox orbitalPitch;
    @FXML CheckBox orbitalYaw;
    @FXML TextField orbitalAddPitch;
    @FXML TextField orbitalAddYaw;
    @FXML CheckBox orbitalRandom;

    @FXML CheckBox startPitch;
    @FXML CheckBox startYaw;
    @FXML TextField startAddPitch;
    @FXML TextField startAddYaw;
    @FXML TextField startDistance;
    @FXML TextField startHight;

    @FXML//初始設定
    void initialize() {

    }
    //打開動作選單
    public void openActionList(){
        Action_OrbitalAction actionMenu = FxmlLoader.display("/page/actionmenu/meta/Action_OrbitalAction.fxml", Main.secondaryWindow);
        if(actionMenu != null){
            Manager.controller_Map.put("Action_OrbitalAction", actionMenu);
        }
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();

        ActionMenuPage.keyValue.put("onStart", StringControl.getValue(startAction));
        ActionMenuPage.keyValue.put("onTime", StringControl.getValue(timeAction));
        ActionMenuPage.keyValue.put("onTimeHit", StringControl.getValue(timeHitAction));
        ActionMenuPage.keyValue.put("onEnd", StringControl.getValue(endAction));
        ActionMenuPage.keyValue.put("onEndHit", StringControl.getValue(endHitAction));
        ActionMenuPage.keyValue.put("Duration", StringControl.getValue(duration));
        ActionMenuPage.keyValue.put("Period", StringControl.getValue(period));

        ActionMenuPage.keyValue.put("HitRange", StringControl.getValue(hitRange));
        ActionMenuPage.keyValue.put("HitCount", StringControl.getValue(hitCount));
        ActionMenuPage.keyValue.put("HitStop", StringControl.getValue(hitStop));
        ActionMenuPage.keyValue.put("Speed", StringControl.getValue(speed));
        ActionMenuPage.keyValue.put("SelfDead", StringControl.getValue(selfDead));
        ActionMenuPage.keyValue.put("TargetDead", StringControl.getValue(targetDead));

        if(!StringControl.getValue(orbitalAddPitch).isEmpty() && !StringControl.getValue(orbitalAddYaw).isEmpty())
            ActionMenuPage.keyValue.put("OrbitalDeviation", StringControl.getValue(orbitalPitch)+"|"+StringControl.getValue(orbitalYaw)+"|"+StringControl.getValue(orbitalAddPitch)+"|"+StringControl.getValue(orbitalAddYaw)+"|"+StringControl.getValue(orbitalRandom));
        if(!StringControl.getValue(startAddPitch).isEmpty() && !StringControl.getValue(startAddYaw).isEmpty() && !StringControl.getValue(startDistance).isEmpty() && !StringControl.getValue(startHight).isEmpty())
            ActionMenuPage.keyValue.put("StartLocAdd", StringControl.getValue(startPitch)+"|"+StringControl.getValue(startYaw)+"|"+StringControl.getValue(startAddPitch)+"|"+StringControl.getValue(startAddYaw)+"|"+StringControl.getValue(startDistance)+"|"+StringControl.getValue(startHight));

        ActionMenuPage.changeActionContnet("Orbital");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            String orbitalString = StringConversion.getActionKey(inputMap, new String[]{"OrbitalDeviation", "OD"});
            if(!orbitalString.isEmpty() && orbitalString.contains("|")){
                String[] mArray = orbitalString.split("\\|");
                if(mArray.length == 5){
                    orbitalPitch.setSelected(Boolean.parseBoolean(mArray[0]));
                    orbitalYaw.setSelected(Boolean.parseBoolean(mArray[1]));
                    orbitalAddPitch.setText(mArray[2]);
                    orbitalAddYaw.setText(mArray[3]);
                    orbitalRandom.setSelected(Boolean.parseBoolean(mArray[4]));
                }
            }

            String startString = StringConversion.getActionKey(inputMap, new String[]{"StartLocAdd", "SLA"});
            if(!startString.isEmpty() && startString.contains("|")){
                String[] mArray = startString.split("\\|");
                if(mArray.length == 6){
                    startPitch.setSelected(Boolean.parseBoolean(mArray[0]));
                    startYaw.setSelected(Boolean.parseBoolean(mArray[1]));
                    startAddPitch.setText(mArray[2]);
                    startAddYaw.setText(mArray[3]);
                    startDistance.setText(mArray[4]);
                    startHight.setText(mArray[5]);
                }
            }

            StringControl.setMapValue(startAction, inputMap, new String[]{"onStart", "os"});
            StringControl.setMapValue(timeAction, inputMap, new String[]{"onTime", "ot"});
            StringControl.setMapValue(timeHitAction, inputMap, new String[]{"onTimeHit", "oth"});
            StringControl.setMapValue(endAction, inputMap, new String[]{"onEnd", "oe"});
            StringControl.setMapValue(endHitAction, inputMap, new String[]{"onEndHit", "oeh"});
            StringControl.setMapValue(duration, inputMap, new String[]{"duration", "d"});
            StringControl.setMapValue(period, inputMap, new String[]{"period", "p"});

            StringControl.setMapValue(hitRange, inputMap, new String[]{"HitRange", "hr"});
            StringControl.setMapValue(hitCount, inputMap, new String[]{"HitCount", "hc"});
            StringControl.setMapValue(hitStop, inputMap, new String[]{"HitStop", "hs"});
            StringControl.setMapValue(speed, inputMap, new String[]{"speed", "s"});
            StringControl.setMapValue(selfDead, inputMap, new String[]{"selfDead", "sd"});
            StringControl.setMapValue(targetDead, inputMap, new String[]{"targetDead", "td"});

        }
    }

    //選擇起始動作
    public void choiceStartAction(){
        Action_OrbitalAction actionMenu = (Action_OrbitalAction) Manager.controller_Map.get("Action_OrbitalAction");
        if(actionMenu != null){
            startAction.setText(actionMenu.actionList.getSelectionModel().getSelectedItem());
            onChangeContent();
        }

    }
    //選擇運行中動作
    public void choiceTimeAction(){
        Action_OrbitalAction actionMenu = (Action_OrbitalAction) Manager.controller_Map.get("Action_OrbitalAction");
        if(actionMenu != null){
            timeAction.setText(actionMenu.actionList.getSelectionModel().getSelectedItem());
            onChangeContent();
        }

    }

    //選擇運行中命中動作
    public void choiceTimeHitAction(){
        Action_OrbitalAction actionMenu = (Action_OrbitalAction) Manager.controller_Map.get("Action_OrbitalAction");
        if(actionMenu != null){
            timeHitAction.setText(actionMenu.actionList.getSelectionModel().getSelectedItem());
            onChangeContent();
        }

    }

    //選擇最後動作
    public void choiceEndAction(){
        Action_OrbitalAction actionMenu = (Action_OrbitalAction) Manager.controller_Map.get("Action_OrbitalAction");
        if(actionMenu != null){
            endAction.setText(actionMenu.actionList.getSelectionModel().getSelectedItem());
            onChangeContent();
        }

    }

    //選擇最後命中動作
    public void choiceEndHitAction(){
        Action_OrbitalAction actionMenu = (Action_OrbitalAction) Manager.controller_Map.get("Action_OrbitalAction");
        if(actionMenu != null){
            endHitAction.setText(actionMenu.actionList.getSelectionModel().getSelectedItem());
            onChangeContent();
        }

    }

}
