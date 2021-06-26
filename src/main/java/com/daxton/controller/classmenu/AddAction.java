package com.daxton.controller.classmenu;

import com.daxton.config.FileSearch;
import com.daxton.page.classmenu.AddActionPage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class AddAction {

    @FXML public ListView<String> actionList;

    public void addList(){
        FileSearch.getTypeFileName("Actions/").forEach(s -> actionList.getItems().add(s.replace(".yml", "")));
        actionList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void define(){
        actionList.getSelectionModel().getSelectedItems().forEach(s -> System.out.println(s));
    }

    public void cancel(){
        AddActionPage.addActionWindow.close();
    }

}
