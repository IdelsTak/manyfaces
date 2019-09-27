/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.manyfaces.spi.RootComponent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputEvent;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileMenuController {

    private static final Logger LOG;
    private static final Lookup LOOKUP = Lookup.getDefault();
    @FXML
    private Label menuTitleLabel;
    @FXML
    private JFXButton goHomeButton;
    @FXML
    private TitledPane advancedMenuTitledPane;
    @FXML
    private RadioButton advancedMenuToggle;
    @FXML
    private ToggleGroup profileMenuGroup;
    @FXML
    private RadioButton overviewToggle;
    @FXML
    private RadioButton proxyToggle;
    @FXML
    private RadioButton timezoneToggle;
    @FXML
    private RadioButton webRtcToggle;
    @FXML
    private RadioButton geoLocationToggle;
    @FXML
    private RadioButton navigatorToggle;
    @FXML
    private RadioButton fontsToggle;
    @FXML
    private RadioButton devicesToggle;
    @FXML
    private RadioButton hardwareToggle;
    @FXML
    private RadioButton extensionsToggle;
    @FXML
    private RadioButton storageToggle;
    @FXML
    private RadioButton browserPluginsToggle;
    @FXML
    private RadioButton otherToggle;
    private ProfileAttributeController attributeController;

    static {
        LOG = Logger.getLogger(ProfileMenuController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        advancedMenuTitledPane.addEventFilter(InputEvent.ANY, e -> {
            //If the target of the event is the title, ignore it
            //we want to be able to expand the titled pane using 
            //the advancedMenuToggle only
            if (((Styleable) e.getTarget())
                    .getStyleClass()
                    .contains("title")) {
                e.consume();
            }
        });

        BooleanProperty menuExpand = advancedMenuTitledPane.expandedProperty();
        BooleanProperty menuToggleSelected = advancedMenuToggle.selectedProperty();

        menuExpand.bind(menuToggleSelected);

        goHomeButton.setOnAction(e -> {
            LOOKUP.lookup(RootComponent.class).resetContent();
        });
    }

    public void setMenuTitle(String menuTitle) {
        String message = "Profile menu title should not be null";
        String title = Objects.requireNonNull(menuTitle, message);

        menuTitleLabel.setText(title);
    }

    public void setProfileAttributeController(ProfileAttributeController controller) {
        String message = "Profile attribute controller should not be null";
        attributeController = Objects.requireNonNull(controller, message);

        profileMenuGroup.selectedToggleProperty()
                .addListener((o, ov, nv) -> {
                    if (nv != null) {
                        attributeController.setHeaderText(((Labeled) nv).getText());
                    }
                });

        Toggle selectedToggle = profileMenuGroup.getSelectedToggle();
        attributeController.setHeaderText(((Labeled) selectedToggle).getText());

        overviewToggle.setOnAction(e -> setContentFrom("/views/ProfileOverview.fxml"));
        proxyToggle.setOnAction(e -> setContentFrom("/views/ProfileProxy.fxml"));
        timezoneToggle.setOnAction(e -> setContentFrom("/views/ProfileTimezone.fxml"));
        webRtcToggle.setOnAction(e -> setContentFrom("/views/ProfileWebRtc.fxml"));
        otherToggle.setOnAction(e -> setContentFrom("/views/ProfileAdvancedOther.fxml"));

        overviewToggle.fireEvent(new ActionEvent(null, null));
    }

    void showProxyContent() {
        proxyToggle.setSelected(true);
        setContentFrom("/views/ProfileProxy.fxml");
    }

    void showTimezoneContent() {
        timezoneToggle.setSelected(true);
        setContentFrom("/views/ProfileTimezone.fxml");
    }

    void showWebRtcContent() {
        webRtcToggle.setSelected(true);
        setContentFrom("/views/ProfileWebRtc.fxml");
    }

    private void setContentFrom(String path) {
        URL location = getClass().getResource(path);
        FXMLLoader loader = new FXMLLoader(location);
        try {
            Node content = loader.load();

            Platform.runLater(() -> attributeController.setContent(content));

            if (loader.getController() instanceof ProfileMenuChildController) {
                ProfileMenuChildController childController = (ProfileMenuChildController) loader.getController();
                childController.setProfileMenuController(this);
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    interface ProfileMenuChildController {

        void setProfileMenuController(ProfileMenuController controller);
    }
}
