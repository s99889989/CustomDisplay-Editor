package com.daxton;

import com.daxton.api.Discord;
import com.daxton.api.StringConversion;
import com.daxton.config.FileConfig;
import com.daxton.config.FileControl;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.CharacterMenu;
import com.daxton.function.Task;
import com.daxton.page.main.*;
import com.daxton.page.test.ControllerPage;
import com.daxton.terminal.CmdMain;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;

public class Main extends Application {

    public static Main main;

    public static Stage mainWindow;

    public static Stage secondaryWindow;

    public static String mainMenuName = "ServerMenu";

    private static String openPath;

    public static String resourcePath;

    public static ResourceBundle language;

    public static FileConfiguration config;

    public static FileConfiguration languageConfig;
    public static FileConfiguration placeholderAPIConfig;
    public static FileConfiguration characterConfig;

    public static Timer timer = new Timer();

    public static Discord discord;

    public static void main(String[] args) {
        launch(args);
//        String message = "content[<cd_self_base_attack_number>]";
//        Map<String ,String> getCharacterMap = FileSearch.getCharacterMap(message);
//        System.out.println("TEST RUN");
//        String charType = StringConversion.getActionKey(getCharacterMap, new String[]{"charactertype"});
//        String charFunction = StringConversion.getActionKey(getCharacterMap, new String[]{"Function","fc"});
//        String charMessage = StringConversion.getActionKey(getCharacterMap, new String[]{"message","m"});
//        System.out.println("P: "+charType+" : "+charFunction+" : "+charMessage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        main = this;
        mainWindow = primaryStage;

        FileConfig.create();
        config = FileConfig.getConfig();
        languageConfig = FileConfig.getLanguageConfig();

        //讀取插件預設位置設定檔
        File cdFilePath = new File(Objects.requireNonNull(config.getString("CustomDisplay.Folder-Path")));
        FileControl.loadCustomDisplaySettings(cdFilePath);

        URL location = Main.main.getClass().getResource("");
        String compilePath = Objects.requireNonNull(location).toString().replace("/com/daxton","")+"resource";
        String testPath = location.toString().replace("/classes/java/main/com/daxton","")+"resources/main/resource";
        resourcePath = compilePath;

        //語言

        language = ResourceBundle.getBundle("resource/language/lang", FileConfig.getLocale());
        timer.schedule(new Task(), 0,5 * 1000);

        setDefaultMainWindow();
        setSecondaryWindow();

        //CharacterMenuPage.display();
        //ClassMenuPage.display();
        //ServerMenuPage.display();
        ActionMenuPage.display();
        //SkillMenuPage.display();
        //ControllerPage.display();

        //System.setOut(new PrintStream(new FileOutputStream("log.txt",true)));
        //Thread thread = new Thread(this::setDiscord);
        //thread.start();

    }

    public void setDiscord(){
        //機器人設定
        boolean dcenable = config.getBoolean("Backups.Discord.enable");
        String token = config.getString("Backups.Discord.token");
        long channelID = config.getLong("Backups.Discord.channelID");

        if(dcenable){
            discord = new Discord(token,channelID,true);

        }
    }

    //設置主頁面
    public void setDefaultMainWindow(){

        String title = languageConfig.getString("Title");
        if(title != null){
            mainWindow.setTitle(title);
        }else {
            mainWindow.setTitle("CustomDisplay-Editor");
        }
        mainWindow.getIcons().add(new Image(Main.resourcePath+"/icon.png"));
        mainWindow.setOnCloseRequest(event -> {
            secondaryWindow.close();
            if(timer != null){
                try {
                    timer.cancel();
                }catch (Exception exception){

                }
                CmdMain.forcedEndServer();
            }
        });

    }

    public void setSecondaryWindow(){
        secondaryWindow = new Stage();
        secondaryWindow.getIcons().add(new Image(Main.resourcePath+"/icon.png"));
        secondaryWindow.setAlwaysOnTop(true);

//        secondaryWindow.setOnCloseRequest(event -> {
//
//        });
    }

    //打開目前頁面
    public static void openMainPage(){
        switch (mainMenuName){
            case "ActionMenu":
                ActionMenuPage.display();
                break;
            case "CharacterMenu":
                CharacterMenuPage.display();
                break;
            case "ClassMenu":
                ClassMenuPage.display();
                break;
            case "GUIMenu":
                GUIMenuPage.display();
                break;
            case "ItemsMenu":
                ItemsMenuPage.display();
                break;
            case "MobsMenu":
                MobsMenuPage.display();
                break;
            case "ServerMenu":
                ServerMenuPage.display();
                break;
            case "SkillMenu":
                SkillMenuPage.display();
                break;
            case "TextureMenu":
                TextureMenuPage.display();
                break;
        }
    }

    public static void setOpenPath(String openPath) {
        Main.openPath = openPath;
    }

    public static String getOpenPath() {
        return openPath;
    }
}
