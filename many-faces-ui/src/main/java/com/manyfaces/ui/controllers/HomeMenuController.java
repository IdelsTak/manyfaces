/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.manyfaces.spi.RootComponent;
import com.manyfaces.ui.BrowserProfileList;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.css.Styleable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class HomeMenuController {

    private static final Logger LOG;
    @FXML
    private StackPane rootPane;
    @FXML
    private ToggleGroup menuGroup;
    @FXML
    private RadioButton homeToggle;
    @FXML
    private RadioButton newProfileToggle;
    @FXML
    private RadioButton accountToggle;
    @FXML
    private RadioButton pluginsToggle;
    @FXML
    private RadioButton helpToggle;
    @FXML
    private JFXButton groupSettingsButton;
    private Pane contentPane;

    static {
        LOG = Logger.getLogger(HomeMenuController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        menuGroup.getToggles().forEach(this::removeRadioButtonStyling);
        groupSettingsButton.setOnAction(e -> {

            URL location = getClass().getResource("/views/EditGroupsDialog.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            Pane pane = null;
            EditGroupsDialogController controller = null;

            try {
                pane = loader.load();
                controller = loader.getController();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }

            if (pane != null && controller != null) {
                RootComponent root = Lookup.getDefault().lookup(RootComponent.class);

                if (root != null) {
                    JFXDialog dialog = new JFXDialog();
                    dialog.setContent(pane);
                    dialog.setTransitionType(JFXDialog.DialogTransition.BOTTOM);
                    controller.setDialog(dialog);
                    dialog.show(root.getRoot());
                }
            }

        });
    }

    public void setContentPane(Pane contentPane) {
        this.contentPane = contentPane;

        homeToggle.selectedProperty().addListener((o, ov, nv) -> {
            if (nv) {
                openBrowserProfileList();
            }
        });

        openBrowserProfileList();
        homeToggle.setSelected(true);
    }

    private void openBrowserProfileList() {
        contentPane.getChildren().setAll(new BrowserProfileList().loadPane());
    }

    private void removeRadioButtonStyling(Toggle toggle) {
        ((Styleable) toggle).getStyleClass().remove("radio-button");
    }

}
