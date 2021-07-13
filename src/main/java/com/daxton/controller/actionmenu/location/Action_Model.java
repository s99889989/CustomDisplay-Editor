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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.Map;

public class Action_Model {

    @FXML TextField idfindKey;
    @FXML ListView<String> modelid;
    @FXML TextField state;
    @FXML TextField lerpin;
    @FXML TextField lerpout;
    @FXML TextField speed;
    @FXML CheckBox teleport;
    @FXML CheckBox delete;

    @FXML//初始設定
    void initialize() {
        state.setText("idle");
        lerpin.setText("3");
        lerpout.setText("2");
        speed.setText("1");
        if(Main.config.getString("ModelEngine.Folder-Path") != null){
            File file = new File(Main.config.getString("ModelEngine.Folder-Path")+"/blueprints");
            if(file.list() != null){
                modelid.getItems().add("");
                for(String s : file.list()){
                    modelid.getItems().add(s.replace(".bbmodel",""));
                }
            }

        }

    }

    //輸入模型ID關鍵字
    public void changeItemIDList(){
        if(Main.config.getString("ModelEngine.Folder-Path") != null){
            modelid.getItems().clear();
            File file = new File(Main.config.getString("ModelEngine.Folder-Path")+"/blueprints");
            if(file.list() != null){
                modelid.getItems().add("");
                for(String s : file.list()){
                    if(s.contains(idfindKey.getText())){
                        modelid.getItems().add(s.replace(".bbmodel",""));
                    }
                }
            }
        }
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();

        ActionMenuPage.keyValue.put("Modelid", StringControl.getValue(modelid));
        if(!StringControl.getValue(state).isEmpty() && !StringControl.getValue(lerpin).isEmpty() && !StringControl.getValue(lerpout).isEmpty() && !StringControl.getValue(speed).isEmpty())
        ActionMenuPage.keyValue.put("State", StringControl.getValue(state)+"|"+StringControl.getValue(lerpin)+"|"+StringControl.getValue(lerpout)+"|"+StringControl.getValue(speed));
        ActionMenuPage.keyValue.put("Teleport", StringControl.getValue(teleport));
        ActionMenuPage.keyValue.put("Delete", StringControl.getValue(delete));

        ActionMenuPage.changeActionContnet("Model");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(modelid, inputMap, new String[]{"modelid", "m", "mid", "model"});
            StringControl.setMapValue(teleport, inputMap, new String[]{"teleport", "tp"});
            StringControl.setMapValue(delete, inputMap, new String[]{"delete", "d"});

            String stateString = StringConversion.getActionKey(inputMap, new String[]{"state", "s"});
            if(!stateString.isEmpty() && stateString.contains("|")){
                String[] mArray = stateString.split("\\|");
                if(mArray.length == 4){
                    state.setText(mArray[0]);
                    lerpin.setText(mArray[1]);
                    lerpout.setText(mArray[2]);
                    speed.setText(mArray[3]);
                }
            }

        }
    }

}
