package com.daxton.controller.skillmenu;

import com.daxton.Main;
import com.daxton.controller.main.SkillMenu;
import com.daxton.function.Manager;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Objects;

public class SkillActionEdit {

    @FXML public ListView<String> actionTypeList;
    @FXML public ListView<String> actionList;
    @FXML public ListView<String> targetList;
    @FXML public ListView<String> targetFiltersList;
    @FXML public TextField distance;
    @FXML public TextField radius;
    @FXML public ListView<String> triggerList;

    @FXML public TextField actionSelect;
    @FXML public TextField targetSelect;
    @FXML public TextField triggerSelect;

    @FXML public Text description;
    //選擇動作類型時
    public void selectActionType(){
        String selectKey = actionTypeList.getSelectionModel().getSelectedItem();
        actionList.getItems().clear();
        Objects.requireNonNull(Manager.file_Config_Map.get(selectKey).getConfigurationSection("")).getKeys(false).forEach(s -> actionList.getItems().add(s));
    }

    //選擇動作時
    public void selectAction(){
        String selectKey = actionList.getSelectionModel().getSelectedItem();
        actionSelect.setText(selectKey);
    }

    //選擇目標列表時
    public void selectTarget(){
        String selectKey = targetList.getSelectionModel().getSelectedItem();

        String descriptionString = Main.languageConfig.getString("Aims.EntityTargeters."+selectKey);
        description.setText(descriptionString);
        changeTargetContent();
    }

    public void onSelectFilter(){
        description.setText("");
        changeTargetContent();
    }

    public void onDistanceChange(){
        description.setText("");
        changeTargetContent();
    }

    public void onRadiusChange(){
        description.setText("");
        changeTargetContent();
    }

    //改變目標內容
    public void changeTargetContent(){
        String addAll = "";

        String selectKey = targetList.getSelectionModel().getSelectedItem();

        if(!selectKey.isEmpty()){
            addAll = selectKey;

            String filter = targetFiltersList.getSelectionModel().getSelectedItem();
            if(filter == null){
                filter = "";
            }
            String distanceString = distance.getText();
            String radiusString = radius.getText();

            if(!filter.isEmpty() || !distanceString.isEmpty() || !radiusString.isEmpty()){
                addAll += "{";
                if(!filter.isEmpty()){
                    addAll += "F="+filter;
                    if(!distanceString.isEmpty()){
                        addAll += ";";
                    }
                    if(distanceString.isEmpty() && !radiusString.isEmpty()){
                        addAll += ";";
                    }
                }
                if(!distanceString.isEmpty()){
                    addAll += "D="+distanceString;
                    if(!radiusString.isEmpty()){
                        addAll += ";";
                    }
                }
                if(!radiusString.isEmpty()){
                    addAll += "R="+radiusString;
                }

                addAll += "}";
            }
        }

        targetSelect.setText(addAll);
    }

    //選擇觸發時
    public void selectTrigger(){
        String selectKey = triggerList.getSelectionModel().getSelectedItem();
        triggerSelect.setText(selectKey);
        String descriptionString = Main.languageConfig.getString("Trigger."+selectKey);
        description.setText(descriptionString);
    }
    //確定
    public void define(){
        SkillMenu skillMenu = (SkillMenu) Manager.controller_Map.get("SkillMenu");
        if(skillMenu != null){

            int selectOrder = skillMenu.actionList.getSelectionModel().getSelectedIndex();

            if(!getFineString().isEmpty()){
                skillMenu.actionList.getItems().set(selectOrder, getFineString());

                skillMenu.onAction();
            }

        }
    }

    public String getFineString(){
        String output = "";

        String a = actionSelect.getText();
        if(!a.isEmpty()){
            output = "Action[action="+a+"]";

            String b = targetSelect.getText();
            if(!b.isEmpty()){
                output += " "+b;
            }

            String c = triggerSelect.getText();
            if(!c.isEmpty()){
                output += " "+c;
            }

        }

        return output;
    }

    //取消
    public void cancel(){
        Main.secondaryWindow.close();
    }

}
