/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.manyfaces.model.Profile;
import com.manyfaces.spi.ProfilesRepository;
import com.manyfaces.spi.Registry;
import com.manyfaces.ui.ProfileEditHome;
import java.util.Collection;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileAttributeController {

    private static final Logger LOG;
    private static final Lookup LOOKUP = Lookup.getDefault();
    @FXML
    private Text headerText;
    @FXML
    private AnchorPane attributeContentPane;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXButton updateButton;

    static {
        LOG = Logger.getLogger(ProfileAttributeController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
    }

    public void setHeaderText(String text) {
        headerText.setText(text);
    }

    public void setEditType(ProfileEditHome.EditType editType) {
        switch (editType) {
            case CREATE:
                updateButton.setText("Create profile");
                updateButton.setOnAction(e -> createProfile());
                break;
            case EDIT:
                updateButton.setText("Update profile");
                updateButton.setOnAction(e -> updateProfile());
                break;
            default:
                throw new IllegalArgumentException("Edit type not known");
        }
    }

    public void setContent(Node content) {
        String message = "Content node should not be null";
        Node kontent = Objects.requireNonNull(content, message);

        Platform.runLater(() -> {
            attributeContentPane.getChildren().setAll(kontent);
            //Ensure the content resizes with the
            //parent anchorpane
            AnchorPane.setTopAnchor(kontent, 0.0);
            AnchorPane.setRightAnchor(kontent, 0.0);
            AnchorPane.setBottomAnchor(kontent, 0.0);
            AnchorPane.setLeftAnchor(kontent, 0.0);
        });
    }

    private void updateProfile() {
        Registry gl = LOOKUP.lookup(Registry.class);
        Collection<? extends Profile> profiles = gl.getLookup().lookupAll(Profile.class);

        LOG.log(Level.INFO, "Found {0} profiles. Value(s): {1}",
                new Object[]{profiles.size(), profiles});
    }

    private void createProfile() {
        LOOKUP.lookup(Registry.class)
                .getLookup()
                .lookupAll(String.class)
                .stream()
                .findFirst()
                .ifPresent(name -> {
                    LOOKUP.lookup(ProfilesRepository.class).add(name);
                });
    }

}
