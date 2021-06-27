package com.daxton;

import com.daxton.config.FileConfig;
import com.daxton.controller.TerminalMenu;
import com.daxton.function.Task;
import com.daxton.page.ClassMenuPage;
import com.daxton.page.TerminalMenuPage;
import javafx.application.Application;
import javafx.stage.Stage;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class Main extends Application {

    public static Main main;

    public static Stage mainWindows;

    public static String openPath;

    public static String resourcePath;

    public static ResourceBundle language;

    public static FileConfiguration config;

    public static Timer timer = new Timer();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        main = this;
        mainWindows = primaryStage;
        System.out.println();
        config = FileConfig.create();

        URL location = Main.main.getClass().getResource("");
        String compilePath = location.toString().replace("/com/daxton","")+"resource";
        String testPath = location.toString().replace("/classes/java/main/com/daxton","")+"resources/main/resource";
        resourcePath = compilePath;

        //語言
        language = ResourceBundle.getBundle("resource/language/lang", FileConfig.getLocale());
        timer.schedule(new Task(), 0,5 * 1000);
        //ClassMenuPage.display();
        TerminalMenuPage.display();

        //System.setOut(new PrintStream(new FileOutputStream("log.txt",true)));
    }




}
