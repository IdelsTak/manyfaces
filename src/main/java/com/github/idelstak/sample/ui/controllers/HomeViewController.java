/*
 Copyright 2019.
 */
package com.github.idelstak.sample.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleNode;
import java.util.logging.Logger;
import javafx.css.Styleable;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class HomeViewController {

    private static final Logger LOG;
    @FXML
    private JFXButton notificationsButton;
    @FXML
    private TitledPane operationsTitledPane;
    @FXML
    private JFXToggleNode operationsButton;
    @FXML
    private JFXButton refreshButton;
    @FXML
    private FontIcon operationsIcon;
    @FXML
    private FontIcon refreshIcon;
    @FXML
    private JFXCheckBox selectRadioButton;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton moveToGroupButton;
    @FXML
    private JFXButton removeFromGroupButton;

    static {
        LOG = Logger.getLogger(HomeViewController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    void initialize() {
//        LOG.log(Level.INFO, "toggle node style class: {0}", operationsButton.getStyleClass());
//        LOG.log(Level.INFO, "icon style class: {0}", operationsIcon.getStyleClass());

        operationsTitledPane.addEventFilter(InputEvent.ANY, e -> {
            //If the target of the event is the
            //title, ignore it -- we want to be able
            //to expand the titled pane using our custom button
            //and nowhere else
            if (((Styleable) e.getTarget())
                    .getStyleClass()
                    .contains("title")) {
                e.consume();
            }
        });

        selectRadioButton.selectedProperty().addListener((o, oldVal, selected) -> {
            String value = selected ? "Deselect all" : "Select all";
            selectRadioButton.setText(value);
            deleteButton.setDisable(!selected);
            moveToGroupButton.setDisable(!selected);
            removeFromGroupButton.setDisable(!selected);
        });
    }

    @FXML
    void displayOperations() {
        operationsTitledPane.setExpanded(!operationsTitledPane.isExpanded());
    }

    @FXML
    void refreshView() {
        //TODO
    }

    @FXML
    void showOperationsPane() {
        boolean expand = !operationsTitledPane.isExpanded()
                && operationsButton.isSelected();
        
        operationsTitledPane.setExpanded(expand);

        if (expand) {
            operationsIcon.getStyleClass().add("action-icon-focused-color");
        } else {
            operationsIcon.getStyleClass().remove("action-icon-focused-color");
        }
    }

    @FXML
    void addFocusedIconColor(MouseEvent event) {
        if (event.getSource().equals(refreshButton)) {
            refreshIcon.getStyleClass().add("action-icon-focused-color");
        }
    }

    @FXML
    void addUnfocusedIconColor(MouseEvent event) {
        if (event.getSource().equals(refreshButton)) {
            refreshIcon.getStyleClass().remove("action-icon-focused-color");
        }
    }

    @FXML
    void operationsTitledPaneClicked(MouseEvent e) {
//        LOG.log(Level.INFO, "Titled pane clicked: {0}", event);
    }

}
