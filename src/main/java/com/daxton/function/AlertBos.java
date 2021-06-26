package com.daxton.function;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;


public class AlertBos {

    public static void display(){


        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Title");

        Label label = new Label();
        label.setText("請輸入要新增的職業檔案名稱");
        Font font = new Font(25);
        label.setFont(font);

        TextField textField = new TextField();
//        ComboBox<String> comboBox = new ComboBox<>();
//        comboBox.getItems().add("AAAA");

        Button defineButton = new Button("確定");
        defineButton.setOnAction(event -> {
            if(!textField.getText().isEmpty()){
                System.out.println(textField.getText());
                window.close();
            }
        });

        Button closeButton = new Button("取消");
        closeButton.setOnAction(event -> window.close());



        HBox hBox = new HBox(10);
        hBox.prefHeight(38);
        hBox.getChildren().addAll(defineButton, closeButton);

        VBox layout = new VBox(10);
        //layout.setPadding(10,10,10,10);
        layout.getChildren().addAll(label, textField, hBox);
        Insets insets = new Insets(10,10,10,10);
        layout.setPadding(insets);
        layout.setAlignment(Pos.CENTER);

//        URL location = Main.main.getClass().getResource("");
//        try {
//            URL newURL = new URL(location.toString().replace("/classes/java/main/com/daxton","")+"resources/main/resource/page/ClassMenu.fxml");
//            Parent root = FXMLLoader.load(newURL);
//            Scene scene = new Scene(root, 1280, 800);
//            window.setScene(scene);
//            window.showAndWait();
//        }catch (Exception exception){
//
//        }

        Scene scene = new Scene(layout, 350, 120);
        scene.getStylesheets().add("resource/style.css");
        window.setScene(scene);
        window.showAndWait();

    }

}
