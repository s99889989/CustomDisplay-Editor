package com.daxton.page.topmenu;

import com.daxton.Main;
import com.daxton.config.FileControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.ClassMenu;
import com.daxton.controller.topmenu.Settings;
import com.daxton.function.Manager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SettingsPage {

    public static Stage addActionWindow;

    public static String[] languageList = new String[]{"English","Chinese_TW","Chinese_CN","Portuguese_BR","Korean_KR"};

    //打開設定介面
    public static void display(){

        addActionWindow = new Stage();

        try {
            URL newURL = new URL(Main.resourcePath+"/page/topmenu/Settings.fxml");

            ResourceBundle language = Main.language;

            FXMLLoader loader = new FXMLLoader(newURL, language);

            Parent root = loader.load();


            Scene scene = new Scene(root);
            scene.getStylesheets().add("resource/style.css");
            addActionWindow.getIcons().add(new Image(Main.resourcePath+"/icon.png"));
            addActionWindow.setScene(scene);
            addActionWindow.show();

            Settings settings = loader.getController();
            Manager.controller_Map.put("Settings", settings);

            String path = Main.main.getClass().getResource("").getPath();

            for(String lang : languageList){
                settings.languageList.getItems().add(lang);

            }


            settings.languageList.setValue( Main.config.getString("Language"));


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

            Main.config.set("Language", settings.languageList.getValue());
            File file2 = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/config.yml");
            try {
                Main.config.save(file2);
            }catch (IOException exception){

            }

        }

    }
}
