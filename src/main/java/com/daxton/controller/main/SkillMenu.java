package com.daxton.controller.main;

import com.daxton.Main;
import com.daxton.config.FileControl;
import com.daxton.function.Manager;
import com.daxton.api.StringConversion;
import com.daxton.page.*;
import com.daxton.page.main.*;
import com.daxton.page.topmenu.SettingsPage;
import com.daxton.terminal.CmdMain;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SkillMenu {

    @FXML public ListView<String> skillListList;
    @FXML public TextField addSkillListName;

    @FXML public ListView<String> skillList;
    @FXML public TextField addSkillName;

    @FXML public TextField barName;
    @FXML public TextField material;
    @FXML public TextField customModelData;
    @FXML public TextField name;
    @FXML public TextField mana;
    @FXML public CheckBox needTarget;
    @FXML public CheckBox passiveSkill;
    @FXML public TextField targetDistance;
    @FXML public TextField coolDown;
    @FXML public TextField castTime;
    @FXML public TextField castDelay;
    @FXML public ListView<String> loreList;
    @FXML public ListView<String> actionList;

    @FXML public TextField loreText;

    public static List<String> loreList2 = new ArrayList<>();
    public static List<String> actionList2 = new ArrayList<>();

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

    /**=============================================================================**/

    //選擇技能列表
    public void selectSkillList(){
        SkillMenuPage.setSkillList(skillListList, skillList);
    }

    //增加技能列表
    public void addSkillList(){
        String name = addSkillListName.getText();
        if(!name.isEmpty()){
            if(!skillListList.getItems().contains(name)){
                ServerMenuPage.print(Main.getOpenPath());
                File file = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/DefaultFile/DefaultSkill.yml");
                FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(file);
                //defaultConfig.getConfigurationSection("Skills").getKeys(false).forEach(s->ServerMenuPage.print(s));
                Manager.file_Config_Map.put("Class/Skill/"+name+".yml", defaultConfig);
                skillListList.getItems().add(name);
            }

        }


    }

    //移除技能列表
    public void removeSkillList(){

        String selectSlillList = skillListList.getSelectionModel().getSelectedItem();
        skillListList.getItems().remove(selectSlillList);
        Manager.file_Config_Map.remove("Class/Skill/"+selectSlillList+".yml");
        FileControl.deleteFile("Class/Skill/"+selectSlillList+".yml");

        skillList.getItems().clear();
        barName.clear();
        material.clear();
        customModelData.clear();
        name.clear();
        needTarget.setSelected(false);
        passiveSkill.setSelected(false);
        targetDistance.clear();
        coolDown.clear();
        castTime.clear();
        castDelay.clear();
        mana.clear();
        loreList.getItems().clear();
        actionList.getItems().clear();
    }

    //選擇技能
    public void selectSkill(){
        String selectSkillList = skillListList.getSelectionModel().getSelectedItem();
        FileConfiguration skillConfig = Manager.file_Config_Map.get("Class/Skill/"+selectSkillList+".yml");
        String selectSkill = skillList.getSelectionModel().getSelectedItem();

        String barNameString = skillConfig.getString("Skills."+selectSkill+".BarName");
        barName.setText(barNameString);

        String materialString = skillConfig.getString("Skills."+selectSkill+".Material");
        material.setText(materialString);

        String customModelDataString = skillConfig.getString("Skills."+selectSkill+".CustomModelData");
        customModelData.setText(customModelDataString);

        String nameString = skillConfig.getString("Skills."+selectSkill+".Name");
        name.setText(nameString);

        boolean needTargetString = skillConfig.getBoolean("Skills."+selectSkill+".NeedTarget");
        needTarget.setSelected(needTargetString);

        boolean passiveSkillString = skillConfig.getBoolean("Skills."+selectSkill+".PassiveSkill");
        passiveSkill.setSelected(passiveSkillString);

        String targetDistanceString = skillConfig.getString("Skills."+selectSkill+".TargetDistance");
        targetDistance.setText(targetDistanceString);

        String coolDownString = skillConfig.getString("Skills."+selectSkill+".CoolDown");
        coolDown.setText(coolDownString);

        String castTimeString = skillConfig.getString("Skills."+selectSkill+".CastTime");
        castTime.setText(castTimeString);

        String castDelayString = skillConfig.getString("Skills."+selectSkill+".CastDelay");
        castDelay.setText(castDelayString);

        String manaString = skillConfig.getString("Skills."+selectSkill+".Mana");
        mana.setText(manaString);

        List<String> loreListString = skillConfig.getStringList("Skills."+selectSkill+".Lore");
        loreList.getItems().clear();
        loreListString.forEach(s -> loreList.getItems().add(s));
        loreList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        loreList2 = loreListString;

        List<String> actionListString = skillConfig.getStringList("Skills."+selectSkill+".Action");
        actionList.getItems().clear();
        actionListString.forEach(s -> actionList.getItems().add(s));
        actionList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        actionList2 = actionListString;

    }

    //確定修改技能
    public void defineModifySkill(){
        String selectSkillList = skillListList.getSelectionModel().getSelectedItem();
        FileConfiguration skillConfig = Manager.file_Config_Map.get("Class/Skill/"+selectSkillList+".yml");
        String selectSkill = skillList.getSelectionModel().getSelectedItem();
        ServerMenuPage.print(selectSkill);
        skillConfig.set("Skills."+selectSkill+".BarName", barName.getText());

        skillConfig.set("Skills."+selectSkill+".Material", material.getText());

        skillConfig.set("Skills."+selectSkill+".CustomModelData", StringConversion.getInt(0, customModelData.getText()));

        skillConfig.set("Skills."+selectSkill+".Name", name.getText());

        skillConfig.set("Skills."+selectSkill+".NeedTarget", needTarget.isSelected());

        skillConfig.set("Skills."+selectSkill+".PassiveSkill", passiveSkill.isSelected());

        if(StringConversion.isNumber(targetDistance.getText())){
            skillConfig.set("Skills."+selectSkill+".TargetDistance", StringConversion.getDoubel(0, targetDistance.getText()));
        }else {
            skillConfig.set("Skills."+selectSkill+".TargetDistance", targetDistance.getText());
        }
        if(StringConversion.isNumber(coolDown.getText())){
            skillConfig.set("Skills."+selectSkill+".CoolDown", StringConversion.getDoubel(0, coolDown.getText()));
        }else {
            skillConfig.set("Skills."+selectSkill+".CoolDown", coolDown.getText());
        }

        if(StringConversion.isNumber(castTime.getText())){
            skillConfig.set("Skills."+selectSkill+".CastTime", StringConversion.getDoubel(0, castTime.getText()));
        }else {
            skillConfig.set("Skills."+selectSkill+".CastTime", castTime.getText());
        }

        if(StringConversion.isNumber(castDelay.getText())){
            skillConfig.set("Skills."+selectSkill+".CastDelay", StringConversion.getDoubel(0, castDelay.getText()));
        }else {
            skillConfig.set("Skills."+selectSkill+".CastDelay", castDelay.getText());
        }

        if(StringConversion.isNumber(mana.getText())){
            skillConfig.set("Skills."+selectSkill+".Mana", StringConversion.getDoubel(0, mana.getText()));
        }else {
            skillConfig.set("Skills."+selectSkill+".Mana", mana.getText());
        }

        skillConfig.set("Skills."+selectSkill+".Lore", loreList2);

        skillConfig.set("Skills."+selectSkill+".Action", actionList2);

    }

    //增加技能
    public void addSkill(){
        String name = addSkillName.getText();
        String skillListName = skillListList.getSelectionModel().getSelectedItem();
        FileConfiguration skillConfig = Manager.file_Config_Map.get("Class/Skill/"+skillListName+".yml");
        if(!Objects.requireNonNull(skillConfig.getConfigurationSection("Skills")).getKeys(false).contains(name)){
            skillList.getItems().add(name);

            File file = new File(System.getProperty("user.dir")+"/CustomDisplay-Editor/DefaultFile/DefaultSkill.yml");
            FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(file);
            String barNameString = defaultConfig.getString("Skills."+"Example_1"+".BarName");
            skillConfig.set("Skills."+name+".BarName", barNameString);
            String materialString = defaultConfig.getString("Skills."+"Example_1"+".Material");
            skillConfig.set("Skills."+name+".Material", materialString);
            int customModelDataString = defaultConfig.getInt("Skills."+"Example_1"+".CustomModelData");
            skillConfig.set("Skills."+name+".CustomModelData", customModelDataString);
            String nameString = defaultConfig.getString("Skills."+"Example_1"+".Name");
            skillConfig.set("Skills."+name+".Name", nameString);
            boolean needTargetString = defaultConfig.getBoolean("Skills."+"Example_1"+".NeedTarget");
            skillConfig.set("Skills."+name+".NeedTarget", needTargetString);
            boolean passiveSkillString = defaultConfig.getBoolean("Skills."+"Example_1"+".PassiveSkill");
            skillConfig.set("Skills."+name+".PassiveSkill", passiveSkillString);
            double targetDistanceString = defaultConfig.getDouble("Skills."+"Example_1"+".TargetDistance");
            skillConfig.set("Skills."+name+".TargetDistance", targetDistanceString);
            double coolDownString = defaultConfig.getDouble("Skills."+"Example_1"+".CoolDown");
            skillConfig.set("Skills."+name+".CoolDown", coolDownString);
            double castTimeString = defaultConfig.getDouble("Skills."+"Example_1"+".CastTime");
            skillConfig.set("Skills."+name+".CastTime", castTimeString);
            double castDelayString = defaultConfig.getDouble("Skills."+"Example_1"+".CastDelay");
            skillConfig.set("Skills."+name+".CastDelay", castDelayString);
            double manaString = defaultConfig.getDouble("Skills."+"Example_1"+".Mana");
            skillConfig.set("Skills."+name+".Mana", manaString);
            List<String> loreListString = defaultConfig.getStringList("Skills."+"Example_1"+".Lore");
            skillConfig.set("Skills."+name+".Lore", loreListString);
            List<String> actionListString = defaultConfig.getStringList("Skills."+"Example_1"+".Action");
            skillConfig.set("Skills."+name+".Action", actionListString);

            Manager.file_Config_Map.put("Class/Skill/"+skillListName+".yml", skillConfig);
        }
    }
    //移除技能
    public void removeSkill(){
        String selectSkillName = skillList.getSelectionModel().getSelectedItem();
        String skillListName = skillListList.getSelectionModel().getSelectedItem();
        FileConfiguration skillConfig = Manager.file_Config_Map.get("Class/Skill/"+skillListName+".yml");
        Objects.requireNonNull(skillConfig.getConfigurationSection("Skills." + selectSkillName)).getKeys(false).forEach(s-> skillConfig.set("Skills."+selectSkillName+"."+s, null));
        skillConfig.set("Skills."+selectSkillName, null);
        skillList.getItems().remove(selectSkillName);

        barName.clear();
        material.clear();
        customModelData.clear();
        name.clear();
        needTarget.setSelected(false);
        passiveSkill.setSelected(false);
        targetDistance.clear();
        coolDown.clear();
        castTime.clear();
        castDelay.clear();
        mana.clear();
        loreList.getItems().clear();
        actionList.getItems().clear();

    }

    //選擇Lore
    public void selectLore(){

        loreText.setText(loreList.getSelectionModel().getSelectedItem());

    }
    //新增Lore
    public void addLore(){

        String skillListName = skillListList.getSelectionModel().getSelectedItem();
        FileConfiguration skillConfig = Manager.file_Config_Map.get("Class/Skill/"+skillListName+".yml");
        String selectSkill = skillList.getSelectionModel().getSelectedItem();
        int order = loreList.getItems().size();
        order++;
        loreList2.add(order+"");
        loreList.getItems().add(order+"");
        skillConfig.set("Skills."+selectSkill+".Lore", loreList2);

    }
    //編輯Lore
    public void editLore(){
        String changeString = loreText.getText();
        int selectedIndex = loreList.getSelectionModel().getSelectedIndex();
        loreList.getItems().set(selectedIndex, changeString);
        loreList2.set(selectedIndex, changeString);
    }
    //移除Lore
    public void removeLore(){
        loreList.getItems().removeAll(loreList.getSelectionModel().getSelectedItems());
    }
    //新增Action
    public void addAction(){
        actionList.getItems().add("Action[action=Novice_FirstAid]");
        actionList2.add("Action[action=Novice_FirstAid]");
    }
    //編輯Action
    public void editAction(){
        SkillMenuPage.editAction();
    }
    //移除Action
    public void removeAction(){
        actionList.getItems().remove(actionList.getSelectionModel().getSelectedItem());
    }

}
