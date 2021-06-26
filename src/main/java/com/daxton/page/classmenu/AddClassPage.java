package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.controller.ClassMenu;
import com.daxton.controller.classmenu.AddClass;
import com.daxton.function.Manager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AddClassPage {

    public static Stage addClassWindow;



    public static void display(){

        addClassWindow = new Stage();

        URL location = Main.main.getClass().getResource("");
        try {
            URL newURL = new URL(location.toString().replace("/classes/java/main/com/daxton","")+"resources/main/resource/page/classmenu/AddClass.fxml");
            FXMLLoader loader = new FXMLLoader(newURL);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("resource/style.css");
            addClassWindow.setScene(scene);
            addClassWindow.showAndWait();
        }catch (Exception exception){
            //
        }

    }



}
