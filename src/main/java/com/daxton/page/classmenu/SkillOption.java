package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ClassMenu;
import com.daxton.controller.classmenu.SkillAdd;
import com.daxton.function.Manager;
import javafx.scene.control.SelectionMode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class SkillOption {

    public static void addDisplay(){
        SkillAdd skillAdd = FxmlLoader.display("/page/classmenu/SkillAdd.fxml", Main.secondaryWindow);
        if(skillAdd != null){
            FileSearch.getTypeFileName("Class/Skill/").forEach(s -> skillAdd.skillList.getItems().add(s.replace(".yml", "")));
            skillAdd.skillList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
    }

    public static void removeAction(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            String selectString = classMenu.skillsList.getSelectionModel().getSelectedItem();
            classMenu.skillsList.getItems().remove(selectString);

            String nowClassFileName = classMenu.classList.getValue();

            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");

            List<String> actionConfigList = classConfig.getStringList(nowClassFileName+".Skills");
            actionConfigList.remove(selectString);
            classConfig.set(nowClassFileName+".Skills", actionConfigList);

        }
    }

}
