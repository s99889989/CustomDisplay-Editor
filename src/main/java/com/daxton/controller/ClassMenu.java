package com.daxton.controller;

import com.daxton.config.FileControl;
import com.daxton.config.FileSearch;
import com.daxton.function.*;
import com.daxton.Main;
import com.daxton.controller.classmenu.EditClass;
import com.daxton.page.classmenu.AddActionPage;
import com.daxton.page.classmenu.AddClassPage;
import com.daxton.page.topmenu.SettingsPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.net.URL;


public class ClassMenu {

    @FXML public ChoiceBox<String> classList;

    @FXML TextField className;

    @FXML ListView<String> actionList;

    @FXML ListView<String> levelList;

    @FXML ListView<String> pointList;

    @FXML ListView<String> attributesPointList;

    @FXML ListView<String> attributesStatsList;

    @FXML ListView<String> equipmentStatsList;

    @FXML ListView<String> skillsList;

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

                classList.getItems().clear();

                FileSearch.getTypeFileName("Class/Main/").forEach(s -> classList.getItems().add(s.replace(".yml","")));

                choseClass();


            }

        } catch (Exception e) {
            //ExceptionController.display(e);
        }
    }

    public void openSettings(){
        SettingsPage.display();
    }

    //存檔
    public void saveFile(){
        FileControl.save();
    }


    //選擇職業時
    public void choseClass(){
        //動作列表
        classList.setOnAction((event) -> {
//            int selectedIndex = classList.getSelectionModel().getSelectedIndex();
//            Object selectedItem = classList.getSelectionModel().getSelectedItem();
//            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
//            System.out.println("   ChoiceBox.getValue(): " + classList.getValue());
            String chosseClassName = classList.getValue();

            FileConfiguration classConfig = Manager.file_Config_Map.get("Class/Main/"+chosseClassName+".yml");
            if(classConfig != null){

                //職業顯示名稱
                className.setText(classConfig.getString(chosseClassName+".Class_Name"));
                //動作列表
                actionList.getItems().clear();
                classConfig.getStringList(chosseClassName+".Action").forEach(s->actionList.getItems().add(s));
                //等級
                levelList.getItems().clear();
                classConfig.getStringList(chosseClassName+".Level").forEach(s->levelList.getItems().add(s));
                //點數列表
                pointList.getItems().clear();
                classConfig.getStringList(chosseClassName+".Point").forEach(s->pointList.getItems().add(s));
                //屬性點數列表
                attributesPointList.getItems().clear();
                classConfig.getStringList(chosseClassName+".Attributes_Point").forEach(s->attributesPointList.getItems().add(s));
                //屬性狀態列表
                attributesStatsList.getItems().clear();
                classConfig.getStringList(chosseClassName+".Attributes_Stats").forEach(s->attributesStatsList.getItems().add(s));
                //裝備狀態列表
                equipmentStatsList.getItems().clear();
                classConfig.getStringList(chosseClassName+".Equipment_Stats").forEach(s->equipmentStatsList.getItems().add(s));
                //技能列表
                skillsList.getItems().clear();
                classConfig.getStringList(chosseClassName+".Skills").forEach(s->skillsList.getItems().add(s));
            }


        });
    }

    public void modifyClassName(){
        String chosseClassName = classList.getValue();
        String classShowName = className.getText();
        if(!classShowName.isEmpty()){
            FileConfiguration classConfig = FileControl.setValueNoMap("/Class/Main/"+chosseClassName, chosseClassName+".Class_Name", classShowName);
            FileControl.inputMap("Class/Main/"+chosseClassName, classConfig);
            System.out.println(classShowName);
        }

    }

    //新增職業
    public void classAdd(){
//        Manager.controller_Map.put("AddClass", new AddClass());
//        AddClass addclass = (AddClass) Manager.controller_Map.get("AddClass");
//        addclass.display(classList);
        AddClassPage.display();
    }
    //編輯職業
    public void classEdit(){
        Manager.controller_Map.put("EditClass", new EditClass());
        EditClass addclass = (EditClass) Manager.controller_Map.get("EditClass");
        addclass.display(classList);
    }
    //移除職業
    public void classRemove(){
        String chosseClassName = classList.getValue();
        classList.getItems().remove(chosseClassName);

        File file = new File(Main.openPath +"/Class/Main/"+chosseClassName+".yml");
        if(file.exists()){
            if(file.delete()) {
                System.out.println("DELETE");
            }
        }

        Manager.file_Name_Map.remove("Class/Main/"+chosseClassName+".yml");
        Manager.file_Config_Map.remove("Class/Main/"+chosseClassName+".yml");

    }

    //動作

    public void addAction(){
//        Manager.controller_Map.put("AddAction", new AddAction());
//        AddAction addclass = (AddAction) Manager.controller_Map.get("AddAction");
//        addclass.display();
        AddActionPage.display();
        //addclass.addList();
    }

    public void editAction(){
        AlertBos.display();
    }

    public void removeAction(){

    }

    //等級

    public void addLevel(){

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
