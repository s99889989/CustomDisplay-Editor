package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ClassMenu;
import com.daxton.function.Manager;
import org.bukkit.configuration.file.FileConfiguration;

public class ClassMenuPage {

    //打開職業編輯介面
    public static void display(){
        ClassMenu classMenu = FxmlLoader.display("/page/main/ClassMenu.fxml", Main.mainWindow);
        Manager.controller_Map.put("ClassMenu", classMenu);
        Main.mainMenuName = "ClassMenu";

        setClass();


    }

    //設置職業設定
    public static void setClass(){
        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            classMenu.classList.getItems().clear();

            FileSearch.getTypeFileName("Class/Main/").forEach(s -> classMenu.classList.getItems().add(s.replace(".yml","")));

            //動作列表
            classMenu.classList.setOnAction((event) -> {

                String chosseClassName = classMenu.classList.getValue();

                FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+chosseClassName+".yml");
                if(classConfig != null){

                    //職業顯示名稱
                    classMenu.className.setText(classConfig.getString(chosseClassName+".Class_Name"));
                    //動作列表
                    classMenu.actionList.getItems().clear();
                    classConfig.getStringList(chosseClassName+".Action").forEach(s->classMenu.actionList.getItems().add(s));
                    //classMenu.actionList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    //等級列表
                    classMenu.levelList.getItems().clear();
                    classConfig.getStringList(chosseClassName+".Level").forEach(s->classMenu.levelList.getItems().add(s));
                    //classMenu.levelList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    //點數列表
                    classMenu.pointList.getItems().clear();
                    classConfig.getStringList(chosseClassName+".Point").forEach(s->classMenu.pointList.getItems().add(s));
                    //classMenu.pointList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    //屬性點數列表
                    classMenu.attributesPointList.getItems().clear();
                    classConfig.getStringList(chosseClassName+".Attributes_Point").forEach(s->classMenu.attributesPointList.getItems().add(s));
                    //classMenu.attributesPointList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    //屬性狀態列表
                    classMenu.attributesStatsList.getItems().clear();
                    classConfig.getStringList(chosseClassName+".Attributes_Stats").forEach(s->classMenu.attributesStatsList.getItems().add(s));
                    //classMenu.attributesStatsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    //裝備狀態列表
                    classMenu.equipmentStatsList.getItems().clear();
                    classConfig.getStringList(chosseClassName+".Equipment_Stats").forEach(s->classMenu.equipmentStatsList.getItems().add(s));
                    //classMenu.equipmentStatsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    //技能列表
                    classMenu.skillsList.getItems().clear();
                    classConfig.getStringList(chosseClassName+".Skills").forEach(s->classMenu.skillsList.getItems().add(s));
                    //classMenu.skillsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }


            });
            classMenu.classList.getSelectionModel().select("Default_Player");
        }

    }

    //新增職業
    public static void addClassList(String className){

        ClassMenu classMenu = (ClassMenu) Manager.controller_Map.get("ClassMenu");
        if(classMenu != null){
            classMenu.classList.getItems().add(className);
        }


    }

}
