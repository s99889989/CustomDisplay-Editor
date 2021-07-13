package com.daxton.config;

import com.daxton.Main;
import com.daxton.function.Manager;
import com.daxton.page.main.ServerMenuPage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileControl {

    //儲存檔案
    public static void save(){
        Manager.file_Config_Map.forEach((s, fileConfiguration) -> {
            File file = new File(Main.getOpenPath()+"/"+s);
            if(file.exists()){
                try {
                    fileConfiguration.save(file);
                }catch (IOException exception){
                    //
                }
            }else {
                try {
                    file.createNewFile();
                    fileConfiguration.save(file);
                }catch (IOException exception){
                    exception.printStackTrace();
                }

            }
        });
    }
    //刪除設定檔
    public static void deleteFile(String fileName){
        ServerMenuPage.print(Main.getOpenPath()+"/"+fileName);
        File file = new File(Main.getOpenPath()+"/"+fileName);
        if(file.exists()){
            file.delete();
        }
    }

    //建立新的檔案
    public static boolean createNewFile(String fileName){
        File file = new File(Main.getOpenPath()+"/"+fileName+".yml");
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
    //儲存檔案(一個檔案)
    public static void saveOne(String fileName, FileConfiguration fileConfiguration){
        File file = new File(Main.getOpenPath()+"/"+fileName+".yml");
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
        File file = new File(Main.getOpenPath()+"/"+fileName+".yml");
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
            //System.out.println(fileName);
            Manager.file_Name_Map.put(fileName+".yml", fileName+".yml");
            Manager.file_Config_Map.put(fileName+".yml", fileConfiguration);
        }
    }

    //讀取CustomDisplay的設定檔
    public static void loadCustomDisplaySettings(File firstFile){
        String savePath = firstFile.getPath().replace("\\","/");
        Main.setOpenPath(savePath);

        List<File> files = FileSearch.getFiles(firstFile.getAbsolutePath());
        Manager.file_Name_Map.clear();
        Manager.file_Config_Map.clear();
        for(File f : files){

            String filePath = f.getPath().replace(firstFile.getAbsolutePath()+"\\", "").replace("\\","/");
            //System.out.println(filePath);

            String fileName = filePath;
            while (fileName.contains("/")){
                fileName = fileName.replace(fileName.substring(0, fileName.indexOf("/")+1),"");
            }

            //System.out.println(filePath+" : "+fileName);
            Manager.file_Name_Map.put(filePath, fileName);
            //ServerMenuPage.print(filePath);
            if(f.getPath().endsWith(".yml")){
                FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(f);
                Manager.file_Config_Map.put(filePath, fileConfiguration);
            }

        }
    }

    //讀取CustomDisplay-Editor的設定檔
    public static void loadSettings(){
        List<File> files = FileSearch.getFiles(System.getProperty("user.dir")+"/CustomDisplay-Editor");
        //ServerMenuPage.print(System.getProperty("user.dir")+"/CustomDisplay-Editor");
        for(File f : files){

            String key = f.getPath().replace(System.getProperty("user.dir")+"\\CustomDisplay-Editor\\", "").replace("\\","/");
            //ServerMenuPage.print(key);

            String fileName = key;
            while (fileName.contains("/")){
                fileName = fileName.replace(fileName.substring(0, fileName.indexOf("/")+1),"");
            }

            //ServerMenuPage.print(key);
            //ServerMenuPage.print(f.getPath());
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(f);
            Manager.settings_Config_Map.put(key, fileConfiguration);

        }

    }

}
