package com.daxton.page;

import com.daxton.Main;
import com.daxton.controller.ClassMenu;
import com.daxton.function.Manager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClassMenuPage {

    //打開職業編輯介面
    public static void display(){

        try {
            URL newURL = new URL(Main.resourcePath+"/page/ClassMenu.fxml");

            ResourceBundle language = Main.language;
//            Locale locale = Locale.forLanguageTag("lang.zh");
//            ResourceBundle language = ResourceBundle.getBundle(Main.resourcePath+"/language");


            FXMLLoader loader = new FXMLLoader(newURL, language);


            Parent root = loader.load();

            Main.mainWindows.setTitle("CustomDisplay-編輯器");
            Scene scene = new Scene(root);
            scene.getStylesheets().add("resource/style.css");
            Main.mainWindows.getIcons().add(new Image(Main.resourcePath+"/icon.png"));
            Main.mainWindows.setScene(scene);
            Main.mainWindows.show();

            ClassMenu classMenu = loader.getController();
            Manager.controller_Map.put("ClassMenu", classMenu);

        }catch (IOException exception){
            //
        }

    }

    public static void addClassList(String className){

        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            classMenu.classList.getItems().add(className);
        }


    }

}
