package com.daxton.controller.actionmenu.player;

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
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;

public class Action_Item {

    @FXML ListView<String> itmeType;
    @FXML ListView<String> itemID;
    @FXML TextField amount;
    @FXML CheckBox remove;

    @FXML//初始設定
    void initialize() {
        FileSearch.getTypeFileName("Items/item").forEach(s -> itmeType.getItems().add(s.replace(".yml","")));

    }
    public void onChangeContentType(){
        changeItemIDList();
        onChangeContent();
    }

    public void changeItemIDList(){
        if(Manager.file_Config_Map.get("Items/item/"+itmeType.getSelectionModel().getSelectedItem()+".yml") != null){
            itemID.getItems().clear();
            FileConfiguration fileConfiguration = Manager.file_Config_Map.get("Items/item/"+itmeType.getSelectionModel().getSelectedItem()+".yml");
            if(fileConfiguration.getConfigurationSection("") != null){
                fileConfiguration.getConfigurationSection("").getKeys(false).forEach(s -> itemID.getItems().add(s));
            }
        }
    }

    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        ActionMenuPage.keyValue.put("ItemID", StringControl.getValue(itmeType)+"_"+StringControl.getValue(itemID));
        ActionMenuPage.keyValue.put("Amount", StringControl.getValue(amount));
        ActionMenuPage.keyValue.put("Remove", StringControl.getValue(remove));
        ActionMenuPage.changeActionContnet("Item");

    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            String messageString = StringConversion.getActionKey(inputMap, new String[]{"iid", "ItemID"});
            if(!messageString.isEmpty() && messageString.contains("_")){
                String[] mArray = messageString.split("_");
                if(mArray.length == 2){
                    itmeType.getSelectionModel().select(mArray[0]);
                    changeItemIDList();
                    itemID.getSelectionModel().select(mArray[1]);
                }
            }
            StringControl.setMapValue(amount, inputMap, new String[]{"a", "Amount"});
            StringControl.setMapValue(remove, inputMap, new String[]{"Remove","r"});
        }
    }

}
