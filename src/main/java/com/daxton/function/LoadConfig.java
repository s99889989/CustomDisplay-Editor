package com.daxton.function;

import com.daxton.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadConfig {

    public static void load2(File firstFile){
        Main.openPath = firstFile.getAbsolutePath().replace("\\","/");
        //System.out.println(firstFile.getAbsolutePath());
        List<File> files = getFiles(firstFile.getAbsolutePath());
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
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(f);
            Manager.file_Config_Map.put(filePath, fileConfiguration);

        }
    }
    //獲取資料夾下.yml檔案
    public static List<File> getFiles(String path){
        File root = new File(path);
        List<File> files = new ArrayList<>();
        if(!root.isDirectory()){
            if(root.getAbsolutePath().endsWith(".yml")){
                files.add(root);
            }
        }else{
            File[] subFiles = root.listFiles();
            for(File f : subFiles){
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }

}
