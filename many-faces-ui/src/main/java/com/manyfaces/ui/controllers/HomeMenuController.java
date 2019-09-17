/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.manyfaces.ui.BrowserProfileList;
import com.manyfaces.ui.Home;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.css.Styleable;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class HomeMenuController {

    private static final Logger LOG;
    @FXML
    private ToggleGroup menuGroup;
    @FXML
    private RadioButton homeToggle;
    @FXML
    private RadioButton newProfileToggle;
    @FXML
    private RadioButton accountToggle;
    @FXML
    private RadioButton pluginsToggle;
    @FXML
    private RadioButton helpToggle;
    private Home home;

    static {
        LOG = Logger.getLogger(HomeMenuController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        menuGroup.getToggles().forEach(this::removeRadioButtonStyling);
    }

    public void setHome(Home home) {
        this.home = home;

        homeToggle.selectedProperty().addListener((o, oldVal, selected) -> {
            if (selected) {
                openBrowserProfileList();
            }
        });

        openBrowserProfileList();
        homeToggle.setSelected(true);
    }

    private void openBrowserProfileList() {
        Platform.runLater(() -> {
            Pane contentPane = home.getContentPane();

            contentPane.getChildren().setAll(new BrowserProfileList().loadPane());
        });
    }

    private void removeRadioButtonStyling(Toggle toggle) {
        ((Styleable) toggle).getStyleClass().remove("radio-button");
    }

}
