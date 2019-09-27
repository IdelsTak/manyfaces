/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import javafx.fxml.FXML;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class HelpAndSupportController {

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
    }

    public void setPageHeaderController(PageHeaderController phc) {
        if (phc == null) {
            String message = "PageHeaderController should not be null";
            throw new IllegalArgumentException(message);
        }
        phc.setHeaderText("Help & Support");
    }

}
