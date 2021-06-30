package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.controller.classmenu.ClassAdd;
import com.daxton.controller.classmenu.ClassEdit;
import com.daxton.controller.main.ClassMenu;
import com.daxton.function.Manager;

import java.io.File;

public class ClassOption {

    //新增職業介面
    public static void addDisplay(){
        ClassAdd classAdd = FxmlLoader.display("/page/classmenu/ClassAdd.fxml", Main.secondaryWindow);


    }

    //編輯職業介面
    public static void editDisplay(){
        ClassEdit classEdit = FxmlLoader.display("/page/classmenu/ClassEdit.fxml", Main.secondaryWindow);

    }

    //移除職業
    public static void removeClass(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");

        String chosseClassName = classMenu.classList.getValue();
        classMenu.classList.getItems().remove(chosseClassName);

        File file = new File(Main.getOpenPath() +"/Class/Main/"+chosseClassName+".yml");
        if(file.exists()){
            if(file.delete()) {
                System.out.println("DELETE");
            }
        }

        Manager.file_Name_Map.remove("Class/Main/"+chosseClassName+".yml");
        Manager.file_Config_Map.remove("Class/Main/"+chosseClassName+".yml");
    }


}
