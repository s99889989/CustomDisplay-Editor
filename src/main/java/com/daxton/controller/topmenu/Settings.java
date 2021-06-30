package com.daxton.controller.topmenu;

import com.daxton.Main;
import com.daxton.page.topmenu.SettingsPage;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Settings {

    @FXML public ChoiceBox<String> languageList;
    @FXML public TextField serverParameters;
    @FXML public TextField cdParameters;

    public void define(){

        SettingsPage.changeLanguage();
        SettingsPage.changeCustomDisplayFolderPath();
        SettingsPage.changeServerParameters();

        Main.secondaryWindow.close();


    }

    public void cancel(){
        Main.secondaryWindow.close();
    }


}
