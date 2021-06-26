package com.daxton.controller;

import com.daxton.Main;
import com.daxton.page.SkillMenuPage;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class SkillMenu {

    public void open() {

        try {
            DirectoryChooser dc = new DirectoryChooser();
            dc.setTitle("選擇目錄");
            File desktop = new File(System.getProperty("user.home") + File.separator + "Desktop");
            //if (desktop.isDirectory())
            dc.setInitialDirectory(desktop);
            File selectedFile = dc.showDialog(Main.mainWindows);
            if(selectedFile != null){


            }

        } catch (Exception e) {
            //ExceptionController.display(e);
        }
    }

    public void openClassMenu(){

        SkillMenuPage.display();

    }

}
