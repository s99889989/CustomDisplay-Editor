package com.daxton.controller;

import com.daxton.Main;
import com.daxton.config.FileControl;
import com.daxton.function.LoadConfig;
import com.daxton.page.TerminalMenuPage;
import com.daxton.page.topmenu.SettingsPage;
import com.daxton.terminal.CmdMain;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;

public class TerminalMenu {

    final JFXPanel fxPanel = new JFXPanel();

    @FXML public TextArea message;
    @FXML public TextField inputText;

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

//        CallShell call = new CallShell();
//        call.callScript("test.sh", "4", "/");

        //CmdMain.runrun3();
        CmdMain.startServer();

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

    public void stop(){
        CmdMain.stopServer();
    }
    public void restart(){
        CmdMain.restopRun();
    }

    public void forcedEnd(){
        CmdMain.forcedEnd();
    }

    public void cdreload(){
        CmdMain.command("customdisplay reload");
        TerminalMenuPage.print(System.getProperty("user.dir")+"/CustomDisplay-Editor/swish1.mp3");
        Media sound=new Media(new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/swish1.mp3").toURI().toString());
        MediaPlayer mediaPlayer=new MediaPlayer(sound);
        mediaPlayer.play();


//        File f = new File(Main.resourcePath+"/swish1.mp3");
//        Media _media = new Media(f.toURI().toString());
//        //        必須有這一行，並且要在MediaPlayer創建之前
//
//        MediaPlayer _mediaPlayer = new MediaPlayer(_media);
//        _mediaPlayer.play();

    }

    public void onEnter(KeyEvent keyEvent){
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            TerminalMenuPage.enter();
        }
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
