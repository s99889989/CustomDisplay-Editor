package com.daxton.controller.main;

import com.daxton.config.FileControl;
import com.daxton.Main;
import com.daxton.page.*;
import com.daxton.page.classmenu.*;
import com.daxton.page.main.*;
import com.daxton.page.topmenu.SettingsPage;
import com.daxton.terminal.CmdMain;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.bukkit.configuration.file.FileConfiguration;


public class ClassMenu {

    @FXML public ChoiceBox<String> classList;

    @FXML public TextField className;

    @FXML public ListView<String> actionList;

    @FXML public ListView<String> levelList;

    @FXML public ListView<String> pointList;

    @FXML public ListView<String> attributesPointList;

    @FXML public ListView<String> attributesStatsList;

    @FXML public ListView<String> equipmentStatsList;

    @FXML public ListView<String> skillsList;

    //打開設定檔
    public void open() {

        if(TopMenu.openCDSetting()){
            ClassMenuPage.setClass();
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

    /**=============================================================================**/

    @FXML//初始設定
    void initialize() {

    }

    //新增職業
    public void classAdd(){
        ClassOption.addDisplay();
    }
    //編輯職業
    public void classEdit(){
        //ClassOption.editDisplay();
    }
    //移除職業
    public void classRemove(){
        ClassOption.removeClass();
    }
    //修改職業名稱
    public void modifyClassName(){
        String chosseClassName = classList.getValue();
        String classShowName = className.getText();
        if(!classShowName.isEmpty()){
            FileConfiguration classConfig = FileControl.setValueNoMap("/Class/Main/"+chosseClassName, chosseClassName+".Class_Name", classShowName);
            FileControl.inputMap("Class/Main/"+chosseClassName, classConfig);
            System.out.println(classShowName);
        }

    }

    //新增動作
    public void addAction(){
        ActionOption.addAction();
    }
    //編輯動作
    public void editAction(){
        ActionOption.editAction();
    }
    //移除動作
    public void removeAction(){
        ActionOption.removeAction();
    }

    //新增等級
    public void addLevel(){
        LevelOption.addDisplay();
    }
    //編輯等級
    public void editLevel(){

    }
    //移除等級
    public void removeLevel(){
        LevelOption.removeAction();
    }

    //新增點數
    public void addPoint(){
        PointOption.addDisplay();
    }
    //編輯點數
    public void editPoint(){

    }
    //移除點數
    public void removePoint(){
        PointOption.removeAction();
    }

    //屬性點數-新增
    public void addAttrPoint(){
        AttrPointOption.addDisplay();
    }
    //屬性點數-編輯
    public void editAttrPoint(){

    }
    //屬性點數-移除
    public void removeAttrPoint(){
        AttrPointOption.removeAction();
    }

    //屬性狀態-新增
    public void addAttrStatus(){
        AttrStatusOption.addDisplay();
    }
    //屬性狀態-編輯
    public void editAttrStatus(){

    }
    //屬性狀態-移除
    public void removeAttrStatus(){
        AttrStatusOption.removeAction();
    }

    //裝備狀態-新增
    public void addEqmStatus(){
        EqmStatusOption.addDisplay();
    }
    //裝備狀態-編輯
    public void editEqmStatus(){

    }
    //裝備狀態-移除
    public void removeEqmStatus(){
        EqmStatusOption.removeAction();
    }

    //技能列表-新增
    public void addSkill(){
        SkillOption.addDisplay();
    }
    //技能列表-編輯
    public void editSkill(){
        SkillMenuPage.display();
    }
    //技能列表-移除
    public void removeSkill(){
        SkillOption.removeAction();
    }


}
