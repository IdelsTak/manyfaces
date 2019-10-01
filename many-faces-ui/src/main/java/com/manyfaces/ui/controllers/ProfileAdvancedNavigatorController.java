/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileAdvancedNavigatorController {

    @FXML
    private JFXComboBox<?> resolutionComboBox;
    @FXML
    private Hyperlink setResolutionHyperlink;
    @FXML
    private Hyperlink editLanguagesHyperlink;
    @FXML
    private JFXComboBox<?> trackComboBox;
    @FXML
    private JFXComboBox<?> concurrencyComboBox;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
    }

}
