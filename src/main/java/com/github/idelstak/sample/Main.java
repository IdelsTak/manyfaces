/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package com.github.idelstak.sample;

import com.github.idelstak.sample.ui.controllers.LocalNavigationViewController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
        URL url = getClass().getResource("/views/LocalNavigationView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Node navigationPane = fxmlLoader.load();
        LocalNavigationViewController controller = fxmlLoader.getController();
        BorderPane mainWindow = new BorderPane();
        AnchorPane pagesPane = new AnchorPane();
        
        mainWindow.setLeft(navigationPane);
        mainWindow.setCenter(pagesPane);
        
        controller.setPagesContainer(pagesPane);
        
        stage.setTitle("Many Faces - alpha");
        stage.setScene(new Scene(mainWindow));
        stage.setMaximized(true);
        stage.show();
    }

}
