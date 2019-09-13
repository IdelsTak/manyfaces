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
 The application's entry point.

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class Main extends Application {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    /**
     Launches the application.

     @see Application#launch(String... args);
     @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //Locate the navigation bar's fxml
        URL url = getClass().getResource("/views/LocalNavigationView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        //Load its object heirarchy
        Node navigationPane = fxmlLoader.load();
        //If load successful, retrieve the form's controller
        LocalNavigationViewController controller = fxmlLoader.getController();
        //Create the area that will hold the
        //views selected on the in the navigation bar
        BorderPane mainWindow = new BorderPane();
        AnchorPane pagesCointainer = new AnchorPane();
        //Set the navigation bar to the left of
        //of the main window
        mainWindow.setLeft(navigationPane);
        //Set the pane that will hold the details
        //of the navigation's selection to the center
        mainWindow.setCenter(pagesCointainer);
        //Attach the area that will hold the details
        //of the navigation's selection to the navigation
        //bar's controller
        controller.setPagesContainer(pagesCointainer);
        //Initialize the main window
        stage.setTitle("Many Faces - alpha");
        stage.setScene(new Scene(mainWindow));
        stage.setMaximized(true);
        stage.show();
    }

}
