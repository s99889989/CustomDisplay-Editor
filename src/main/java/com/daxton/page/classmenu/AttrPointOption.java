package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ClassMenu;
import com.daxton.controller.classmenu.AttrPointAdd;
import com.daxton.function.Manager;
import javafx.scene.control.SelectionMode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class AttrPointOption {

    public static void addDisplay(){
        AttrPointAdd attrPointAdd = FxmlLoader.display("/page/classmenu/AttrPointAdd.fxml", Main.secondaryWindow);
        if(attrPointAdd != null){
            FileSearch.getTypeFileName("Class/Attributes/Point/").forEach(s -> attrPointAdd.attrPointList.getItems().add(s.replace(".yml", "")));
            attrPointAdd.attrPointList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

    }

    public static void removeAction(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            String selectString = classMenu.attributesPointList.getSelectionModel().getSelectedItem();
            classMenu.attributesPointList.getItems().remove(selectString);

            String nowClassFileName = classMenu.classList.getValue();

            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");

            List<String> actionConfigList = classConfig.getStringList(nowClassFileName+".Attributes_Point");
            actionConfigList.remove(selectString);
            classConfig.set(nowClassFileName+".Attributes_Point", actionConfigList);

        }
    }

}
