/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXToggleNode;
import com.manyfaces.model.Profile;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfilePaneController {

    private static final Logger LOG;
    @FXML
    private JFXCheckBox titlePaneCheckBox;
    @FXML
    private TitledPane titledPane;
    @FXML
    private Text profileIdText;
    @FXML
    private HBox titleHbox;
    @FXML
    private Label profileNameLabel;
    @FXML
    private Label lastEditedLabel;
    @FXML
    private JFXToggleNode expandPaneToggle;
    @FXML
    private JFXButton menuButton;
    private JFXPopup popup;

    static {
        LOG = Logger.getLogger(ProfilePaneController.class.getName());
    }

    /**
     Initializes the controller class.

     @throws java.io.IOException
     */
    @FXML
    public void initialize() throws IOException {
        titledPane.widthProperty().addListener((obs, ov, nv) -> {
            titleHbox.setPrefWidth(nv.doubleValue());
        });

        titledPane.expandedProperty().addListener((obs, ov, nv) -> {
            expandPaneToggle.setSelected(nv);
        });

        expandPaneToggle.selectedProperty().addListener((ob, ov, nv) -> {
            titledPane.setExpanded(nv);
        });
    }

    void setProfileInstance(Profile profile) {
        String message = "Profile should not be null";
        Profile profyl = Objects.requireNonNull(profile, message);
        profileNameLabel.setText(profyl.getName());
        profileIdText.setText(profyl.getId());
        
        LocalDateTime lastEdited = profyl.getLastEdited();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        
        lastEditedLabel.setText(formatter.format(lastEdited));

        URL location = getClass().getResource("/views/ProfileActionsPopup.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane actionsPane = null;
        
        try {
            actionsPane = loader.load();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        
        if (actionsPane != null) {
            popup = new JFXPopup(actionsPane);
            ProfileActionsPopupController controller = loader.getController();

            controller.setProfilePopup(profyl, popup);
            menuButton.setOnAction(e -> {
                popup.show(menuButton,
                        JFXPopup.PopupVPosition.TOP,
                        JFXPopup.PopupHPosition.RIGHT);
            });
        }
    }

    void setTitledPaneSelected(boolean selected) {
        titlePaneCheckBox.setSelected(selected);
    }

    void showSelectBoxes(boolean show) {
        if (show) {
            titleHbox.getChildren().set(0, titlePaneCheckBox);
        } else {
            titleHbox.getChildren().remove(titlePaneCheckBox);
        }
    }
}
