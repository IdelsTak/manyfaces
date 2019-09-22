/*
 Copyright 2019.
 */
package com.manyfaces.ui;

import com.manyfaces.ui.controllers.PageHeaderController;
import com.manyfaces.ui.controllers.PluginsController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class Plugins extends VBox {

    private static final Logger LOG = Logger.getLogger(Plugins.class.getName());
    private Pane headerPane;
    private Pane pluginsPane;
    private PageHeaderController pageHeaderController;

    public Plugins() {
        super();
    }

    public VBox getPane() {
         try {
            getChildren().setAll(getHeaderPane(), getPluginsPane());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return this;
    }

    private Pane getHeaderPane() throws IOException {
        if (headerPane == null) {
            URL location = getClass().getResource("/views/PageHeader.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            headerPane = loader.load();
            pageHeaderController = loader.getController();
        }
        return headerPane;
    }

    private Pane getPluginsPane() throws IOException {
        if (pluginsPane == null) {
            URL location = getClass().getResource("/views/Plugins.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            pluginsPane = loader.load();
            PluginsController controller = loader.getController();

            //First, ensure pageHeaderController is loaded
            getHeaderPane();
            controller.setPageHeaderController(pageHeaderController);

        }
        return pluginsPane;
    }

}
