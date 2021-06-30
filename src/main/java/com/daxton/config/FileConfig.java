package com.daxton.config;

import com.daxton.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FileConfig {

    static FileConfiguration config;

    static FileConfiguration languageConfig;

    public static boolean create(){
        File file = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor");
        if(!file.exists()){
            file.mkdir();
        }

        try {
            List<String> ll = FileSearch.readZipFile(Objects.requireNonNull(Main.main.getClass().getResource("")).getPath().replace("!/com/daxton/","").replace("file:/",""));
            ll.forEach(s -> {
                if(s.endsWith(".yml")){
                    saveResource(s, "./CustomDisplay-Editor/" + s.replace("resource/settings/", ""), false);
                }
            });
        }catch (Exception exception){
            exception.printStackTrace();
        }

        File configFile = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        String languageString = config.getString("Language");
        File languageFile = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/Language/"+languageString+".yml");
        languageConfig = YamlConfiguration.loadConfiguration(languageFile);

        FileControl.loadSettings();

        return config != null && languageConfig != null;

    }

    //語言設定
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
                case "portuguese_br":
                    locale = new Locale("pt", "BR");
                    break;
                case "korean_kr":
                    locale = new Locale("ko", "");
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

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getLanguageConfig() {
        return languageConfig;
    }

    //複製設定檔
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
