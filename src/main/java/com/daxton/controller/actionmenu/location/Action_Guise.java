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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;

public class Action_Guise {

    @FXML ListView<String> entityType;
    @FXML ChoiceBox<String> eqmParts;
    @FXML TextField duration;
    @FXML TextField name;
    @FXML CheckBox canLook;
    @FXML ListView<String> itmeType;
    @FXML ListView<String> item;

    @FXML CheckBox headPitch;
    @FXML CheckBox headYaw;
    @FXML TextField headX;
    @FXML TextField headY;
    @FXML TextField headZ;
    @FXML TextField bodyX;
    @FXML TextField bodyY;
    @FXML TextField bodyZ;
    @FXML TextField leftArmX;
    @FXML TextField leftArmY;
    @FXML TextField leftArmZ;
    @FXML TextField rightArmX;
    @FXML TextField rightArmY;
    @FXML TextField rightArmZ;
    @FXML TextField leftLegX;
    @FXML TextField leftLegY;
    @FXML TextField leftLegZ;
    @FXML TextField rightLegX;
    @FXML TextField rightLegY;
    @FXML TextField rightLegZ;

    @FXML//初始設定
    void initialize() {
        if(Main.languageConfig.getConfigurationSection("Entity") != null){
            Main.languageConfig.getConfigurationSection("Entity").getKeys(false).forEach(s -> entityType.getItems().add(s));
        }
        eqmParts.getItems().add("");
        if(Main.languageConfig.getConfigurationSection("Equipment") != null){
            Main.languageConfig.getConfigurationSection("Equipment").getKeys(false).forEach(s -> eqmParts.getItems().add(s));
        }
        eqmParts.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            eqmParts.getSelectionModel().select(eqmParts.getItems().get(newValue.intValue()));
            onChangeContent();
        });
        FileSearch.getTypeFileName("Items/item").forEach(s -> itmeType.getItems().add(s.replace(".yml","")));
        headX.setText("0");headY.setText("0");headZ.setText("0");bodyX.setText("0");bodyY.setText("0");bodyZ.setText("0");
        leftArmX.setText("0");leftArmY.setText("0");leftArmZ.setText("0");rightArmX.setText("0");rightArmY.setText("0");rightArmZ.setText("0");
        leftLegX.setText("0");leftLegY.setText("0");leftLegZ.setText("0");rightLegX.setText("0");rightLegY.setText("0");rightLegZ.setText("0");
    }



    //物品類別
    public void onChangeContentFind(){
        changeItemIDList();

    }

    public void changeItemIDList(){
        if(Manager.file_Config_Map.get("Items/item/"+itmeType.getSelectionModel().getSelectedItem()+".yml") != null){
            item.getItems().clear();
            FileConfiguration fileConfiguration = Manager.file_Config_Map.get("Items/item/"+itmeType.getSelectionModel().getSelectedItem()+".yml");
            if(fileConfiguration.getConfigurationSection("") != null){
                fileConfiguration.getConfigurationSection("").getKeys(false).forEach(s -> item.getItems().add(s));
            }
        }
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("EntityType", StringControl.getValue(entityType));
        ActionMenuPage.keyValue.put("EntityName", StringControl.getValue(name));
        ActionMenuPage.keyValue.put("Visible", StringControl.getValue(canLook));

        if(!StringControl.getValue(itmeType).isEmpty() && !StringControl.getValue(item).isEmpty())
        ActionMenuPage.keyValue.put("ItemID", StringControl.getValue(itmeType)+"_"+StringControl.getValue(item));

        ActionMenuPage.keyValue.put("EquipmentSlot", StringControl.getValue(eqmParts));
        ActionMenuPage.keyValue.put("Duration", StringControl.getValue(duration));
        ActionMenuPage.keyValue.put("HeadAddLoc", headPitch.isSelected()+"|"+headYaw.isSelected());
        if(!StringControl.getValue(headX).isEmpty() && !StringControl.getValue(headY).isEmpty() && !StringControl.getValue(headZ).isEmpty())
        ActionMenuPage.keyValue.put("Head", StringControl.getValue(headX)+"|"+StringControl.getValue(headY)+"|"+StringControl.getValue(headZ));
        if(!StringControl.getValue(bodyX).isEmpty() && !StringControl.getValue(bodyY).isEmpty() && !StringControl.getValue(bodyZ).isEmpty())
        ActionMenuPage.keyValue.put("Body", StringControl.getValue(bodyX)+"|"+StringControl.getValue(bodyY)+"|"+StringControl.getValue(bodyZ));
        if(!StringControl.getValue(leftArmX).isEmpty() && !StringControl.getValue(leftArmY).isEmpty() && !StringControl.getValue(leftArmZ).isEmpty())
        ActionMenuPage.keyValue.put("LeftArm", StringControl.getValue(leftArmX)+"|"+StringControl.getValue(leftArmY)+"|"+StringControl.getValue(leftArmZ));
        if(!StringControl.getValue(rightArmX).isEmpty() && !StringControl.getValue(rightArmY).isEmpty() && !StringControl.getValue(rightArmZ).isEmpty())
        ActionMenuPage.keyValue.put("RightArm", StringControl.getValue(rightArmX)+"|"+StringControl.getValue(rightArmY)+"|"+StringControl.getValue(rightArmZ));
        if(!StringControl.getValue(leftLegX).isEmpty() && !StringControl.getValue(leftLegY).isEmpty() && !StringControl.getValue(leftLegZ).isEmpty())
        ActionMenuPage.keyValue.put("LeftLeg", StringControl.getValue(leftLegX)+"|"+StringControl.getValue(leftLegY)+"|"+StringControl.getValue(leftLegZ));
        if(!StringControl.getValue(rightLegX).isEmpty() && !StringControl.getValue(rightLegY).isEmpty() && !StringControl.getValue(rightLegZ).isEmpty())
        ActionMenuPage.keyValue.put("RightLeg", StringControl.getValue(rightLegX)+"|"+StringControl.getValue(rightLegY)+"|"+StringControl.getValue(rightLegZ));
        ActionMenuPage.changeActionContnet("Guise");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            StringControl.setMapValue(entityType, inputMap, new String[]{"ET", "EntityType"});
            StringControl.setMapValue(name, inputMap, new String[]{"en", "EntityName"});
            StringControl.setMapValue(canLook, inputMap, new String[]{"Visible"});

            String itemString = StringConversion.getActionKey(inputMap, new String[]{"iid", "ItemID"});
            if(!itemString.isEmpty() && itemString.contains("_")){
                String[] mArray = itemString.split("_");
                if(mArray.length == 2){
                    itmeType.getSelectionModel().select(mArray[0]);
                    changeItemIDList();
                    item.getSelectionModel().select(mArray[1]);
                }
            }

            StringControl.setMapValue(eqmParts, inputMap, new String[]{"ES", "EquipmentSlot"});
            StringControl.setMapValue(duration, inputMap, new String[]{"dt", "duration"});

            String halString = StringConversion.getActionKey(inputMap, new String[]{"hal", "HeadAddLoc"});
            if(!halString.isEmpty() && halString.contains("|")){
                String[] mArray = halString.split("\\|");
                if(mArray.length == 2){
                    headPitch.setSelected(Boolean.parseBoolean(mArray[0]));
                    headYaw.setSelected(Boolean.parseBoolean(mArray[1]));
                }
            }

            String headString = StringConversion.getActionKey(inputMap, new String[]{"H", "Head"});
            if(!headString.isEmpty() && headString.contains("|")){
                String[] mArray = headString.split("\\|");
                if(mArray.length == 3){
                    headX.setText(mArray[0]);
                    headY.setText(mArray[1]);
                    headZ.setText(mArray[2]);
                }
            }

            String bodyString = StringConversion.getActionKey(inputMap, new String[]{"b", "Body"});
            if(!bodyString.isEmpty() && bodyString.contains("|")){
                String[] mArray = bodyString.split("\\|");
                if(mArray.length == 3){
                    bodyX.setText(mArray[0]);
                    bodyY.setText(mArray[1]);
                    bodyZ.setText(mArray[2]);
                }
            }

            String lARString = StringConversion.getActionKey(inputMap, new String[]{"LAR", "LeftArm"});
            if(!lARString.isEmpty() && lARString.contains("|")){
                String[] mArray = lARString.split("\\|");
                if(mArray.length == 3){
                    leftArmX.setText(mArray[0]);
                    leftArmY.setText(mArray[1]);
                    leftArmZ.setText(mArray[2]);
                }
            }

            String rARString = StringConversion.getActionKey(inputMap, new String[]{"RAR", "RightArm"});
            if(!rARString.isEmpty() && rARString.contains("|")){
                String[] mArray = rARString.split("\\|");
                if(mArray.length == 3){
                    rightArmX.setText(mArray[0]);
                    rightArmY.setText(mArray[1]);
                    rightArmZ.setText(mArray[2]);
                }
            }

            String lLGString = StringConversion.getActionKey(inputMap, new String[]{"LLG", "LeftLeg"});
            if(!lLGString.isEmpty() && lLGString.contains("|")){
                String[] mArray = lLGString.split("\\|");
                if(mArray.length == 3){
                    leftLegX.setText(mArray[0]);
                    leftLegY.setText(mArray[1]);
                    leftLegZ.setText(mArray[2]);
                }
            }

            String rLGString = StringConversion.getActionKey(inputMap, new String[]{"RLG", "RightLeg"});
            if(!rLGString.isEmpty() && rLGString.contains("|")){
                String[] mArray = rLGString.split("\\|");
                if(mArray.length == 3){
                    rightLegX.setText(mArray[0]);
                    rightLegY.setText(mArray[1]);
                    rightLegZ.setText(mArray[2]);
                }
            }

        }
    }

}
