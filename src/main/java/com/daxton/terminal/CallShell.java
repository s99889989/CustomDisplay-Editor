package com.daxton.terminal;

import com.daxton.page.TerminalMenuPage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class CallShell {

    public void callScript(String script, String args, String... workspace){
        try {
            String cmd = "sh " + script + " " + args;
//        	String[] cmd = {"sh", script, "4"};
            File dir = null;
            if(workspace[0] != null){
                dir = new File(workspace[0]);
                TerminalMenuPage.print(workspace[0]);
                System.out.println(workspace[0]);
            }
            String[] evnp = {"val=2", "call=Bash Shell", "cost=Bash Shell"};

            String cmd2 = "cmd /c start " + System.getProperty("user.dir")+"/test.sh";
            TerminalMenuPage.print(cmd2);
            Process process = Runtime.getRuntime().exec(cmd2, evnp, dir);
            //Process process = Runtime.getRuntime().exec(cmd, evnp, dir);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                TerminalMenuPage.print(line);
            }
            input.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
