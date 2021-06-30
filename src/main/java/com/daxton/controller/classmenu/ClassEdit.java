package com.daxton.controller.classmenu;

import com.daxton.Main;
import com.daxton.controller.main.ClassMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ServerMenuPage;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import org.bukkit.configuration.file.FileConfiguration;


public class ClassEdit {

    @FXML TextField inputClass;

    public void define(){
        ServerMenuPage.print("測試");
        if(inputClass != null){ // && Main.openPath != null

            ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");

//            ServerMenuPage.print(inputClass.getText());
//            ServerMenuPage.print(classMenu.classList.getValue());

            String changeClassFileName = inputClass.getText();
            String nowClassFileName = classMenu.classList.getValue();

            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+nowClassFileName+".yml");


            //ServerMenuPage.print(classConfig.getString(nowClassFileName+".Class_Name"));

//            File deleteFile = new File(Main.openPath +"/Class/Main/"+editclass.classList.getValue()+".yml");
//            if(deleteFile.exists()){
//
//                File file = new File(Main.openPath +"/Class/Main/"+inputClass.getText()+".yml");
//                if(!file.exists()){
//                    System.out.println(file.getPath());
//                    try {
//                        editclass.classList.getItems().add(inputClass.getText());
//                        file.createNewFile();
//
//                    }catch (IOException exception){
//                        //
//                    }
//                }
//
//                FileConfiguration classConfig = YamlConfiguration.loadConfiguration(deleteFile);
//                //classConfig.set(inputClass.getText()+".Class_Name",inputClass.getText());
//
//                String fileName = file.getPath().replace(Main.openPath +"/", "");
//                Manager.file_Name_Map.put(fileName, fileName);
//                Manager.file_Config_Map.put(fileName, classConfig);
//
//                editclass.classList.getItems().remove(editclass.classList.getValue());
//                deleteFile.delete();
//                ClassOption.classOptionWindow.close();
//                try {
//                    classConfig.save(file);
//                }catch (IOException exception){
//
//                }
//
//            }

        }
    }

    public void cancel(){
        Main.secondaryWindow.close();
    }

}
