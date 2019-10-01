/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.github.javafaker.Faker;
import com.github.javafaker.Internet;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.manyfaces.ui.controllers.AlertInfoController.Style;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileWebRtcController {

    private static final Logger LOG;
    @FXML
    private RadioButton alteredToggle;
    @FXML
    private RadioButton disabledToggle;
    @FXML
    private RadioButton realToggle;
    @FXML
    private BorderPane behaviorAlertPane;
    @FXML
    private VBox ipSettingsBox;
    @FXML
    private VBox publicIPBox;
    @FXML
    private VBox localIpsBox;
    @FXML
    private JFXToggleButton enableMaskingToggle;
    @FXML
    private Hyperlink addIpHyperlink;
    @FXML
    private VBox extraIpsBox;
    @FXML
    private VBox otherIpsBox;
    @FXML
    private JFXToggleButton fillIPToggle;
    @FXML
    private JFXTextField fillIPField;
    @FXML
    private JFXTextField ip1Field;
    private final SimpleIntegerProperty ipPanesCountProperty;
    private int ipPanesCount;

    static {
        LOG = Logger.getLogger(ProfileWebRtcController.class.getName());
    }

    {
        ipPanesCount = 1;
        ipPanesCountProperty = new SimpleIntegerProperty(ipPanesCount);
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        Internet net = new Faker().internet();
        ip1Field.setText(net.privateIpV4Address());
        fillIPField.setText(net.publicIpV4Address());
        
        alteredToggle.setOnAction(e -> {
            if (alteredToggle.isSelected()) {
                Pane alertPane = getAlertPane("WebRTC plugin will be turned on and "
                        + "will falsely leak your actual external IP as a Public IP "
                        + "address. A valid Local IP will also be falsely leaked "
                        + "instead of your actual local IP address.",
                        Style.INFO);
                alertPane.setMinHeight(110.0);
                behaviorAlertPane.setCenter(alertPane);

                localIpsBox.getChildren().remove(enableMaskingToggle);

                otherIpsBox.setVisible(true);
            }
        });

        alteredToggle.selectedProperty().addListener((o, ov, selected) -> {
            if (selected && !ipSettingsBox.getChildren().contains(publicIPBox)) {
                ipSettingsBox.getChildren().add(0, publicIPBox);
            } else if (!selected) {
                ipSettingsBox.getChildren().remove(publicIPBox);
            }
        });

        disabledToggle.setOnAction(e -> {
            if (disabledToggle.isSelected()) {
                Pane alertPane = getAlertPane("WebRTC plugin will be turned off "
                        + "completely. Websites will see that you turned it off.",
                        Style.WARNING);
                alertPane.setMinHeight(70.0);
                behaviorAlertPane.setCenter(alertPane);
            }
        });

        realToggle.setOnAction(e -> {
            if (realToggle.isSelected()) {
                Pane alertPane = getAlertPane("WebRTC plugin will be turned on and "
                        + "will leak your real IP. This mode is only recommended if "
                        + "you don't use proxies in your connection.",
                        Style.WARNING);
                alertPane.setMinHeight(90.0);
                behaviorAlertPane.setCenter(alertPane);

                otherIpsBox.setVisible(enableMaskingToggle.isSelected());
            }
        });

        realToggle.selectedProperty().addListener((o, ov, selected) -> {
            if (selected
                    && !localIpsBox.getChildren().contains(enableMaskingToggle)) {
                localIpsBox.getChildren().add(enableMaskingToggle);
            }
        });

        enableMaskingToggle.selectedProperty().addListener((o, ov, nv) -> {
            if (realToggle.isSelected()) {
                otherIpsBox.setVisible(nv);
            }
        });

        ipSettingsBox.visibleProperty().bind(disabledToggle.selectedProperty().not());

        fillIPField.disableProperty().bind(fillIPToggle.selectedProperty());

        Platform.runLater(() -> alteredToggle.fireEvent(new ActionEvent()));

        addIpHyperlink.setOnAction(e -> {
            extraIpsBox.getChildren().add(getExtraIpPane());
            ipPanesCount += 1;
            ipPanesCountProperty.set(ipPanesCount);
        });

        ipPanesCountProperty.addListener((o, ov, nv) -> {
            ipPanesCount = nv.intValue();
        });
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

    private Pane getExtraIpPane() {
        URL location = getClass().getResource("/views/LocalIPs.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane pane = null;

        try {
            pane = loader.load();

            LocalIPsController controller = loader.getController();
            controller.setParentAndCountProperty(extraIpsBox, ipPanesCountProperty);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return pane;
    }

}
