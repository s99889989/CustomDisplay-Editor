package com.daxton.controller.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    public VBox change, options;

    @FXML
    void initialize() {
        Button btnOptions = (Button) options.lookup("#btnOptions");
        btnOptions.setOnAction(this::goToOptions);
        Button btnChange = (Button) change.lookup("#btnChange");
        btnChange.setOnAction(this::goBack);
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        change.setVisible(false);
        change.setManaged(false);
        options.setVisible(true);
        options.setManaged(true);
    }
    @FXML
    public void goToOptions(ActionEvent actionEvent) {
        change.setVisible(true);
        change.setManaged(true);
        options.setVisible(false);
        options.setManaged(false);
    }

}
