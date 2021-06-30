package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ClassMenu;
import com.daxton.controller.classmenu.PointAdd;
import com.daxton.function.Manager;
import javafx.scene.control.SelectionMode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class PointOption {

    public static void addDisplay(){
        PointAdd pointAdd = FxmlLoader.display("/page/classmenu/PointAdd.fxml", Main.secondaryWindow);
        if(pointAdd != null){
            FileSearch.getTypeFileName("Class/Level/").forEach(s -> pointAdd.pointList.getItems().add(s.replace(".yml", "")));
            pointAdd.pointList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

    }

    public static void removeAction(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            String selectString = classMenu.pointList.getSelectionModel().getSelectedItem();
            classMenu.pointList.getItems().remove(selectString);

            String nowClassFileName = classMenu.classList.getValue();
            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");
            List<String> actionConfigList = classConfig.getStringList(nowClassFileName+".Point");
            actionConfigList.remove(selectString);

            classConfig.set(nowClassFileName+".Point", actionConfigList);

        }
    }

}
