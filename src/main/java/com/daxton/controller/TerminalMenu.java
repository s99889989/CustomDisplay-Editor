package com.daxton.controller;

import com.daxton.Main;
import com.daxton.config.FileControl;
import com.daxton.function.LoadConfig;
import com.daxton.page.TerminalMenuPage;
import com.daxton.page.topmenu.SettingsPage;
import com.daxton.terminal.CmdMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;

public class TerminalMenu {

    @FXML public TextArea message;

    //打開設定檔
    public void open() {

        try {
            DirectoryChooser dc = new DirectoryChooser();
            dc.setTitle("選擇目錄");
            File desktop = new File(System.getProperty("user.home") + File.separator + "Desktop");
            //if (desktop.isDirectory())
            dc.setInitialDirectory(desktop);
            File selectedFile = dc.showDialog(Main.mainWindows);
            if(selectedFile != null){

                LoadConfig.load2(selectedFile);

            }

        } catch (Exception e) {
            //ExceptionController.display(e);
        }
    }

    public void start(){
        CmdMain.runrun2();

//        ByteArrayOutputStream baoStream = new ByteArrayOutputStream(1024);
//        PrintStream cacheStream = new PrintStream(baoStream);
//        PrintStream oldStream = System.out;
//        System.setOut(cacheStream);
//        System.out.println("System.out.println:test");
//        String strMsg = baoStream.toString();
//        System.setOut(oldStream);
//        TerminalMenuPage.print(strMsg);
        //System.out.println(strMsg);
    }

    public void setMessage(TextArea message) {
        this.message = message;
    }

    public void openSettings(){
        SettingsPage.display();
    }

    //存檔
    public void saveFile(){
        FileControl.save();
    }

    //打開技能編輯介面
    public void openSkillMenu(){

        URL location = Main.main.getClass().getResource("");

        try {
            //URL newURL = new URL(location.toString().replace("/com/daxton","")+"resource/page/SkillMenu.fxml");
            URL newURL;
            if (location != null) {
                newURL = new URL(location.toString().replace("/classes/java/main/com/daxton","")+"resources/main/resource/page/SkillMenu.fxml");
                Parent root = FXMLLoader.load(newURL);

                Main.mainWindows.setTitle("CustomDisplay-編輯器");
                Scene scene = new Scene(root, 1280, 800);
                scene.getStylesheets().add("resource/style.css");
                Main.mainWindows.setScene(scene);
                Main.mainWindows.show();
            }


        }catch (Exception exception){

        }

    }

}
