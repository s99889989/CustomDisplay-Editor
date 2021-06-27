package com.daxton.controller.topmenu;

import com.daxton.page.topmenu.SettingsPage;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Settings {

    @FXML public ChoiceBox<String> languageList;
    @FXML public TextField serverParameters;

    public void define(){

        SettingsPage.addKEY();

        //SettingsPage.addActionWindow.close();

    }

    public void cancel(){
        SettingsPage.settingsWindow.close();
    }


}
