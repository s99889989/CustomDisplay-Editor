package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ClassMenu;
import com.daxton.controller.classmenu.EqmStatusAdd;
import com.daxton.function.Manager;
import javafx.scene.control.SelectionMode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class EqmStatusOption {

    public static void addDisplay(){
        EqmStatusAdd eqmStatusAdd = FxmlLoader.display("/page/classmenu/EqmStatusAdd.fxml", Main.secondaryWindow);
        if(eqmStatusAdd != null){
            FileSearch.getTypeFileName("Class/Attributes/EquipmentStats/").forEach(s -> eqmStatusAdd.eqmStatusList.getItems().add(s.replace(".yml", "")));
            eqmStatusAdd.eqmStatusList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

    }

    public static void removeAction(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            String selectString = classMenu.equipmentStatsList.getSelectionModel().getSelectedItem();
            classMenu.equipmentStatsList.getItems().remove(selectString);

            String nowClassFileName = classMenu.classList.getValue();

            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");

            List<String> actionConfigList = classConfig.getStringList(nowClassFileName+".Equipment_Stats");
            actionConfigList.remove(selectString);
            classConfig.set(nowClassFileName+".Equipment_Stats", actionConfigList);

        }
    }

}
