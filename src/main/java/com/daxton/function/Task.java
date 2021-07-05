package com.daxton.function;

import com.daxton.page.main.ServerMenuPage;
import com.daxton.terminal.CmdMain;

import java.util.TimerTask;

public class Task extends TimerTask {

    public static boolean restart;

    public void run(){

        if(CmdMain.process != null && CmdMain.process.isAlive()){
            ServerMenuPage.setServerState(true);
        }else {
            ServerMenuPage.setServerState(false);
            if(CmdMain.serverStart){
                CmdMain.serverStart = false;
                CopyBackup.start();
            }
            if(restart){
                restart = false;
                CmdMain.startServer();
            }
        }


    }


}
