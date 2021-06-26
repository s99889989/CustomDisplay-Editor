package com.daxton.controller.topmenu;

import com.daxton.function.Manager;
import com.daxton.page.topmenu.SettingsPage;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class Settings {

    @FXML public ChoiceBox<String> languageList;

    public void define(){

        SettingsPage.addKEY();

        //SettingsPage.addActionWindow.close();

    }

    public void cancel(){
        SettingsPage.addActionWindow.close();
    }


}
