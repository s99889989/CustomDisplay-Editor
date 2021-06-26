package com.daxton.terminal;

import com.daxton.page.TerminalMenuPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdMain {

    public static void runrun2(){
        //String cmd = "ipconfig";
        String cmd = "cmd /c start  "+System.getProperty("user.dir")+"/RunServer.bat";
        //String cmd = "-d64 -server -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+UseStringDeduplication -Xms1G -Xmx1G -Dfile.encoding=UTF-8 -XX:hashCode=5 -jar paper-1.16.5-778.jar";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String content = br.readLine();
            while (content != null) {
                //System.out.println(content);
                TerminalMenuPage.print(content);
                content = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void runrun(){
        TerminalMenuPage.print("開始");
// 執行批處理檔案
        //String strcmd = "cmd /c start  E:\\run.bat";
        String strcmd = "cmd /c start  "+System.getProperty("user.dir")+"/RunServer.bat";
        Runtime rt = Runtime.getRuntime();
        Process ps = null;
        try {
            ps = rt.exec(strcmd);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            ps.waitFor();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            InputStream child_in = ps.getInputStream();
            int c;
            while ((c = child_in.read()) != -1) {
                //System.out.print((char)c);
                char cc = (char)c;
                String mm = String.valueOf(c);
                TerminalMenuPage.print(mm);
            }
        }catch (IOException e) {
            //
        }
        int i = ps.exitValue();
        if (i == 0) {
            System.out.println("執行完成.");
        } else {
            System.out.println("執行失敗.");
        }
        ps.destroy();
        ps = null;


        // 批處理執行完後，根據cmd.exe程序名稱
        // kill掉cmd視窗
        new CmdMain().killProcess();
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
