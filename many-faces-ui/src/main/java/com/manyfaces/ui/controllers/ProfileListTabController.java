/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileListTabController {

    private static final Logger LOG;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab profileListTab;
    @FXML
    private Tab groupsTab;

    static {
        LOG = Logger.getLogger(ProfileListTabController.class.getName());
    }

    /**
     Initializes the controller class.

     @throws java.io.IOException
     */
    @FXML
    public void initialize() throws IOException {
        URL location = getClass().getResource("/views/ProfileList.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane pane = null;

        try {
            pane = loader.load();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        if (pane != null) {
            profileListTab.setContent(pane);
        }
    }

    public void setPageHeaderController(PageHeaderController phc) {
        if (phc == null) {
            throw new IllegalArgumentException("Controller should not be null");
        }

        SingleSelectionModel<Tab> ssm = tabPane.getSelectionModel();
        ReadOnlyIntegerProperty roop = ssm.selectedIndexProperty();
        roop.addListener((o, oldVal, newVal) -> {
            setHeaderText(newVal.intValue(), phc);
        });

        setHeaderText(ssm.getSelectedIndex(), phc);
    }

    private void setHeaderText(int idx, PageHeaderController phc) {
        switch (idx) {
            case 0:
                phc.setHeaderText("Browser profile list");
                return;
            case 1:
                phc.setHeaderText("Groups");
                return;
            default:
                throw new IllegalArgumentException("Tab index not known");
        }
    }

}
