package com.daxton.controller.actionmenu.location;

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

public class Action_Hologram {

    @FXML TextField mark;
    @FXML TextField message;
    @FXML TextField removeMessage;
    @FXML TextField changeMessage;
    @FXML CheckBox teleport;
    @FXML CheckBox delete;
    @FXML ListView<String> itmeType;
    @FXML ListView<String> item;

    @FXML//初始設定
    void initialize() {
        FileSearch.getTypeFileName("Items/item").forEach(s -> itmeType.getItems().add(s.replace(".yml","")));
    }

    //物品類別
    public void onChangeContentFind(){
        changeItemIDList();

    }

    public void changeItemIDList(){
        if(Manager.file_Config_Map.get("Items/item/"+itmeType.getSelectionModel().getSelectedItem()+".yml") != null){

            item.getItems().clear();
            item.getItems().add("");
            FileConfiguration fileConfiguration = Manager.file_Config_Map.get("Items/item/"+itmeType.getSelectionModel().getSelectedItem()+".yml");
            if(fileConfiguration.getConfigurationSection("") != null){
                fileConfiguration.getConfigurationSection("").getKeys(false).forEach(s -> item.getItems().add(s));
            }

        }
    }

    //改變設定時
    public void onChangeContent(){
        ActionMenuPage.keyValue.clear();
        if(!StringControl.getValue(itmeType).isEmpty() && !StringControl.getValue(item).isEmpty())
            ActionMenuPage.keyValue.put("ItemID", StringControl.getValue(itmeType)+"_"+StringControl.getValue(item));

        ActionMenuPage.keyValue.put("Mark", StringControl.getValue(mark));
        ActionMenuPage.keyValue.put("Message", StringControl.getValue(message));
        ActionMenuPage.keyValue.put("RemoveMessage", StringControl.getValue(removeMessage));
        ActionMenuPage.keyValue.put("ChangeMessage", StringControl.getValue(changeMessage));
        ActionMenuPage.keyValue.put("Teleport", StringControl.getValue(teleport));
        ActionMenuPage.keyValue.put("Delete", StringControl.getValue(delete));


        ActionMenuPage.changeActionContnet("Hologram");
    }

    //獲取初始值
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();
            Map<String, String> inputMap = FileSearch.setClassAction(input);

            String itemString = StringConversion.getActionKey(inputMap, new String[]{"iid", "ItemID"});
            if(!itemString.isEmpty() && itemString.contains("_")){
                String[] mArray = itemString.split("_");
                if(mArray.length == 2){
                    itmeType.getSelectionModel().select(mArray[0]);
                    changeItemIDList();
                    item.getSelectionModel().select(mArray[1]);
                }
            }
            StringControl.setMapValue(mark, inputMap, new String[]{"Mark", "mk"});
            StringControl.setMapValue(message, inputMap, new String[]{"Message", "M"});
            StringControl.setMapValue(removeMessage, inputMap, new String[]{"RemoveMessage", "RM"});
            StringControl.setMapValue(changeMessage, inputMap, new String[]{"ChangeMessage", "cm"});
            StringControl.setMapValue(teleport, inputMap, new String[]{"Teleport", "Tp"});
            StringControl.setMapValue(delete, inputMap, new String[]{"Delete", "D"});

        }
    }

}
