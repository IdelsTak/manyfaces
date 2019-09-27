/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileWebRtcController {

    @FXML
    private RadioButton alteredToggle;
    @FXML
    private ToggleGroup behaviorGroup;
    @FXML
    private RadioButton disabledToggle;
    @FXML
    private RadioButton realToggle;
    @FXML
    private HBox realAlert;
    @FXML
    private HBox disabledAlert;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        disabledAlert.visibleProperty().bind(disabledToggle.selectedProperty());
        realAlert.visibleProperty().bind(realToggle.selectedProperty());
    }

}
