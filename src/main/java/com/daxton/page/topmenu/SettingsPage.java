package com.daxton.page.topmenu;

import com.daxton.Main;
import com.daxton.config.FileConfig;
import com.daxton.controller.topmenu.Settings;
import com.daxton.function.Manager;
import com.daxton.page.TerminalMenuPage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsPage {

    public static Stage settingsWindow;

    public static String[] languageList = new String[]{"English","Chinese_TW","Chinese_CN","Portuguese_BR","Korean_KR"};

    //打開設定介面
    public static void display(){

        settingsWindow = new Stage();

        try {
            URL newURL = new URL(Main.resourcePath+"/page/topmenu/Settings.fxml");

            ResourceBundle language = Main.language;

            FXMLLoader loader = new FXMLLoader(newURL, language);

            Parent root = loader.load();


            Scene scene = new Scene(root);
            scene.getStylesheets().add("resource/style.css");
            settingsWindow.getIcons().add(new Image(Main.resourcePath+"/icon.png"));
            settingsWindow.setScene(scene);
            settingsWindow.show();

            Settings settings = loader.getController();
            Manager.controller_Map.put("Settings", settings);

            String path = Main.main.getClass().getResource("").getPath();

            for(String lang : languageList){
                settings.languageList.getItems().add(lang);

            }


            settings.languageList.setValue(Main.config.getString("Language"));
            settings.serverParameters.setText(Main.config.getString("Server-Settings.Startup-Parameters"));

//            try {
//                List<String> fileList = FileSearch.readZipFile(path.replace("!/com/daxton/","").replace("file:/",""));//
//                fileList.forEach(s -> settings.languageList.getItems().add(s.substring(s.indexOf("ge/lang_")+8, s.indexOf(".properties"))));
//            }catch (Exception exception){
//
//            }

            //settings.languageList.getItems().add(path.replace("!/com/daxton/","").replace("file:/",""));
            //settings.languageList.getItems().add("TEST");

        }catch (IOException exception){
            //
        }

    }

    public static void addKEY(){
        Settings settings = (Settings) Manager.controller_Map.get("Settings");
        if(settings != null){
            //System.out.println(settings.languageList.getValue());
            //settings.languageList.getItems().add("TEST");
            settings.languageList.setValue(settings.languageList.getValue());
            Main.language = ResourceBundle.getBundle("resource/language/lang", FileConfig.getLocale());
            Main.config.set("Language", settings.languageList.getValue());
            File file2 = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/config.yml");
            try {
                Main.config.save(file2);
            }catch (IOException exception){

            }
            settingsWindow.close();
            Main.mainWindows.close();
            TerminalMenuPage.display();
        }

    }
}
