/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.manyfaces.model.Profile;
import com.manyfaces.spi.RootComponent;
import com.manyfaces.ui.ProfileEditHome;
import com.manyfaces.ui.ProfileEditHome.EditType;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import org.openide.util.Lookup;
import com.manyfaces.spi.Registry;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileActionsPopupController {

    private static final Logger LOG;
    private static final Lookup LOOKUP = Lookup.getDefault();
    @FXML
    private JFXButton editButton;
    @FXML
    private JFXButton moveButton;
    @FXML
    private JFXButton deleteButton;

    static {
        LOG = Logger.getLogger(ProfileActionsPopupController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
    }

    void setProfilePopup(Profile profile, JFXPopup popup) {
        editButton.setOnAction(e -> {
            //Announce that the profile that was passed 
            //to this method is the one that all clients
            //should be keen on from now on
            LOOKUP.lookup(Registry.class).setAll(profile);
            
            popup.hide();
            
            RootComponent rc = LOOKUP.lookup(RootComponent.class);
            rc.setContent(new ProfileEditHome(EditType.EDIT).getPane());
        });
    }

}
