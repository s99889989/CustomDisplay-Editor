package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.SkillMenu;
import com.daxton.controller.skillmenu.SkillActionEdit;
import com.daxton.function.Manager;
import javafx.scene.control.ListView;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;
import java.util.Objects;

public class SkillMenuPage {

    //打開技能編輯介面
    public static void display(){
        SkillMenu skillMenu = FxmlLoader.display("/page/main/SkillMenu.fxml", Main.mainWindow);
        Manager.controller_Map.put("SkillMenu", skillMenu);
        Main.mainMenuName = "SkillMenu";
        setSkillListList();

    }

    //打開動作編輯介面
    public static void editAction(){
        SkillMenu skillMenu = (SkillMenu) Manager.controller_Map.get("SkillMenu");
        if(skillMenu != null){
            String selectAction = skillMenu.actionList.getSelectionModel().getSelectedItem();
            if(selectAction != null && !selectAction.isEmpty()){

                SkillActionEdit skillActionEdit = FxmlLoader.display("/page/skillmenu/SkillActionEdit.fxml", Main.secondaryWindow);

                if(skillActionEdit != null){

                    Map<String ,String> stringStringMap = FileSearch.setClassAction(selectAction);

                    skillActionEdit.actionSelect.setText(StringConversion.getActionKey(stringStringMap, new String[]{"a", "action"}));

                    skillActionEdit.targetSelect.setText(StringConversion.getActionKey(stringStringMap, new String[]{"targetkey"}));

                    skillActionEdit.triggerSelect.setText(StringConversion.getActionKey(stringStringMap, new String[]{"triggerkey"}));

                    FileSearch.getTypeFileKey("Actions/").forEach(s -> skillActionEdit.actionTypeList.getItems().add(s));

                    Objects.requireNonNull(Main.languageConfig.getConfigurationSection("Aims.EntityTargeters")).getKeys(false).forEach(s -> skillActionEdit.targetList.getItems().add("@"+s));

                    Objects.requireNonNull(Manager.file_Config_Map.get("Other/TargetFilters.yml").getConfigurationSection("")).getKeys(false).forEach(s -> skillActionEdit.targetFiltersList.getItems().add(s));


                    if(skillMenu.passiveSkill.isSelected()){
                        Objects.requireNonNull(Main.languageConfig.getConfigurationSection("Trigger")).getKeys(false).forEach(s -> skillActionEdit.triggerList.getItems().add(s));
                    }


                }

            }

        }

    }

    //設置技能列表
    public static void setSkillListList(){
        SkillMenu skillMenu = (SkillMenu) Manager.controller_Map.get("SkillMenu");
        if(skillMenu != null){
            FileSearch.getTypeFileName("Class/Skill/").forEach(s -> skillMenu.skillListList.getItems().add(s.replace(".yml","")));
        }
    }
    //設計技能列表內技能
    public static void setSkillList(ListView<String> skillListList, ListView<String> skillList){
        String selectSkillList = skillListList.getSelectionModel().getSelectedItem();
        FileConfiguration fileConfiguration = Manager.file_Config_Map.get("Class/Skill/"+selectSkillList+".yml");
        skillList.getItems().clear();
        Objects.requireNonNull(fileConfiguration.getConfigurationSection("Skills")).getKeys(false).forEach(s -> skillList.getItems().add(s));
    }

}
