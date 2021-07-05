package com.daxton.function;

import com.daxton.Main;
import com.daxton.api.Discord;
import com.daxton.api.backup.*;
import com.daxton.page.main.ServerMenuPage;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.TimerTask;

public class CopyBackup2 extends TimerTask {

    public void run(){
        FileConfiguration config = Main.config;
        String backUpFilePatch = config.getString("Backups.BackUpFilePatch");
        String worldPatch = config.getString("Backups.WorldPatch");
        String backUpCopyPatch = config.getString("Backups.BackUpCopyPatch");
        String conditionCrashAmount = config.getString("Backups.Condition.Crash.Amount");
        ServerMenuPage.print("開始執行，套用最新備份程序 !");
        //複製備份檔案到world資料夾內
        String packupName = Time.lastBackUpName(backUpFilePatch);
        boolean crashEnable = config.getBoolean("Backups.Condition.Crash.Enable");
        if(crashEnable){
            boolean crashB = CrashTime.lastBackUpName("./crash-reports");
            if(crashB){
                copy(packupName, worldPatch, backUpFilePatch, backUpCopyPatch);

            }else {
                ServerMenuPage.print("------------------------------------------------");
                ServerMenuPage.print("最新崩潰報告距離目前時間為 "+CrashTime.mmm+"秒");
                ServerMenuPage.print("超過設定時間 "+conditionCrashAmount+"秒 取消使用備份");
                ServerMenuPage.print("================================================");
                CopyBackup.timer.cancel();
            }

        }else {
            copy(packupName, worldPatch, backUpFilePatch, backUpCopyPatch);

        }

    }

    public void copy(String packupName, String worldPatch, String backUpFilePatch, String backUpCopyPatch){
        try {

            if(packupName.equals("null")){
                ServerMenuPage.print("------------------------------------------------");
                ServerMenuPage.print("找不到可用備份 !");
                ServerMenuPage.print("結束程序 !");
                ServerMenuPage.print("================================================");
                CopyBackup.timer.cancel();
            }else {

                ServerMenuPage.print("------------------------------------------------");
                ServerMenuPage.print("開始刪除目前世界 !");
                //刪除world資料夾
                DeleteFile.deleteDirectory(worldPatch);
                ServerMenuPage.print("刪除世界完成 !");
                ServerMenuPage.print("------------------------------------------------");

                //建立空world資料夾
                File file = new File(worldPatch);
                file.mkdir();

                ServerMenuPage.print("開始複製備份世界 !");
                Discord.send("開始複製備份世界 !");
                ServerMenuPage.print("使用的備份名稱: "+packupName);
                ServerMenuPage.print("備份檔案大小: "+ Size.fileSize(backUpFilePatch+"/"+packupName));

                Discord.send("使用的備份名稱: "+packupName);
                Discord.send("備份檔案大小: " + Size.fileSize(backUpFilePatch + "/" + packupName));
                try {
                    String zipFilePath = backUpFilePatch+"/"+packupName;
                    zip3.unzip(zipFilePath, backUpCopyPatch);
                }catch (Exception exception){
                    CopyBackup.timer.cancel();
                }

                ServerMenuPage.print("完成複製備份世界 !");

                Discord.send("完成複製備份世界 !");


                ServerMenuPage.print("================================================");

                LocalDateTime dateTime = LocalDateTime.now();
                FileWriter fw = new FileWriter("./backups/backuplog.txt",true);
                fw.write("\n");
                fw.write("使用的備份名稱: "+packupName+" 完成時間: "+dateTime);
                fw.write("\n");
                fw.write("------------------------------------------------");
                fw.flush();
                fw.close();
                CopyBackup.timer.cancel();
            }

        }catch (IOException exception){
            CopyBackup.timer.cancel();
        }
    }

}
