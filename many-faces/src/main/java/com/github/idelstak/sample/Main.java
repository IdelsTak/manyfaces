/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package com.github.idelstak.sample;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
    public void start(Stage stage) throws IOException {

        URL url = getClass().getResource("/views/NavigationBar.fxml");
        HBox navigationBar = FXMLLoader.load(url);

        Scene scene = new Scene(navigationBar);

//        ObservableList<String> stylesheets = scene.getStylesheets();
//
//        URL cssUrl = getClass().getResource("/css/jfoenix-design.css");
//        URL fontsUrl = getClass().getResource("/css/jfoenix-fonts.css");
//        
//        LOG.log(Level.INFO, "Css url: {0}", cssUrl);
//
//        stylesheets.addAll(cssUrl.toExternalForm());
//        
//        stylesheets.addAll(
//                JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(), 
//                JFoenixResources.load("css/jfoenix-design.css").toExternalForm());

        stage.setTitle("Many Faces - alpha");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
