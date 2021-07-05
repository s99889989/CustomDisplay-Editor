package com.daxton.api.backup;

import com.daxton.Main;
import com.daxton.page.main.ServerMenuPage;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashTime {

    public static int mmm = 0;

    public CrashTime(){

    }

    /**給路徑回傳最新檔案名稱**/
    public static boolean lastBackUpName(String patch){
        boolean outPut = false;
        File folder1 = new File(patch);
        File[] files = folder1.listFiles();
        Date dd=new Date();
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String end=sim.format(dd);
        long en= 0;
        try {
            en=sim.parse(end).getTime();
        }catch (Exception exception){

        }
        FileConfiguration config = Main.config;
        boolean crashB = config.getBoolean("Backups.Condition.Crash.Enable");
        int crashA = config.getInt("Backups.Condition.Crash.Amount");
        if(files != null){
            for(File file : files){
                long st = file.lastModified();
                //System.out.println("現在時間: "+end);
                int day=(int) ((en-st)/86400000);
                //计算小时
                int h=(int) (((en-st)%86400000)/3600000);
                //计算分钟
                int m=(int)(((en-st)%86400000)%3600000)/60000;
                //计算秒
                int s=(int)((((en-st)%86400000)%3600000)%60000)/1000;
                //System.out.println(day+"天 "+h+"小時 "+m+"分鐘 "+s+"秒");

                int mm=(int)(en-st)/1000;

                //System.out.println(mm+"秒");
                if(mmm == 0){
                    mmm = mm;
                }else if(mm < mmm){
                    mmm = mm;
                }
                //ServerMenuPage.print(mm+" : "+crashA);
                if(mmm > 0 && mmm < crashA){
                    outPut = true;
                }

//            try {
//                BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
//                System.out.println("建立"+attr.creationTime()+" 最後修改: "+attr.lastModifiedTime());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            }
        }




        return outPut;
    }




}
