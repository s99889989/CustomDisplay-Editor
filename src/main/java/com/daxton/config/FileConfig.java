package com.daxton.config;

import com.daxton.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

public class FileConfig {

    public static FileConfiguration create(){
        File file = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor");
        if(!file.exists()){
            file.mkdir();
        }
        File file2 = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/config.yml");
        if(!file2.exists()){
            //建立設定檔
            saveResource("resource/config.yml","./CustomDisplay-Editor/config.yml", false);

            if(file2.exists()){
                return YamlConfiguration.loadConfiguration(file2);
            }

        }else {
            return YamlConfiguration.loadConfiguration(file2);
        }
        return null;
    }


    public static Locale getLocale(){
        FileConfiguration fileConfiguration = Main.config;

        String language = fileConfiguration.getString("Language");

        Locale locale;

        if (language != null) {
            switch(language.toLowerCase()){
                case "english":
                    locale = new Locale("en", "");
                    break;
                case "chinese_tw":
                    locale = new Locale("zh", "TW");
                    break;
                case "chinese_cn":
                    locale = new Locale("zh", "CN");
                    break;
                default:
                    locale = new Locale("", "");
                    break;
            }
        }else {
            locale = new Locale("en", "");
        }

        return locale;
    }

    public static void saveResource(String resourcePath,String savePath, boolean replace) {


        InputStream in = getResource(resourcePath);

        File outFile = new File(savePath);

        int lastIndex = savePath.lastIndexOf('/');
        File outDir = new File(savePath.substring(0, lastIndex >= 0 ? lastIndex : 0));
        if (!outDir.exists()) {
            outDir.mkdirs();
        }


        try {
            if (!outFile.exists() || replace) {
                File outFileF = new File(savePath);
                OutputStream out = new FileOutputStream(outFileF);
                byte[] buf = new byte[1024];
                int len;
                if (in != null) {
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.close();
                    in.close();
                }

            }
        } catch (IOException exception) {
            //System.out.println("無法儲存 " + outFile.getName() + " 到 " + outFile);
        }

    }


    public static InputStream getResource(String filename) {

        if (filename == null) {
            throw new IllegalArgumentException("文件名不能為空");
        }

        try {
            URL url = Main.main.getClass().getClassLoader().getResource(filename);
            if (url == null) {
                return null;
            }

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException ex) {
            return null;
        }


    }

}
