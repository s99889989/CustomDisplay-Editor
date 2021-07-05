package com.daxton.api.backup;

import com.daxton.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;


public class Time {

    public Time(){

    }

    //給路徑回傳最新檔案名稱
    public static String lastBackUpName(String patch){
        String outPut = "null";
        File folder1 = new File(patch);
        File[] files = folder1.listFiles();

        FileConfiguration config = Main.config;
        boolean conditionSizeEnable = config.getBoolean("Backups.Condition.Size.Enable");
        String conditionSizeAmount = config.getString("Backups.Condition.Size.Amount");

        String[] s = conditionSizeAmount.split(" ");
        String backUpConditionSize = s[0];
        String backUpConditionUnit = s[1];

        File lastFile = null;
        if(files != null){
            for(File file : files){
                if(file.getName().contains(".zip")){
                    String size = Size.fileSize(patch+"/"+file.getName());
                    if(conditionSizeEnable){
                        if(size.contains(backUpConditionUnit)){
                            String size2 = size.split(" ")[0];
                            double input = Double.parseDouble(backUpConditionSize);
                            double fileSize = Double.parseDouble(size2);
                            if(fileSize > input){
                                if(lastFile == null){
                                    lastFile = file;

                                }else if(lastFile.lastModified() < file.lastModified()){
                                    lastFile = file;
                                }
                            }

                        }
                    }else {
                        if(lastFile == null){
                            lastFile = file;

                        }else if(lastFile.lastModified() < file.lastModified()){
                            lastFile = file;
                        }
                    }

                }
            }
        }


        if(lastFile != null){
            outPut = lastFile.getName();
        }

        return outPut;
    }

    public static void time(){



        File folder1 = new File("C:/Users/Gary/Desktop/TestJAVA/backup");
        File[] files = folder1.listFiles();
        String[] list1 = folder1.list();


        File lastFile = null;
        for(File file : files){
            if(lastFile == null){
                lastFile = file;
            }else if(lastFile.lastModified() < file.lastModified()){
                lastFile = file;
            }

        }
        System.out.println(lastFile.getName());

        for (int i = 0; i < list1.length; i++) {
            //read(list1[i]);
            //System.out.println(list1[i]);
        }



    }



}
