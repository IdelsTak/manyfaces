/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import com.manyfaces.ui.controllers.AlertInfoController.Style;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileGeolocationController {

    private static final Logger LOG;
    @FXML
    private RadioButton promptToggle;
    @FXML
    private RadioButton allowToggle;
    @FXML
    private RadioButton blockToggle;
    @FXML
    private BorderPane behaviorAlertPane;
    @FXML
    private JFXSlider accuracySlider;
    @FXML
    private Label accuracyValueLabel;
    @FXML
    private VBox locationSettingsBox;
    @FXML
    private JFXToggleButton fillLocationToggle;
    @FXML
    private VBox locationValuesBox;

    static {
        LOG = Logger.getLogger(ProfileGeolocationController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        promptToggle.setOnAction(e -> {
            if (promptToggle.isSelected()) {
                Pane alertPane = getAlertPane("Whenever a website requests your "
                        + "geolocation coordinates, you will be prompted in a "
                        + "browser to either accept or deny the request as it "
                        + "happens in a normal browser. In the first case, "
                        + "geolocation from the browser profile configuration "
                        + "will be provided to a website.",
                        Style.INFO);
                alertPane.setMinHeight(145.0);
                behaviorAlertPane.setCenter(alertPane);
            }
        });

        allowToggle.setOnAction(e -> {
            if (allowToggle.isSelected()) {
                Pane alertPane = getAlertPane("Whenever a website requests your "
                        + "geolocation coordinates, the request will always be "
                        + "honored. A website will receive geolocation "
                        + "coordinates configured in the browser profile.",
                        Style.WARNING);
                alertPane.setMinHeight(105.0);
                behaviorAlertPane.setCenter(alertPane);
            }
        });

        blockToggle.setOnAction(e -> {
            if (blockToggle.isSelected()) {
                Pane alertPane = getAlertPane("Websites will always be denied "
                        + "from receiveing your geolocaion coordintes.",
                        Style.WARNING);
                alertPane.setMinHeight(65.0);
                behaviorAlertPane.setCenter(alertPane);
            }
        });
        
        locationSettingsBox.visibleProperty().bind(blockToggle.selectedProperty().not());
        locationValuesBox.visibleProperty().bind(fillLocationToggle.selectedProperty().not());

        Platform.runLater(() -> promptToggle.fireEvent(new ActionEvent()));

        accuracyValueLabel.textProperty().bind(accuracySlider.valueProperty().asString());

    }

    private Pane getAlertPane(String message, Style style) {
        URL location = getClass().getResource("/views/AlertInfo.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane pane = null;

        try {
            pane = loader.load();

            AlertInfoController controller = loader.getController();
            controller.setMessage(message, style);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return pane;
    }

}
