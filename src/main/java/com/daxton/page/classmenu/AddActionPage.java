package com.daxton.page.classmenu;

import com.daxton.Main;
import com.daxton.controller.classmenu.AddAction;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class AddActionPage {

    public static Stage addActionWindow;

    public static void display(){

        addActionWindow = new Stage();


        try {
            URL newURL = new URL(Main.resourcePath+"/page/classmenu/AddAction.fxml");

            FXMLLoader loader = new FXMLLoader(newURL);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("resource/style.css");
            addActionWindow.setScene(scene);
            addActionWindow.show();

            AddAction addAction = loader.getController();
            if(addAction != null){
                addAction.addList();
            }

        }catch (Exception exception){
            //
        }



    }

}
