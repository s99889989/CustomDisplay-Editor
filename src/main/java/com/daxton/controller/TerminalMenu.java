package com.daxton.controller;

import com.daxton.Main;
import com.daxton.config.FileControl;
import com.daxton.function.LoadConfig;
import com.daxton.page.SkillMenuPage;
import com.daxton.page.TerminalMenuPage;
import com.daxton.page.topmenu.SettingsPage;
import com.daxton.terminal.CmdMain;
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
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;

public class TerminalMenu {

    @FXML public TextArea message;
    @FXML public TextField inputText;
    @FXML public Text state;

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
    //關閉
    public void stop(){
        CmdMain.stopServer();
    }
    public void restart(){
        CmdMain.restartServer();
    }

    public void forcedEnd(){
        CmdMain.forcedEndServer();
    }

    public void cdreload(){
        CmdMain.commandServer("customdisplay reload");
        TerminalMenuPage.print(System.getProperty("user.dir")+"/CustomDisplay-Editor/buttom.mp3");
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

    //
    public void openServerMenu(){ }
    //
    public void openClassMenu(){ }
    //打開技能編輯介面
    public void openSkillMenu(){ SkillMenuPage.display(); }
    //
    public void openActionMenu(){ }
    //
    public void opCharacterMenu(){ }
    //
    public void opGUIMenu(){ }
    //
    public void openItemsMenu(){ }
    //
    public void openTextureMenu(){ }
}
