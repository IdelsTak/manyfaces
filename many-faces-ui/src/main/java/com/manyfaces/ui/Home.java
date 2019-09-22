/*
 Copyright 2019.
 */
package com.manyfaces.ui;

import com.manyfaces.ui.controllers.NavigationBarController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public final class Home extends StackPane {

    private static final Logger LOG = Logger.getLogger(Home.class.getName());
    private VBox contentPane;
    private Pane navigationPane;

    /**
     Default constructor.
     */
    public Home() {
        super();

        HBox hBox = new HBox(getNavigationPane(), getContentPane());
        HBox.setHgrow(getContentPane(), Priority.ALWAYS);

        getChildren().setAll(hBox);
    }

    private VBox getContentPane() {
        if (contentPane == null) {
            VBox vBox = new VBox();
            //A red border for testing purposes
            //vBox.getStyleClass().add("-fx-border-color: red");
            vBox.setPrefHeight(632);
            vBox.setMinHeight(632);
            vBox.setMinWidth(706);
            contentPane = vBox;
        }

        return contentPane;
    }

    /**
     @return the navigationPane
     */
    private Pane getNavigationPane() {
        if (navigationPane == null) {
            URL location = getClass().getResource("/views/NavigationBar.fxml");
            FXMLLoader loader = new FXMLLoader(location);

            try {
                navigationPane = loader.load();
                NavigationBarController controller = loader.getController();
                controller.setHomeContentPane(getContentPane());
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }

        return navigationPane;
    }
}
