package com.daxton.terminal;

import com.daxton.Main;
import com.daxton.function.Task;
import com.daxton.page.TerminalMenuPage;
import javafx.application.Platform;

import java.io.*;

public class CmdMain {

    public static Process process;

    public static String message;
    //開啟伺服器
    public static void startServer(){
        String path = Main.config.getString("Server-Settings.Startup-Parameters");
        String cmd = "java "+path+" nogui";

        if(process == null || !process.isAlive()){
            TerminalMenuPage.print("==========================================================================================");
            try {
                process = Runtime.getRuntime().exec(cmd);
                TerminalMenuPage.setServerState(true);
                new Thread(() -> {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String content = br.readLine();
                        while (content != null) {
                            message = content;
                            Platform.runLater(()-> { TerminalMenuPage.print(message);});
                            content = br.readLine();
                        }
                    }catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }).start();

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

    }
    //停止伺服器
    public static void stopServer(){
        if(process != null && process.isAlive()){
            TerminalMenuPage.print("==========================================================================================");
            try {
                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                br.write("stop"+"\n");
                br.flush();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }


    }

    public static void commandServer(String command){
        if(process != null && process.isAlive()){
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                bufferedWriter.write(command+"\n");
                //bufferedWriter.newLine();
                bufferedWriter.flush();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }

    }



    public static void forcedEndServer(){
        if(process != null && process.isAlive()){
            process.destroy();
            TerminalMenuPage.print("強制結束伺服器");
            TerminalMenuPage.print("==========================================================================================");
        }
    }

    public static void restartServer(){
        if(process != null && process.isAlive()){
            TerminalMenuPage.print("==========================================================================================");
            try {
                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                br.write("stop"+"\n");
                br.flush();
                Task.restart = true;
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }

    }



}
