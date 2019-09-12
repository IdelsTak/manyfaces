/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package com.github.idelstak.sample.ui.controllers;

import com.jfoenix.controls.JFXToggleNode;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class LocalNavigationViewController {

    private static final Logger LOG;
    @FXML
    private JFXToggleNode homeToggle;
    @FXML
    private JFXToggleNode newProfileToggle;
    @FXML
    private JFXToggleNode accountToggle;
    @FXML
    private JFXToggleNode pluginsToggle;
    @FXML
    private JFXToggleNode helpToggle;
    private AnchorPane pagesPane;
    private Label sampleLabel;

    static {
        LOG = Logger.getLogger(LocalNavigationViewController.class.getName());
    }

    public void setPagesPane(AnchorPane pagesPane) {
        this.pagesPane = Objects.requireNonNull(pagesPane, "Pages pane should not be null");

        URL url = getClass().getResource("/views/SamplePageView.fxml");
        Optional<AnchorPane> page = Optional.empty();

        try {
            page = Optional.ofNullable(FXMLLoader.load(url));
            
            this.pagesPane.getChildren().add(page.get());
            
            AnchorPane.setTopAnchor(page.get(), 0.0);
            AnchorPane.setBottomAnchor(page.get(), 0.0);
            AnchorPane.setLeftAnchor(page.get(), 0.0);
            AnchorPane.setRightAnchor(page.get(), 0.0);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        sampleLabel = page.map(pane -> (Label) pane.lookup("#sampleLabel"))
                .orElse(null);
        
        homeToggle.setSelected(true);
        
        goToHomePage();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    /**
     Initializes the controller class.
     */
    @FXML
    void initialize() {
        ToggleGroup group = new ToggleGroup();

        group.getToggles().addAll(
                homeToggle,
                newProfileToggle,
                accountToggle,
                pluginsToggle,
                helpToggle);

        homeToggle.setOnAction(e -> goToHomePage());
        newProfileToggle.setOnAction(e -> goToNewProfilePage());
        accountToggle.setOnAction(e -> goToAccountPage());
        pluginsToggle.setOnAction(e -> goToPluginsPage());
        helpToggle.setOnAction(e -> goToHelpPage());
    }

    private void goToHomePage() {
        LOG.log(Level.INFO, "Set home page at {0}", pagesPane);

        sampleLabel.setText("<Home page goes here>");
    }

    private void goToNewProfilePage() {
        LOG.log(Level.INFO, "Set new profile page");
        sampleLabel.setText("<Profile page goes here>");
    }

    private void goToAccountPage() {
        LOG.log(Level.INFO, "Set account page");
        sampleLabel.setText("<Account page goes here>");
    }

    private void goToPluginsPage() {
        LOG.log(Level.INFO, "Set plugins page");
        sampleLabel.setText("<Plugins page goes here>");
    }

    private void goToHelpPage() {
        LOG.log(Level.INFO, "Set help page");
        sampleLabel.setText("<Help page goes here>");
    }

}
