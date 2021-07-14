package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.Discord;
import com.daxton.api.FxmlLoader;
import com.daxton.controller.main.ServerMenu;
import com.daxton.function.Manager;
import com.daxton.terminal.CmdMain;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;




public class ServerMenuPage {

    public static boolean serverState = false;

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
    //在列表發送訊息
    public static void print(String message){
        ServerMenu serverMenu = (ServerMenu) Manager.controller_Map.get("ServerMenu");

        if(serverMenu != null){
            serverMenu.message.appendText(message+"\n");
        }
        Manager.message_Map.appendText(message+"\n");
        //mm(message);
    }



    //輸入指令
    public static void enter(){
        ServerMenu serverMenu = (ServerMenu) Manager.controller_Map.get("ServerMenu");
        if(serverMenu != null){
            TextField textField = serverMenu.inputText;
            print("[Command]: "+textField.getText());
            CmdMain.commandServer(textField.getText());
            textField.clear();
        }

    }
    //設定伺服器狀態
    public static void setServerState(Boolean b){
        ServerMenu serverMenu = (ServerMenu) Manager.controller_Map.get("ServerMenu");
        if(serverMenu != null){
            if(b){
                serverMenu.state.setFill(Color.DARKGREEN);
                String open = Main.languageConfig.getString("Server-Menu.Status.Open");
                serverState = true;
                if(open != null){
                    serverMenu.state.setText(open);
                }else {
                    serverMenu.state.setText("Open");
                }
            }else {
                serverMenu.state.setFill(Color.DARKRED);
                String close = Main.languageConfig.getString("Server-Menu.Status.Close");
                serverState = false;
                if(close != null){
                    serverMenu.state.setText(close);
                }else {
                    serverMenu.state.setText("Close");
                }

            }
        }

    }
    //
    public static void mm(String message){
        if(message.contains("INFO]: Done")){
            CmdMain.serverStart = true;
            Discord.send(":white_check_mark:伺服器已開啟!");
        }
        if(message.contains("INFO]: Closing Server")){
            CmdMain.serverStart = false;
            Discord.send(":octagonal_sign:伺服器已停止!");
        }
        if(message.endsWith(" joined the game")){
            String name = message.substring(message.indexOf("INFO]: ")+7, message.indexOf(" joined"));
            Discord.send(":arrow_forward: "+name+" 已加入");
        }
        if(message.endsWith(" left the game")){
            String name = message.substring(message.indexOf("INFO]: ")+7, message.indexOf(" left"));
            Discord.send(":stop_button: "+name+" 已離開");
        }
        if(message.contains("INFO]: <") && message.contains("> ")){
            String name = message.substring(message.indexOf("INFO]: <")+8, message.indexOf("> "));
            String say = message.substring(message.indexOf("> ")+2);
            Discord.send(":speech_balloon: "+name+":"+say);
        }

        if(message.contains("INFO]: There are") && message.contains(" players online: ")){
            String number = message.substring(message.indexOf("There are ")+10,message.indexOf(" of a max of"));
            //Discord.setChannelName("message"+number);
        }

    }

}
