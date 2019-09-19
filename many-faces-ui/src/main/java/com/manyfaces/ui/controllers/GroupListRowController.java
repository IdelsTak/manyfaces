/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import javafx.css.Styleable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.HBox;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class GroupListRowController {

    @FXML
    private TitledPane titledPane;
    @FXML
    private HBox titleHbox;
    @FXML
    private Label groupNameLabel;
    @FXML
    private Label noOfProfilesLabel;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        titledPane.addEventFilter(InputEvent.ANY, e -> {
            //If the target of the event is the title, ignore it
            //we want to be able to expand the titled pane using 
            //our custom button only
            if (((Styleable) e.getTarget())
                    .getStyleClass()
                    .contains("title")) {
                e.consume();
            }
        });
        titleHbox.prefWidthProperty().bind(titledPane.widthProperty());
    }

    void setGroupName(String groupName) {
        groupNameLabel.setText(groupName);
    }

}
