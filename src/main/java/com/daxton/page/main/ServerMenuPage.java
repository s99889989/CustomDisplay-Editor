package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.controller.main.ServerMenu;
import com.daxton.function.Manager;
import com.daxton.terminal.CmdMain;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;




public class ServerMenuPage {

    //打開伺服器介面
    public static void display(){
        ServerMenu serverMenu = FxmlLoader.display("/page/main/ServerMenu.fxml", Main.mainWindow);
        Manager.controller_Map.put("ServerMenu", serverMenu);
        Main.mainMenuName = "ServerMenu";

        if(CmdMain.isActive()){
            setServerState(true);
        }

        if(Manager.message_Map != null && serverMenu != null){
            serverMenu.message.setText(Manager.message_Map.getText());
            serverMenu.message.positionCaret(Manager.message_Map.getText().length());
        }

    }

    public static void print(String message){
        ServerMenu serverMenu = (ServerMenu) Manager.controller_Map.get("ServerMenu");
        if(serverMenu != null){
            serverMenu.message.appendText(message+"\n");
        }
        Manager.message_Map.appendText(message+"\n");

    }

    public static void enter(){
        ServerMenu serverMenu = (ServerMenu) Manager.controller_Map.get("ServerMenu");
        if(serverMenu != null){
            TextField textField = serverMenu.inputText;
            print("[Command]: "+textField.getText());
            CmdMain.commandServer(textField.getText());
            textField.clear();
        }

    }

    public static void setServerState(Boolean b){
        ServerMenu serverMenu = (ServerMenu) Manager.controller_Map.get("ServerMenu");
        if(serverMenu != null){
            if(b){
                serverMenu.state.setFill(Color.DARKGREEN);
                String open = Main.languageConfig.getString("Server-Menu.Status.Open");
                if(open != null){
                    serverMenu.state.setText(open);
                }else {
                    serverMenu.state.setText("Open");
                }
            }else {
                serverMenu.state.setFill(Color.DARKRED);
                String close = Main.languageConfig.getString("Server-Menu.Status.Close");
                if(close != null){
                    serverMenu.state.setText(close);
                }else {
                    serverMenu.state.setText("Close");
                }

            }
        }

    }

}
