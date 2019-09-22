/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import com.manyfaces.model.Profile;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.css.Styleable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputEvent;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileListController {

    private static final Logger LOG;
    @FXML
    private TitledPane titledPane;
    @FXML
    private JFXCheckBox selectCheckBox;
    @FXML
    private JFXButton selectButton;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton moveToGroupButton;
    @FXML
    private JFXButton removeFromGroupButton;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXToggleNode settingsToggle;
    @FXML
    private FontIcon settingsIcon;
    @FXML
    private JFXToggleNode refreshButton;
    @FXML
    private FontIcon refreshIcon;
    @FXML
    private Accordion accordion;
    private final Collection<Profile> profiles = new ArrayList<>();

    static {
        LOG = Logger.getLogger(ProfileListController.class.getName());
    }

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

        titledPane.expandedProperty().addListener((o, ov, expanded) -> {
            selectCheckBox.setSelected(!expanded);

            accordion.getPanes().clear();

            profiles.forEach(profile -> {
                ProfileTitledPane ptp = getProfileTitledPane(
                        profile.getProfileNameProperty().get());
                ptp.showSelectCheckboxes(expanded);
                accordion.getPanes().add(ptp.getTitledPane());
            });
        });

        settingsToggle.setOnAction(e -> {
            titledPane.setExpanded(!titledPane.isExpanded());
        });

        settingsIcon.disableProperty()
                .bind(settingsToggle.selectedProperty().not());

        deleteButton.setDisable(true);
        moveToGroupButton.setDisable(true);
        removeFromGroupButton.setDisable(true);

        selectCheckBox.selectedProperty().addListener((o, oldVal, selected) -> {
            selectButton.setText(selected ? "Deselect all" : "Select all");
            deleteButton.setDisable(!selected);
            moveToGroupButton.setDisable(!selected);
            removeFromGroupButton.setDisable(!selected);
        });

        selectButton.setOnAction(e -> {
            selectCheckBox.setSelected(!selectCheckBox.isSelected());
        });

        selectCheckBox.selectedProperty().addListener((ob, ov, nv) -> {
            accordion.getPanes().clear();

            profiles.forEach(profile -> {
                ProfileTitledPane ptp = getProfileTitledPane(
                        profile.getProfileNameProperty().get());
                ptp.setTitledPaneSelected(nv);
                accordion.getPanes().add(ptp.getTitledPane());
            });
        });

        //Ensures that only ONE pane is expanded in the
        //accordion at any one time
        accordion.expandedPaneProperty().addListener((ob, ov, nv) -> {
            accordion.getPanes()
                    .filtered(pane -> !pane.equals(nv))
                    .filtered(TitledPane::isExpanded)
                    .forEach(pane -> pane.setExpanded(false));
        });

        initTable();
    }

    private void initTable() {
        profiles.add(new Profile("Profile 1", "", "", LocalDateTime.now()));
        profiles.add(new Profile("Profile 2", "", "", LocalDateTime.now()));
        profiles.add(new Profile("Profile 3", "", "", LocalDateTime.now()));
        profiles.add(new Profile("Profile 4", "", "", LocalDateTime.now()));
        profiles.add(new Profile("Profile 5", "", "", LocalDateTime.now()));
        profiles.add(new Profile("Profile 6", "", "", LocalDateTime.now()));

        profiles.forEach(profile -> {
            accordion.getPanes()
                    .add(getProfileTitledPane(profile.getProfileNameProperty().get())
                            .getTitledPane());
        });

    }

    private ProfileTitledPane getProfileTitledPane(String profileName) {
        URL location = getClass().getResource("/views/ProfilePane.fxml");
        FXMLLoader loader = new FXMLLoader(location);

        TitledPane tp = null;
        ProfilePaneController controller = null;
        try {
            tp = loader.load();
            controller = loader.getController();

            controller.setTitledPaneTitle(profileName);
            controller.setTitledPaneSelected(true);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        ProfileTitledPane ptp = new ProfileTitledPane(tp, controller);
        ptp.setTitledPaneSelected(selectCheckBox.isSelected());
        ptp.showSelectCheckboxes(titledPane.isExpanded());

        return ptp;
    }

    private static class ProfileTitledPane {

        private final TitledPane titledPane;
        private final ProfilePaneController controller;

        private ProfileTitledPane(
                TitledPane titledPane,
                ProfilePaneController controller) {
            this.titledPane = titledPane;
            this.controller = controller;
        }

        private TitledPane getTitledPane() {
            return titledPane;
        }

        private void setTitledPaneSelected(boolean selected) {
            controller.setTitledPaneSelected(selected);
        }

        private void showSelectCheckboxes(boolean show) {
            controller.showSelectBoxes(show);
        }

    }
}
