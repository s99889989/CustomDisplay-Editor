package com.daxton.function;

import com.daxton.page.TerminalMenuPage;
import com.daxton.terminal.CmdMain;

import java.util.TimerTask;

public class Task extends TimerTask {

    public static boolean restart;

    public void run(){

        if(CmdMain.process != null && CmdMain.process.isAlive()){
            TerminalMenuPage.setServerState(true);
        }else {
            TerminalMenuPage.setServerState(false);
            if(restart){
                restart = false;
                CmdMain.startServer();
            }
        }


    }


}
