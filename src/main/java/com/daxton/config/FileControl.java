package com.daxton.config;

import com.daxton.Main;
import com.daxton.function.Manager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileControl {

    public static void save(){
        Manager.file_Config_Map.forEach((s, fileConfiguration) -> {
            File file = new File(Main.openPath+"/"+s);
            if(file.exists()){
                try {
                    fileConfiguration.save(file);
                }catch (IOException exception){
                    //
                }
            }
        });
    }
    //建立新的檔案
    public static boolean createNewFile(String fileName){
        File file = new File(Main.openPath+fileName+".yml");
        if(!file.exists()){
            try {

                file.createNewFile();

            }catch (Exception exception){
                //
            }
            return true;
        }else {
            return false;
        }
    }

    public static void saveOne(String fileName, FileConfiguration fileConfiguration){
        File file = new File(Main.openPath+fileName+".yml");
        if(file.exists() && fileConfiguration != null){
            try {
                fileConfiguration.save(file);
            }catch (IOException exception){
                //
            }
        }
    }

    //設置FileConfiguration內的值，並回傳FileConfiguration (當檔案沒有在Map裡面)
    public static FileConfiguration setValueNoMap(String fileName, String setPath, Object value){
        File file = new File(Main.openPath +fileName+".yml");
        if(file.exists()){
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
            fileConfiguration.set(setPath, value);
            return fileConfiguration;
        }else {
            return null;
        }
    }

    //設置FileConfiguration內的值，並回傳FileConfiguration
    public static FileConfiguration setValue(String fileName, String setPath, Object value){
        FileConfiguration fileConfiguration = Manager.file_Config_Map.get(fileName+".yml");
        if(fileConfiguration != null){
            fileConfiguration.set(setPath, value);
            return fileConfiguration;
        }else {
            return null;
        }
    }

    //把FileConfiguration儲存至Map
    public static void inputMap(String fileName, FileConfiguration fileConfiguration){
        if(fileName != null && fileConfiguration != null){
            System.out.println(fileName);
            Manager.file_Name_Map.put(fileName+".yml", fileName+".yml");
            Manager.file_Config_Map.put(fileName+".yml", fileConfiguration);
        }
    }



}
