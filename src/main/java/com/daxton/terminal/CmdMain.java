package com.daxton.terminal;

import com.daxton.Main;
import com.daxton.api.Discord;
import com.daxton.function.CopyBackup;
import com.daxton.function.Task;
import com.daxton.page.main.ServerMenuPage;
import javafx.application.Platform;


import java.io.*;

public class CmdMain {

    public static Process process;

    public static String message;

    public static boolean serverStart = false;

    //開啟伺服器
    public static void startServer(){
        String path = Main.config.getString("Server-Settings.Startup-Parameters");
        String cmd = "java "+path+" nogui";

        if(process == null || !process.isAlive()){
            ServerMenuPage.print("==========================================================================================");
            try {
                process = Runtime.getRuntime().exec(cmd);

                ServerMenuPage.setServerState(true);
                new Thread(() -> {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

                        String content = br.readLine();
                        while (content != null) {
                            message = content;
                            Platform.runLater(()-> ServerMenuPage.print(message));
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
        //CopyBackup.start();
        if(process != null && process.isAlive()){
            ServerMenuPage.print("==========================================================================================");
            try {
                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                br.write("stop"+"\n");
                br.flush();

            }catch (IOException exception){
                exception.printStackTrace();
            }

        }


    }
    //向伺服器發送指令
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

    //強制結束伺服器
    public static void forcedEndServer(){
        if(process != null && process.isAlive()){
            process.destroy();
            //ServerMenuPage.print("強制結束伺服器");
            ServerMenuPage.print("==========================================================================================");
            serverStart = false;
        }
    }
    //重啟伺服器
    public static void restartServer(){
        if(process != null && process.isAlive()){
            ServerMenuPage.print("==========================================================================================");
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

    public static boolean isActive(){
        return process != null && process.isAlive();
    }


}
