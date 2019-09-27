/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import com.manyfaces.model.Profile;
import com.manyfaces.spi.ProfilesRepository;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.css.Styleable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputEvent;
import org.kordamp.ikonli.javafx.FontIcon;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileListController {

    private static final Logger LOG;
    private static final Lookup LOOKUP = Lookup.getDefault();
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
    private ObservableSet<Profile> profiles;

    static {
        LOG = Logger.getLogger(ProfileListController.class.getName());
    }

    {
        profiles = LOOKUP.lookup(ProfilesRepository.class).findAll();
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
                getProfilePane(profile).showSelectCheckboxes(expanded);
                accordion.getPanes().add(getProfilePane(profile).getTitledPane());
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
                getProfilePane(profile).setTitledPaneSelected(nv);
                accordion.getPanes().add(getProfilePane(profile).getTitledPane());
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

        profiles.addListener((SetChangeListener.Change<? extends Profile> change) -> {
            if (change.wasAdded()) {
                refreshProfileRows();
            }
        });

        refreshProfileRows();
    }

    private void refreshProfileRows() {
        accordion.getPanes().clear();

        Platform.runLater(() -> {
            profiles.forEach(profile -> {
                accordion.getPanes().add(getProfilePane(profile).getTitledPane());
            });
        });

    }

    private ProfilePane getProfilePane(Profile profile) {
        URL location = getClass().getResource("/views/ProfilePane.fxml");
        FXMLLoader loader = new FXMLLoader(location);

        TitledPane tp = null;
        ProfilePaneController controller = null;
        try {
            tp = loader.load();
            controller = loader.getController();

            controller.setProfileInstance(profile);
            controller.setTitledPaneSelected(true);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        ProfilePane ptp = new ProfilePane(tp, controller);
        ptp.setTitledPaneSelected(selectCheckBox.isSelected());
        ptp.showSelectCheckboxes(titledPane.isExpanded());

        return ptp;
    }

    private static class ProfilePane {

        private final TitledPane titledPane;
        private final ProfilePaneController controller;

        private ProfilePane(
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
