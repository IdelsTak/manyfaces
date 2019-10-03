/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.manyfaces.spi.RootComponent;
import java.util.Objects;
import javafx.fxml.FXML;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileCancelDialogController {

    private static final Lookup LOOKUP = Lookup.getDefault();
    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXButton yesButton;
    @FXML
    private JFXButton cancelButton;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        yesButton.setOnAction(e -> LOOKUP.lookup(RootComponent.class).resetContent());
    }

    void setDialog(JFXDialog fXDialog) {
        String message = "Dialog should not be null";
        JFXDialog dialog = Objects.requireNonNull(fXDialog, message);

        closeButton.setOnAction(e -> dialog.close());
        cancelButton.setOnAction(e -> dialog.close());
    }

}
