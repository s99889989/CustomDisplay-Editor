package com.daxton.controller.main;

import com.daxton.Main;
import com.daxton.config.FileControl;
import com.daxton.page.*;
import com.daxton.page.main.*;
import com.daxton.page.topmenu.SettingsPage;
import com.daxton.terminal.CmdMain;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.io.File;

public class ServerMenu {

    @FXML public TextArea message;
    @FXML public TextField inputText;
    @FXML public Text state;

    //打開設定檔
    public void open() {
        if(TopMenu.openCDSetting()){

        }
    }

    //打開設定介面
    public void openSettings(){
        SettingsPage.display();
    }

    //存檔
    public void saveFile(){
        FileControl.save();
    }

    //關閉編輯器
    public void exit(){
        Main.timer.cancel();
        Main.mainWindow.close();
        CmdMain.forcedEndServer();
    }

    //打開伺服器介面
    public void openServerMenu(){ ServerMenuPage.display(); }
    //打開職業編輯介面
    public void openClassMenu(){ ClassMenuPage.display(); }
    //打開技能編輯介面
    public void openSkillMenu(){ SkillMenuPage.display(); }
    //打開動作編輯介面
    public void openActionMenu(){ ActionMenuPage.display(); }
    //打開字符編輯介面
    public void opCharacterMenu(){ CharacterMenuPage.display(); }
    //打開GUI編輯介面
    public void opGUIMenu(){ GUIMenuPage.display(); }
    //打開物品編輯介面
    public void openItemsMenu(){ ItemsMenuPage.display(); }
    //打開材質包編輯介面
    public void openTextureMenu(){ TextureMenuPage.display(); }
    //打開怪物編輯介面
    public void openModsMenu(){ MobsMenuPage.display(); }

    /**=============================================================================**/

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

//        ServerMenuPage.print(System.getProperty("user.dir")+"/CustomDisplay-Editor/buttom.mp3");
//        Media sound=new Media(new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/swish1.mp3").toURI().toString());
//        MediaPlayer mediaPlayer=new MediaPlayer(sound);
//        mediaPlayer.play();


//        File f = new File(Main.resourcePath+"/swish1.mp3");
//        Media _media = new Media(f.toURI().toString());
//        //        必須有這一行，並且要在MediaPlayer創建之前
//
//        MediaPlayer _mediaPlayer = new MediaPlayer(_media);
//        _mediaPlayer.play();

    }

    public void onEnter(KeyEvent keyEvent){
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            ServerMenuPage.enter();
        }
    }





}
