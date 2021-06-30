package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ClassMenu;
import com.daxton.controller.classmenu.AttrStatusAdd;
import com.daxton.function.Manager;
import javafx.scene.control.SelectionMode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class AttrStatusOption {

    public static void addDisplay(){
        AttrStatusAdd attrStatusAdd = FxmlLoader.display("/page/classmenu/AttrPointAdd.fxml", Main.secondaryWindow);
        if(attrStatusAdd != null){
            FileSearch.getTypeFileName("Class/Attributes/EntityStats/").forEach(s -> attrStatusAdd.attrStatusList.getItems().add(s.replace(".yml", "")));
            attrStatusAdd.attrStatusList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

    }

    public static void removeAction(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            String selectString = classMenu.attributesStatsList.getSelectionModel().getSelectedItem();
            classMenu.attributesStatsList.getItems().remove(selectString);

            String nowClassFileName = classMenu.classList.getValue();

            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");

            List<String> actionConfigList = classConfig.getStringList(nowClassFileName+".Attributes_Stats");
            actionConfigList.remove(selectString);
            classConfig.set(nowClassFileName+".Attributes_Stats", actionConfigList);

        }
    }

}
