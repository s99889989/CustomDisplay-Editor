package com.daxton.api;

import com.daxton.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class FxmlLoader {

    public static <T> T display(String path, Stage window){

        Main.secondaryWindow.close();

        try {
            URL newURL = new URL(Main.resourcePath+path);

            FXMLLoader loader = new FXMLLoader(newURL, Main.language);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("resource/style.css");

            window.getIcons().add(new Image(Main.resourcePath+"/icon.png"));
            window.setScene(scene);
            window.show();

            return loader.getController();

        }catch (Exception exception){
            return null;
        }
    }


}
