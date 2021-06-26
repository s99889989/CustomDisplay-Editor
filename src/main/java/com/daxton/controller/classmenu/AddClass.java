package com.daxton.controller.classmenu;

import com.daxton.Main;
import com.daxton.config.FileControl;
import com.daxton.page.ClassMenuPage;
import com.daxton.page.classmenu.AddClassPage;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;


import org.bukkit.configuration.file.FileConfiguration;


public class AddClass {

    @FXML TextField fileName;

    @FXML TextField className;


    public void define(){

        if(fileName != null && className != null && Main.openPath != null){
            String addClassName = className.getText();

            String addFileName = fileName.getText();

            if(FileControl.createNewFile("/Class/Main/"+addFileName)){
                ClassMenuPage.addClassList(addFileName);
            }

            FileConfiguration classConfig = FileControl.setValueNoMap("/Class/Main/"+addFileName, addFileName+".Class_Name", addClassName);

            FileControl.inputMap("Class/Main/"+addFileName, classConfig);

            FileControl.saveOne("/Class/Main/"+addFileName, classConfig);
            AddClassPage.addClassWindow.close();
        }
    }

    public void cancel(){
        AddClassPage.addClassWindow.close();
    }

}
