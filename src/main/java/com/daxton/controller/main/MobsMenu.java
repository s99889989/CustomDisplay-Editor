package com.daxton.controller.main;

import com.daxton.Main;
import com.daxton.config.FileControl;
import com.daxton.page.*;
import com.daxton.page.main.*;
import com.daxton.page.topmenu.SettingsPage;
import com.daxton.terminal.CmdMain;

public class MobsMenu {

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
    //打開職業介面
    public void openClassMenu(){ ClassMenuPage.display(); }
    //打開技能編輯介面
    public void openSkillMenu(){ SkillMenuPage.display(); }
    //打開動作介面
    public void openActionMenu(){ ActionMenuPage.display(); }
    //打開字符介面
    public void opCharacterMenu(){ CharacterMenuPage.display(); }
    //打開GUI介面
    public void opGUIMenu(){ GUIMenuPage.display(); }
    //打開物品介面
    public void openItemsMenu(){ ItemsMenuPage.display(); }
    //打開材質包介面
    public void openTextureMenu(){ TextureMenuPage.display(); }
    //打開怪物介面
    public void openModsMenu(){ MobsMenuPage.display(); }

    //**=============================================================================**/

}
