/*
 Copyright 2019.
 */
package com.manyfaces.ui;

import com.manyfaces.ui.controllers.HelpAndSupportController;
import com.manyfaces.ui.controllers.PageHeaderController;
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
public class Help extends VBox {

    private static final Logger LOG = Logger.getLogger(Help.class.getName());
    private Pane headerPane;
    private Pane helpPane;
    private PageHeaderController pageHeaderController;

    public Help() {
        super();
    }

    public VBox getPane() {
        try {
            getChildren().setAll(getHeaderPane(), getHelpPane());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return this;
    }

    /**
     @return the headerPane

     @throws java.io.IOException
     */
    private Pane getHeaderPane() throws IOException {
        if (headerPane == null) {
            URL location = getClass().getResource("/views/PageHeader.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            headerPane = loader.load();
            pageHeaderController = loader.getController();
        }
        return headerPane;
    }

    /**
     @return the helpPane

     @throws java.io.IOException
     */
    private Pane getHelpPane() throws IOException {
        if (helpPane == null) {
            URL location = getClass().getResource("/views/HelpAndSupport.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            helpPane = loader.load();
            HelpAndSupportController controller = loader.getController();

            //First, ensure pageHeaderController is loaded
            getHeaderPane();
            controller.setPageHeaderController(pageHeaderController);
        }
        return helpPane;
    }

}
