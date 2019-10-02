/*
 Copyright 2019.
 */
package com.manyfaces;

import com.manyfaces.spi.RootComponent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.openide.util.Lookup;

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
        
        loadRobotoFonts();
        
        Lookup globalLookup = Lookup.getDefault();
        RootComponent rootComponent = globalLookup.lookup(RootComponent.class);

        if (rootComponent != null) {
            Scene scene = new Scene(rootComponent.getRoot());

            primaryStage.setOnShown(e -> maximizeFallBack(primaryStage));
            primaryStage.setTitle("Many Faces");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
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

    private void loadRobotoFonts() {
        Font roboto = Font.loadFont(getClass().getResourceAsStream("/fonts/roboto/Roboto-Regular.ttf"), 13);
        Font robotoItalic = Font.loadFont(getClass().getResourceAsStream("/fonts/roboto/Roboto-Italic.ttf"), 13);
        Font robotoBold = Font.loadFont(getClass().getResourceAsStream("/fonts/roboto/Roboto-Bold.ttf"), 13);
        
        LOG.log(Level.INFO, "roboto: {0}; italic: {1}; bold: {2}", 
                new Object[]{roboto, robotoItalic, robotoBold});
    }

}
