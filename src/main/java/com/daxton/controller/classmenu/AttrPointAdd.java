package com.daxton.controller.classmenu;

import com.daxton.Main;
import com.daxton.controller.main.ClassMenu;
import com.daxton.function.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public class AttrPointAdd {

    @FXML
    public ListView<String> attrPointList;

    //確定
    public void define(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        //////////
        String nowClassFileName = classMenu.classList.getValue();

        FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");

        List<String> actionConfigList = classConfig.getStringList(nowClassFileName+".Attributes_Point");

        ///////////
        actionConfigList.addAll(attrPointList.getSelectionModel().getSelectedItems());
        List<String> newList = actionConfigList.stream().distinct().collect(Collectors.toList());
        classConfig.set(nowClassFileName+".Attributes_Point", newList);
        classMenu.attributesPointList.getItems().clear();
        classMenu.attributesPointList.getItems().addAll(newList);

        Main.secondaryWindow.close();
    }
    //取消
    public void cancel(){
        Main.secondaryWindow.close();
    }

}
