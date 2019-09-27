/*
 Copyright 2019.
 */
package com.manyfaces.ui;

import com.manyfaces.ui.controllers.AccountPreferencesTabController;
import com.manyfaces.ui.controllers.PageHeaderController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class MyAccountPreferences extends VBox {

    private static final Logger LOG;
    private Pane headerPane;
    private Pane accountPane;
    private PageHeaderController pageHeaderController;

    static {
        LOG = Logger.getLogger(MyAccountPreferences.class.getName());
    }

    public MyAccountPreferences() {
        super();
    }

    public VBox getPane() {
        try {
            getChildren().setAll(getHeaderPane(), getAccountPane());
            VBox.setVgrow(getAccountPane(), Priority.ALWAYS);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return this;
    }

    public Pane getHeaderPane() throws IOException {
        if (headerPane == null) {
            URL location = getClass().getResource("/views/PageHeader.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            headerPane = loader.load();
            pageHeaderController = loader.getController();
        }
        return headerPane;
    }

    public Pane getAccountPane() throws IOException {
        if (accountPane == null) {
            URL location = getClass().getResource("/views/AccountPreferencesTab.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            accountPane = loader.load();
            AccountPreferencesTabController controller = loader.getController();

            //First, ensure pageHeaderController is loaded
            getHeaderPane();
            controller.setPageHeaderController(pageHeaderController);
        }
        return accountPane;
    }

}
