/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXToggleNode;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
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
    private JFXToggleNode expandPaneToggle;
    @FXML
    private JFXButton menuButton;

    static {
        LOG = Logger.getLogger(ProfilePaneController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        titledPane.widthProperty().addListener((obs, ov, nv) -> {
            titleHbox.setPrefWidth(nv.doubleValue());
        });

        titledPane.expandedProperty().addListener((obs, ov, nv) -> {
            expandPaneToggle.setSelected(nv);
        });

        expandPaneToggle.selectedProperty().addListener((ob, ov, nv) -> {
            titledPane.setExpanded(nv);
        });

        URL styleUrl = getClass().getResource("/styles/profile-pane.css");
        String style = styleUrl.toExternalForm();
        JFXListView<Label> list = new JFXListView<>();
        list.getStylesheets().add(style);

        list.getItems().add(new Label("Edit"));
        list.getItems().add(new Label("Move to a group"));
        list.getItems().add(new Label("Delete"));

        list.selectionModelProperty()
                .getValue()
                .selectedItemProperty()
                .addListener((ob, ov, nv) -> {
                    switch (nv.getText()) {
                        case "Edit":
                            LOG.log(Level.INFO, "Edit");
                            return;
                        case "Move to a group":
                            LOG.log(Level.INFO, "Move to a group");
                            return;
                        case "Delete":
                            LOG.log(Level.INFO, "Delete");
                            return;
                        default:
                            throw new IllegalArgumentException("Action not known");

                    }
                });

        JFXPopup popup = new JFXPopup(list);
        
        menuButton.setOnAction(e -> {
            popup.show(menuButton,
                    JFXPopup.PopupVPosition.TOP,
                    JFXPopup.PopupHPosition.RIGHT);
        });

    }

    void setTitledPaneTitle(String profileName) {
        profileNameLabel.setText(profileName);
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
