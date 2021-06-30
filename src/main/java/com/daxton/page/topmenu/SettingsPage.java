package com.daxton.page.topmenu;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileConfig;
import com.daxton.controller.topmenu.Settings;
import com.daxton.function.Manager;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class SettingsPage {

    public static Stage settingsWindow;

    public static String[] languageList = new String[]{"English","Chinese_TW","Chinese_CN","Portuguese_BR","Korean_KR"};

    //打開設定介面
    public static void display(){
        Settings settings = FxmlLoader.display("/page/topmenu/Settings.fxml", Main.secondaryWindow);
        if(settings != null){
            Manager.controller_Map.put("Settings", settings);

            for(String lang : languageList){
                settings.languageList.getItems().add(lang);

            }

            settings.languageList.setValue(Main.config.getString("Language"));
            settings.serverParameters.setText(Main.config.getString("Server-Settings.Startup-Parameters"));
            settings.cdParameters.setText(Main.config.getString("CustomDisplay.Folder-Path"));
        }

    }

    //改變伺服器參數
    public static void changeCustomDisplayFolderPath(){
        Settings settings = (Settings) Manager.controller_Map.get("Settings");
        if(settings != null){

            String defaultCustomDisplayFolderPath = Main.config.getString("CustomDisplay.Folder-Path");
            if(defaultCustomDisplayFolderPath != null && !defaultCustomDisplayFolderPath.equals(settings.cdParameters.getText())){
                Main.config.set("CustomDisplay.Folder-Path", settings.cdParameters.getText());

                File configFile = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/config.yml");
                try {
                    Main.config.save(configFile);
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }

        }
    }

    //改變伺服器參數
    public static void changeServerParameters(){
        Settings settings = (Settings) Manager.controller_Map.get("Settings");
        if(settings != null){

            String defaultServerParameters = Main.config.getString("Server-Settings.Startup-Parameters");
            if(defaultServerParameters != null && !defaultServerParameters.equals(settings.serverParameters.getText())){
                Main.config.set("Server-Settings.Startup-Parameters", settings.serverParameters.getText());

                File configFile = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/config.yml");
                try {
                    Main.config.save(configFile);
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }

        }
    }

    //改變語言
    public static void changeLanguage(){
        Settings settings = (Settings) Manager.controller_Map.get("Settings");
        if(settings != null){

            String defaultLanguage = Main.config.getString("Language");
            if(defaultLanguage != null && !defaultLanguage.equals(settings.languageList.getValue())){
                settings.languageList.setValue(settings.languageList.getValue());

                Main.config.set("Language", settings.languageList.getValue());

                File file2 = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/config.yml");
                try {
                    Main.config.save(file2);
                }catch (IOException exception){
                    exception.printStackTrace();
                }

                Main.language = ResourceBundle.getBundle("resource/language/lang", FileConfig.getLocale());


                Main.mainWindow.close();
                Main.openMainPage();
            }



        }

    }
}
