package com.daxton.controller.classmenu;

import com.daxton.Main;
import com.daxton.function.Manager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class EditClass {

    public static Stage window;

    ChoiceBox<String> classList;

    @FXML
    TextField inputClass;

    public void display(ChoiceBox<String> classList){
        this.classList = classList;
        window = new Stage();

        URL location = Main.main.getClass().getResource("");
        try {
            URL newURL;
            if (location != null) {
                newURL = new URL(location.toString().replace("/classes/java/main/com/daxton","")+"resources/main/resource/page/classmenu/EditClass.fxml");
                Parent root = FXMLLoader.load(newURL);
                Scene scene = new Scene(root);
                scene.getStylesheets().add("resource/style.css");
                window.setScene(scene);
                window.showAndWait();
            }
        }catch (Exception exception){
            //
        }

    }


    public void define(){
        if(inputClass != null && Main.openPath != null){



            EditClass editclass = (EditClass) Manager.controller_Map.get("EditClass");
            File deleteFile = new File(Main.openPath +"/Class/Main/"+editclass.classList.getValue()+".yml");
            if(deleteFile.exists()){

                File file = new File(Main.openPath +"/Class/Main/"+inputClass.getText()+".yml");
                if(!file.exists()){
                    System.out.println(file.getPath());
                    try {
                        editclass.classList.getItems().add(inputClass.getText());
                        file.createNewFile();

                    }catch (IOException exception){
                        //
                    }
                }

                FileConfiguration classConfig = YamlConfiguration.loadConfiguration(deleteFile);
                //classConfig.set(inputClass.getText()+".Class_Name",inputClass.getText());

                String fileName = file.getPath().replace(Main.openPath +"/", "");
                Manager.file_Name_Map.put(fileName, fileName);
                Manager.file_Config_Map.put(fileName, classConfig);

                editclass.classList.getItems().remove(editclass.classList.getValue());
                deleteFile.delete();

                try {
                    classConfig.save(file);
                }catch (IOException exception){

                }
                window.close();
            }

        }
    }

    public void cancel(){
        window.close();
    }

}
