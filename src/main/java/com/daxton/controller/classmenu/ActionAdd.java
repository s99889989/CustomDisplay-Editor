package com.daxton.controller.classmenu;

import com.daxton.Main;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ClassMenu;
import com.daxton.function.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.bukkit.configuration.file.FileConfiguration;


import java.util.List;
import java.util.stream.Collectors;

public class ActionAdd {

    @FXML public ListView<String> actionList;

    @FXML//初始設定
    void initialize() {
        FileSearch.getTypeFileName("Class/Action/").forEach(s -> actionList.getItems().add(s.replace(".yml", "")));
        actionList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    //確定
    public void define(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            String nowClassFileName = classMenu.classList.getValue();

            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");

            List<String> actionConfigList = classConfig.getStringList(nowClassFileName+".Action");

            ///////////
            actionConfigList.addAll(actionList.getSelectionModel().getSelectedItems());
            List<String> newList = actionConfigList.stream().distinct().collect(Collectors.toList());
            classConfig.set(nowClassFileName+".Action", newList);
            classMenu.actionList.getItems().clear();
            classMenu.actionList.getItems().addAll(newList);
            Main.secondaryWindow.close();
        }

    }
    //取消
    public void cancel(){
        Main.secondaryWindow.close();
    }

}
