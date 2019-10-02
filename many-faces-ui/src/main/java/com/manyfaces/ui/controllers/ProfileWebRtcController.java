/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileWebRtcController {

    private static final Logger LOG;
    @FXML
    private VBox settingsPane;
    @FXML
    private RadioButton alteredToggle;
    @FXML
    private RadioButton disabledToggle;
    @FXML
    private RadioButton realToggle;
    private Pane alteredPane;
    private Pane disabledPane;
    private Pane realPane;

    static {
        LOG = Logger.getLogger(ProfileWebRtcController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        alteredToggle.setOnAction(e -> {
            getAlteredPane().ifPresent(settingsPane.getChildren()::setAll);
        });

        disabledToggle.setOnAction(e -> {
            getDisabledPane().ifPresent(settingsPane.getChildren()::setAll);
        });

        realToggle.setOnAction(e -> {
            getRealPane().ifPresent(settingsPane.getChildren()::setAll);
        });

        Platform.runLater(() -> alteredToggle.fireEvent(new ActionEvent()));
    }

    public Optional<Pane> getAlteredPane() {
        if (alteredPane == null) {
            URL location = getClass().getResource("/views/ProfileWebRtcAltered.fxml");
            FXMLLoader loader = new FXMLLoader(location);

            try {
                alteredPane = loader.load();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }

        return Optional.of(alteredPane);
    }

    public Optional<Pane> getDisabledPane() {
        if (disabledPane == null) {
            URL location = getClass().getResource("/views/ProfileWebRtcDisabled.fxml");
            FXMLLoader loader = new FXMLLoader(location);

            try {
                disabledPane = loader.load();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }

        return Optional.of(disabledPane);
    }

    public Optional<Pane> getRealPane() {
        if (realPane == null) {
            URL location = getClass().getResource("/views/ProfileWebRtcReal.fxml");
            FXMLLoader loader = new FXMLLoader(location);

            try {
                realPane = loader.load();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }

        return Optional.of(realPane);
    }

}
