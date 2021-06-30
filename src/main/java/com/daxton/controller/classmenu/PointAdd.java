package com.daxton.controller.classmenu;

import com.daxton.Main;
import com.daxton.controller.main.ClassMenu;
import com.daxton.function.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public class PointAdd {

    @FXML
    public ListView<String> pointList;

    //確定
    public void define(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        //////////
        String nowClassFileName = classMenu.classList.getValue();

        FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");

        List<String> actionConfigList = classConfig.getStringList(nowClassFileName+".Point");

        ///////////
        actionConfigList.addAll(pointList.getSelectionModel().getSelectedItems());
        List<String> newList = actionConfigList.stream().distinct().collect(Collectors.toList());
        classConfig.set(nowClassFileName+".Point", newList);
        classMenu.pointList.getItems().clear();
        classMenu.pointList.getItems().addAll(newList);

        Main.secondaryWindow.close();
    }
    //取消
    public void cancel(){
        Main.secondaryWindow.close();
    }

}
