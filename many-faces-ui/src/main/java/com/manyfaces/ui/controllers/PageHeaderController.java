/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXToggleNode;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class PageHeaderController {

    private static final Logger LOG;
    @FXML
    private Text headerText;
    @FXML
    private JFXToggleNode notificationToggle;
    @FXML
    private FontIcon icon;

    static {
        LOG = Logger.getLogger(PageHeaderController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
    }
    
    public void setHeaderText(String text){
        headerText.setText(text);
    }

}
