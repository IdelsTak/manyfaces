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
    private AnchorPane pagesContainer;
    private Label sampleLabel;

    static {
        LOG = Logger.getLogger(LocalNavigationViewController.class.getName());
    }

    public void setPagesContainer(AnchorPane pagesContainer) {
        this.pagesContainer = Objects.requireNonNull(
                pagesContainer,
                "Pages pane should not be null");

        URL url = getClass().getResource("/views/SamplePageView.fxml");
        Optional<AnchorPane> samplePage = Optional.empty();

        try {
            samplePage = Optional.ofNullable(FXMLLoader.load(url));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        samplePage.ifPresent(this::addSamplePageToContainer);

        sampleLabel = samplePage.map(pane -> {
            return (Label) pane.lookup("#sampleLabel");
        }).orElse(null);

        initSelection();
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

    private void initSelection() {
        homeToggle.setSelected(true);
        goToHomePage();
    }

    private void goToHomePage() {
        sampleLabel.setText("<Home page goes here>");
    }

    private void goToNewProfilePage() {
        sampleLabel.setText("<Profile page goes here>");
    }

    private void goToAccountPage() {
        sampleLabel.setText("<Account page goes here>");
    }

    private void goToPluginsPage() {
        sampleLabel.setText("<Plugins page goes here>");
    }

    private void goToHelpPage() {
        sampleLabel.setText("<Help page goes here>");
    }

    private void addSamplePageToContainer(AnchorPane samplePage) {
        this.pagesContainer.getChildren().add(samplePage);

        AnchorPane.setTopAnchor(samplePage, 0.0);
        AnchorPane.setBottomAnchor(samplePage, 0.0);
        AnchorPane.setLeftAnchor(samplePage, 0.0);
        AnchorPane.setRightAnchor(samplePage, 0.0);
    }

}
