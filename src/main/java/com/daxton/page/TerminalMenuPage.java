package com.daxton.page;

import com.daxton.Main;
import com.daxton.controller.TerminalMenu;
import com.daxton.function.Manager;
import com.daxton.function.Task;
import com.daxton.terminal.CmdMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class TerminalMenuPage {



    //打開職業編輯介面
    public static void display(){

        try {
            URL newURL = new URL(Main.resourcePath+"/page/TerminalMenu.fxml");

            ResourceBundle language = Main.language;

            FXMLLoader loader = new FXMLLoader(newURL, language);


            Parent root = loader.load();

            Main.mainWindows.setTitle("CustomDisplay-編輯器");
            Main.mainWindows.setOnCloseRequest(event -> {
                Main.timer.cancel();
                CmdMain.forcedEndServer();
            });
            Scene scene = new Scene(root);
            scene.getStylesheets().add("resource/style.css");
            Main.mainWindows.getIcons().add(new Image(Main.resourcePath+"/icon.png"));
            Main.mainWindows.setScene(scene);
            Main.mainWindows.show();

            TerminalMenu terminalMenu = loader.getController();
            Manager.controller_Map.put("TerminalMenu", terminalMenu);


        }catch (IOException exception){
            //
        }

    }

    public static void print(String message){
        TerminalMenu terminalMenu = (TerminalMenu) Manager.controller_Map.get("TerminalMenu");
        if(terminalMenu != null){
            terminalMenu.message.appendText(message+"\n");
        }

    }

    public static void enter(){
        TerminalMenu terminalMenu = (TerminalMenu) Manager.controller_Map.get("TerminalMenu");
        if(terminalMenu != null){
            TextField textField = terminalMenu.inputText;
            print("[指令]: "+textField.getText());
            CmdMain.commandServer(textField.getText());
            textField.clear();
        }

    }

    public static void setServerState(Boolean b){
        TerminalMenu terminalMenu = (TerminalMenu) Manager.controller_Map.get("TerminalMenu");
        if(terminalMenu != null){
            if(b){
                terminalMenu.state.setFill(Color.DARKGREEN);
                terminalMenu.state.setText("開啟");
            }else {
                terminalMenu.state.setFill(Color.DARKRED);
                terminalMenu.state.setText("關閉");
            }
        }

    }

}
