package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ClassMenu;
import com.daxton.controller.classmenu.LevelAdd;
import com.daxton.function.Manager;
import javafx.scene.control.SelectionMode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class LevelOption {

    public static void addDisplay(){
        LevelAdd levelAdd = FxmlLoader.display("/page/classmenu/LevelAdd.fxml", Main.secondaryWindow);
        if(levelAdd != null){
            FileSearch.getTypeFileName("Class/Level/").forEach(s -> levelAdd.levelList.getItems().add(s.replace(".yml", "")));
            levelAdd.levelList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

    }

    public static void removeAction(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            String selectString = classMenu.levelList.getSelectionModel().getSelectedItem();
            classMenu.levelList.getItems().remove(selectString);

            String nowClassFileName = classMenu.classList.getValue();
            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");
            List<String> levelConfigList = classConfig.getStringList(nowClassFileName+".Level");
            levelConfigList.remove(selectString);

            classConfig.set(nowClassFileName+".Level", levelConfigList);

        }
    }

}
