/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.manyfaces.model.Profile;
import com.manyfaces.spi.Registry;
import com.manyfaces.spi.RootComponent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileOverviewController
        implements ProfileMenuController.ProfileMenuChildController {

    private static final Logger LOG;
    private static final Lookup LOOKUP = Lookup.getDefault();
    @FXML
    private AnchorPane overviewPane;
    @FXML
    private JFXButton editProxyButton;
    @FXML
    private Hyperlink timezoneHyperlink;
    @FXML
    private Hyperlink webRtcHyperlink;
    @FXML
    private Hyperlink geolocationHyperlink;
    @FXML
    private JFXTextField profileNameField;
    @FXML
    private Hyperlink editOsHyperlink;
    @FXML
    private Label osLabel;


    static {
        LOG = Logger.getLogger(ProfileOverviewController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        LOOKUP.lookup(Registry.class)
                .getLookup()
                .lookupAll(Profile.class)
                .stream()
                .findFirst()
                .ifPresent(p -> {
                    Platform.runLater(() -> profileNameField.setText(p.getName()));
                });

        profileNameField.textProperty().addListener((o, ov, nv) -> {
            LOG.log(Level.INFO, "Text entered: {0}", nv);

            if (nv != null) {
                LOOKUP.lookup(Registry.class).setAll(nv);
            }
        });

        editOsHyperlink.setOnAction(e -> showChooseOSDialog());
    }

    @Override
    public void setProfileMenuController(ProfileMenuController controller) {
        String message = "Profile menu controller should not be null";
        ProfileMenuController pmc = Objects.requireNonNull(controller, message);

        editProxyButton.setOnAction(e -> pmc.showProxyContent());
        timezoneHyperlink.setOnAction(e -> pmc.showTimezoneContent());
        webRtcHyperlink.setOnAction(e -> pmc.showWebRtcContent());
        geolocationHyperlink.setOnAction(e -> pmc.showGeolocationContent());
    }

    private void showChooseOSDialog() {
        URL location = getClass().getResource("/views/ChooseOSDialog.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane pane = null;
        ChooseOSDialogController controller = null;

        try {
            pane = loader.load();
            controller = loader.getController();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        if (pane != null && controller != null) {
            JFXDialog dialog = new JFXDialog();
            dialog.setContent(pane);
            controller.setDialog(dialog);
            controller.setOSLabel(osLabel);
            dialog.show(LOOKUP.lookup(RootComponent.class).getRoot());
        }
    }

}
