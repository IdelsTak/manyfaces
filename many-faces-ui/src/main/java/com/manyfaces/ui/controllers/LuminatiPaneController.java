/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class LuminatiPaneController {

    private static final Logger LOG;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton activateButton;

    static {
        LOG = Logger.getLogger(LuminatiPaneController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
    }

}
