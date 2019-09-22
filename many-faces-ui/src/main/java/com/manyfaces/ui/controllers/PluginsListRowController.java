/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXToggleNode;
import java.util.Objects;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class PluginsListRowController {

    private static final Logger LOG;
    @FXML
    private TitledPane titledPane;
    @FXML
    private HBox titleHbox;
    @FXML
    private AnchorPane pluginContentPane;
    @FXML
    private Label pluginNameLabel;
    @FXML
    private JFXToggleNode openContentToggle;
    @FXML
    private Label sampleContentLabel;

    static {
        LOG = Logger.getLogger(PluginsListRowController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
//        titledPane.addEventFilter(InputEvent.ANY, e -> {
//            //If the target of the event is the title, ignore it
//            //we want to be able to expand the titled pane using 
//            //our custom button only
//            if (((Styleable) e.getTarget())
//                    .getStyleClass()
//                    .contains("title")) {
//                e.consume();
//            }
//        });
        titleHbox.prefWidthProperty().bind(titledPane.widthProperty());
    }

    void setPluginName(String pluginName) {
        String message = "Plugin name should not be null";
        String name = Objects.requireNonNull(pluginName, message);

        pluginNameLabel.setText(name);
        titledPane.setId(name);
    }

    void setPluginContent(Node content) {
        String message = "Content pane should not be null";
        Node kontent = Objects.requireNonNull(content, message);

        pluginContentPane.getChildren().setAll(kontent);

        AnchorPane.setTopAnchor(kontent, 0.0);
        AnchorPane.setRightAnchor(kontent, 0.0);
        AnchorPane.setBottomAnchor(kontent, 0.0);
        AnchorPane.setLeftAnchor(kontent, 0.0);
    }
    
    void setTestContent(String testContent){
        sampleContentLabel.setText(testContent);
    }

    void setParentAccordion(Accordion accordion) {
        String message = "Plugins accordion should not be null";
        Accordion akkordion = Objects.requireNonNull(accordion, message);

//        akkordion.getPanes()
//                .stream()
//                .filter(tp -> tp.getId().equals(titledPane.getId()))
//                .findFirst()
//                .ifPresent(tp -> {
        openContentToggle.setOnAction(e -> {
            if (titledPane.isExpanded()) {
                titledPane.setExpanded(false);
            } else {
                akkordion.setExpandedPane(titledPane);
            }
        });
//                });
//
//        accordion.getPanes()
//                .stream()
//                .filter(tp -> tp.isExpanded())
//                .findFirst()
//                .ifPresent(tp -> {
//                    if (!tp.getId().equals(titledPane.getId())) {
//                        tp.setExpanded(false);
//                    }
//                });
    }

}
