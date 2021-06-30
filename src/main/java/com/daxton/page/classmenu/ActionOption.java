package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ClassMenu;
import com.daxton.controller.classmenu.ActionAdd;
import com.daxton.function.Manager;
import javafx.scene.control.SelectionMode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ActionOption {

    public static void addDisplay(){
        ActionAdd addAction = FxmlLoader.display("/page/classmenu/ActionAdd.fxml", Main.secondaryWindow);
        if(addAction != null){
            FileSearch.getTypeFileName("Class/Action/").forEach(s -> addAction.actionList.getItems().add(s.replace(".yml", "")));
            addAction.actionList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

    }

    public static void removeAction(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            String selectString = classMenu.actionList.getSelectionModel().getSelectedItem();
            classMenu.actionList.getItems().remove(selectString);

            String nowClassFileName = classMenu.classList.getValue();

            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");

            List<String> actionConfigList = classConfig.getStringList(nowClassFileName+".Action");
            actionConfigList.remove(selectString);
            classConfig.set(nowClassFileName+".Action", actionConfigList);

        }
    }

}
