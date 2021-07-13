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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

public class Action_SwitchAction {

    @FXML TextField  conditionContent;
    @FXML ChoiceBox<String> conditionType;
    @FXML ListView<String> caseList;
    @FXML ListView<String> actionList;
    @FXML TextField  caseInput;

    @FXML//初始設定
    void initialize() {
        conditionType.getItems().add("Compare");
        conditionType.getItems().add("Contains");
        conditionType.getItems().add("Equals");
        conditionType.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            conditionType.getSelectionModel().select(conditionType.getItems().get(newValue.intValue()));
            onChangeContent();
        });
    }

    //打開動作選單
    public void openActionList(){
        Action_OrbitalAction actionMenu = FxmlLoader.display("/page/actionmenu/meta/Action_OrbitalAction.fxml", Main.secondaryWindow);
        if(actionMenu != null){
            Manager.controller_Map.put("Action_OrbitalAction", actionMenu);
        }
    }
    //添加選項
    public void addCase(){
        if(!caseInput.getText().isEmpty()){
            caseList.getItems().add(caseInput.getText());
            onChangeContent();
        }
    }
    //編輯選項
    public void editCase(){
        if(!caseInput.getText().isEmpty() && caseList.getSelectionModel().getSelectedItem() != null){
            caseList.getItems().set(caseList.getSelectionModel().getSelectedIndex(), caseInput.getText());
            onChangeContent();
        }
    }
    //移除選項
    public void removeCase(){
        if(caseList.getSelectionModel().getSelectedItem() != null){
            caseList.getItems().remove(caseList.getSelectionModel().getSelectedItem());
            onChangeContent();
        }
    }

    //添加動作
    public void addAction(){
        Action_OrbitalAction actionMenu = (Action_OrbitalAction) Manager.controller_Map.get("Action_OrbitalAction");
        if(actionMenu != null){
            if(actionMenu.actionList.getSelectionModel().getSelectedItem() != null){
                actionList.getItems().add(actionMenu.actionList.getSelectionModel().getSelectedItem());
                onChangeContent();
            }

        }

    }
    //編輯動作
    public void editAction(){
        Action_OrbitalAction actionMenu = (Action_OrbitalAction) Manager.controller_Map.get("Action_OrbitalAction");
        if(actionMenu != null){
            if(actionMenu.actionList.getSelectionModel().getSelectedItem() != null){
                if(actionList.getSelectionModel().getSelectedItem() != null){
                    actionList.getItems().set(actionList.getSelectionModel().getSelectedIndex(), actionMenu.actionList.getSelectionModel().getSelectedItem());
                    onChangeContent();
                }
            }
        }
    }
    //移除動作
    public void removeAction(){
        Action_OrbitalAction actionMenu = (Action_OrbitalAction) Manager.controller_Map.get("Action_OrbitalAction");
        if(actionMenu != null){
            if(actionList.getSelectionModel().getSelectedItem() != null){
                actionList.getItems().remove(actionList.getSelectionModel().getSelectedItem());
                onChangeContent();
            }
        }
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("ConditionContent", StringControl.getValue(conditionContent));
        ActionMenuPage.keyValue.put("ConditionType", StringControl.getValue(conditionType));

        if(caseList.getItems().size() == actionList.getItems().size()){
            int cI = 0;
            String casePut = "";
            for(String caseString : caseList.getItems()){
                if(cI > 0)
                    casePut += "|";
                casePut += caseString;
                cI++;
            }
            ActionMenuPage.keyValue.put("Case", casePut);

            int cA = 0;
            String actionPut = "";
            for(String caseString : actionList.getItems()){
                if(cA > 0)
                    actionPut += "|";
                actionPut += caseString;
                cA++;
            }
            ActionMenuPage.keyValue.put("Action", actionPut);

        }


        ActionMenuPage.changeActionContnet("SwitchAction");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(conditionContent, inputMap, new String[]{"ConditionContent", "cc"});
            StringControl.setMapValue(conditionType, inputMap, new String[]{"ConditionType", "ct"});

            String caseString = StringConversion.getActionKey(inputMap, new String[]{"Case", "c"});
            if(!caseString.isEmpty() && caseString.contains("|")){
                String[] mArray = caseString.split("\\|");
                if(mArray.length > 0){
                    caseList.getItems().clear();
                    for(String s : mArray){
                        caseList.getItems().add(s);
                    }
                }
            }

            String actionString = StringConversion.getActionKey(inputMap, new String[]{"Action", "a"});
            if(!actionString.isEmpty() && actionString.contains("|")){
                String[] mArray = actionString.split("\\|");
                if(mArray.length > 0){
                    actionList.getItems().clear();
                    for(String s : mArray){
                        actionList.getItems().add(s);
                    }
                }
            }

        }
    }

}
