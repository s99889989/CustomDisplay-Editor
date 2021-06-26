package com.daxton.page;

import com.daxton.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SkillMenuPage {

    public static void display(){

        try {

            ResourceBundle language = Main.language;

            URL newURL = new URL(Main.resourcePath+"/page/SkillMenu.fxml");


            FXMLLoader loader = new FXMLLoader(newURL, language);

            Parent root = loader.load();

//            loader.getNamespace().put("labelText", "Foobar");
//            loader.setLocation(newURL);

            Main.mainWindows.setTitle("CustomDisplay-編輯器");
            Scene scene = new Scene(root, 1280, 800);
            scene.getStylesheets().add("resource/style.css");
            Main.mainWindows.setScene(scene);
            Main.mainWindows.getIcons().add(new Image(Main.resourcePath+"/icon.png"));
            Main.mainWindows.show();

        }catch (Exception exception){

        }

    }

}
