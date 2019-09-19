/*
 Copyright 2019.
 */
package com.manyfaces;

import com.manyfaces.ui.Home;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class Main extends Application {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    /**
     @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Home().getPane());

        primaryStage.setOnShown(e -> maximizeFallBack(primaryStage));
        primaryStage.setTitle("Many Faces");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     {@link Stage#setMaximized(boolean)} doesn't seem to work. So this is a
     fallback hack for maximizing the stage.

     @param stage the primary stage
     */
    private void maximizeFallBack(Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }

}
