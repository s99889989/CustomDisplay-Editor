package com.daxton.function;

import com.daxton.Main;
import com.daxton.page.main.ServerMenuPage;
import org.bukkit.configuration.file.FileConfiguration;


import java.util.Timer;


public class CopyBackup {

    public static Timer timer;

    public static void start(){
        FileConfiguration config = Main.config;

        boolean backUpCopyEnable = config.getBoolean("Backups.BackUpCopyEnable");
        int backUpDelay = config.getInt("Backups.BackUpDelay");
        String worldPatch = config.getString("Backups.WorldPatch");
        String backUpFilePatch = config.getString("Backups.BackUpFilePatch");
        String backUpCopyPatch = config.getString("Backups.BackUpCopyPatch");
        //機器人設定
        boolean dcenable = config.getBoolean("Backups.Discord.enable");
        String token = config.getString("Backups.Discord.token");
        long channelID = config.getLong("Backups.Discord.channelID");

        boolean conditionSizeEnable = config.getBoolean("Backups.Condition.Size.Enable");
        String conditionSizeAmount = config.getString("Backups.Condition.Size.Amount");
        boolean conditionCrashEnable = config.getBoolean("Backups.Condition.Crash.Enable");
        String conditionCrashAmount = config.getString("Backups.Condition.Crash.Amount");

        ServerMenuPage.print("================================================");
        ServerMenuPage.print("自動套用備份程序版本: 1.4");
        ServerMenuPage.print("是否啟用套用備份程序: "+backUpCopyEnable);
        ServerMenuPage.print("延遲幾秒後開始執行: "+backUpDelay+" 秒");
        ServerMenuPage.print("世界路徑: "+worldPatch);
        ServerMenuPage.print("備份資料夾路徑: "+backUpFilePatch);
        ServerMenuPage.print("備份檔案要複製到哪: "+backUpCopyPatch);
        ServerMenuPage.print("--------------------------");
        ServerMenuPage.print("是否開啟備份顯示Discord訊息: "+dcenable);
        ServerMenuPage.print("機器人ID: "+token);
        ServerMenuPage.print("Discord頻道ID: "+channelID);
        ServerMenuPage.print("--------------------------");
        ServerMenuPage.print("條件: ");
        ServerMenuPage.print("  啟用大小條件: "+conditionSizeEnable);
        ServerMenuPage.print("  大小條件: "+conditionSizeAmount);
        ServerMenuPage.print("  啟用崩潰條件: "+conditionCrashEnable);
        ServerMenuPage.print("  崩潰時間誤差: "+conditionCrashAmount+" 秒");
        ServerMenuPage.print("------------------------------------------------");


        if(backUpCopyEnable){

            ServerMenuPage.print(backUpDelay+"秒後開始執行程序 !");

            timer = new Timer();
            timer.schedule(new CopyBackup2(), backUpDelay * 1000L);

        }else {
            ServerMenuPage.print("尚未啟用自動套用備份程序");
            ServerMenuPage.print("程序結束");
            ServerMenuPage.print("================================================");
        }

    }



}
