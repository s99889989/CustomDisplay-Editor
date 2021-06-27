package com.daxton.terminal;

import com.daxton.page.TerminalMenuPage;
import javafx.application.Platform;

import java.io.*;

public class CmdMain {

    public static Process process;

    public static String message;
    //開啟伺服器
    public static void startServer(){
        String cmd6 = "java -jar ./paper-1.16.5-778.jar nogui";
        if(process == null){
            try {
                process = Runtime.getRuntime().exec(cmd6);
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
        if(process != null){
            try {
                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                br.write("stop"+"\n");
                br.flush();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }


    }

    public static void command(String command){
        if(process != null){
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



    public static void forcedEnd(){
        if(process != null){
            process.destroy();
        }
    }

    public static void restopRun(){
        try {
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            br.write("stop");
            br.flush();
            br.close();
        }catch (IOException exception){
            exception.printStackTrace();
        }
        if(process.isAlive()){
            TerminalMenuPage.print("伺服器執行中!");
        }

    }



    public void killProcess() {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            rt.exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
