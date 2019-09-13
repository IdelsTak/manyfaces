/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package com.github.idelstak.sample.ui.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class LocalNavigationViewController {

    private static final Logger LOG;
    @FXML
    private RadioButton homeToggle;
    @FXML
    private RadioButton newProfileToggle;
    @FXML
    private RadioButton accountToggle;
    @FXML
    private RadioButton pluginsToggle;
    @FXML
    private RadioButton helpToggle;
    @FXML
    private JFXButton groupSettingsButton;
    @FXML
    private FontIcon groupSettingsIcon;
    private AnchorPane pagesContainer;
    private Label sampleLabel;
    private AnchorPane homePage;
    private AnchorPane samplePage;

    static {
        LOG = Logger.getLogger(LocalNavigationViewController.class.getName());
    }

    public void setPagesContainer(AnchorPane pagesContainer) {
        this.pagesContainer = Objects.requireNonNull(
                pagesContainer,
                "Pages pane should not be null");

        URL url = getClass().getResource("/views/SamplePageView.fxml");
        Optional<AnchorPane> optionalPage = Optional.empty();

        try {
            optionalPage = Optional.ofNullable(FXMLLoader.load(url));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        samplePage = optionalPage.orElse(null);
        sampleLabel = optionalPage.map(page -> {
            return (Label) page.lookup("#sampleLabel");
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
    void initialize() throws IOException {
        RadioButton[] toggles = new RadioButton[]{homeToggle,
                                                  newProfileToggle,
                                                  accountToggle,
                                                  pluginsToggle,
                                                  helpToggle};

        new ToggleGroup().getToggles().addAll(Arrays.asList(toggles));
        //Remove the radio button styling for each toggle
        Arrays.stream(toggles).forEach(t -> t.getStyleClass().remove("radio-button"));

        homePage = FXMLLoader.load(getClass().getResource("/views/HomeView.fxml"));
    }

    @FXML
    void addFocusedIconColor() {
        groupSettingsIcon.getStyleClass().remove("icon-unfocused");
        groupSettingsIcon.getStyleClass().add("icon-focused");
    }

    @FXML
    void addUnfocusedIconColor() {
        groupSettingsIcon.getStyleClass().remove("icon-focused");
        groupSettingsIcon.getStyleClass().add("icon-unfocused");
    }

    @FXML
    void goToHomePage() {
        addPageToContainer(homePage);
    }

    @FXML
    void goToNewProfilePage() {
        sampleLabel.setText("<Profile page goes here>");
        addPageToContainer(samplePage);
    }

    @FXML
    void goToAccountPage() {
        sampleLabel.setText("<Account page goes here>");
        addPageToContainer(samplePage);
    }

    @FXML
    void goToPluginsPage() {
        sampleLabel.setText("<Plugins page goes here>");
        addPageToContainer(samplePage);
    }

    @FXML
    void goToHelpPage() {
        sampleLabel.setText("<Help page goes here>");
        addPageToContainer(samplePage);
    }

    @FXML
    void displayGroupSettings() {
        //TODO
    }

    private void initSelection() {
        homeToggle.setSelected(true);
        goToHomePage();
    }

    private void addPageToContainer(AnchorPane page) {
        ObservableList<Node> children = pagesContainer.getChildren();

        children.clear();
        children.add(page);

        AnchorPane.setTopAnchor(page, 0.0);
        AnchorPane.setBottomAnchor(page, 0.0);
        AnchorPane.setLeftAnchor(page, 0.0);
        AnchorPane.setRightAnchor(page, 0.0);
    }

}
