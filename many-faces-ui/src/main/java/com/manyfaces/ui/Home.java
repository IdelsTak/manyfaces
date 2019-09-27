/*
 Copyright 2019.
 */
package com.manyfaces.ui;

import com.manyfaces.spi.RootComponent;
import com.manyfaces.ui.controllers.NavigationBarController;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.openide.util.lookup.ServiceProvider;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
@ServiceProvider(service = RootComponent.class)
public final class Home extends StackPane implements RootComponent {

    private static final Logger LOG = Logger.getLogger(Home.class.getName());
    private VBox contentPane;
    private Pane navigationPane;
    private final HBox homeContent;

    /**
     Default constructor.
     */
    public Home() {
        super();

        homeContent = new HBox(getNavigationPane(), getContentPane());
        HBox.setHgrow(getContentPane(), Priority.ALWAYS);

        getChildren().setAll(homeContent);
    }

    @Override
    public StackPane getRoot() {
        return this;
    }

    @Override
    public void resetContent() {
        Platform.runLater(() -> getChildren().setAll(homeContent));
    }
    
    @Override
    public void setContent(Node content){
        String message = "Content should not be null";
        Node kontent = Objects.requireNonNull(content, message);
        
        Platform.runLater(() -> getChildren().setAll(kontent));
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
