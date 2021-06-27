package com.daxton.page;

import com.daxton.Main;
import com.daxton.controller.ClassMenu;
import com.daxton.controller.TerminalMenu;
import com.daxton.function.Manager;
import com.daxton.terminal.CmdMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

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
            Main.mainWindows.setOnCloseRequest(event -> CmdMain.forcedEnd());
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
        terminalMenu.message.appendText(message+"\n");
        //terminalMenu.message.setText(terminalMenu.message.getText()+"\n"+message);
        //terminalMenu.message.setScrollTop(terminalMenu.message.getText().length()+2);
        //terminalMenu.message.caretPositionProperty();
    }

    public static void enter(){
        TerminalMenu terminalMenu = (TerminalMenu) Manager.controller_Map.get("TerminalMenu");
        TextField textField = terminalMenu.inputText;
        print("[指令]: "+textField.getText());
        CmdMain.command(textField.getText());
        textField.clear();
    }

}
