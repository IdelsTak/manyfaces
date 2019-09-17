/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import com.manyfaces.model.Profile;
import com.manyfaces.ui.ExpanderColumnCell;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.css.Styleable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.control.table.TableRowExpanderColumn;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileListController {

    private static final Logger LOG;
    @FXML
    private TitledPane titledPane;
    @FXML
    private JFXCheckBox selectCheckBox;
    @FXML
    private JFXButton selectButton;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton moveToGroupButton;
    @FXML
    private JFXButton removeFromGroupButton;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXToggleNode settingsToggle;
    @FXML
    private FontIcon settingsIcon;
    @FXML
    private JFXToggleNode refreshButton;
    @FXML
    private FontIcon refreshIcon;
    @FXML
    private TableView<Profile> profilesTable;

    static {
        LOG = Logger.getLogger(ProfileListController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        titledPane.addEventFilter(InputEvent.ANY, e -> {
            //If the target of the event is the title, ignore it
            //we want to be able to expand the titled pane using 
            //our custom button only
            if (((Styleable) e.getTarget())
                    .getStyleClass()
                    .contains("title")) {
                e.consume();
            }
        });

        titledPane.expandedProperty().addListener((o, oldVal, expanded) -> {
            selectCheckBox.setSelected(selectCheckBox.isSelected() 
                    && expanded != null 
                    && !expanded);
        });

        settingsToggle.setOnAction(e -> {
            titledPane.setExpanded(!titledPane.isExpanded());
        });

        settingsIcon.disableProperty()
                .bind(settingsToggle.selectedProperty().not());

        deleteButton.setDisable(true);
        moveToGroupButton.setDisable(true);
        removeFromGroupButton.setDisable(true);

        selectCheckBox.selectedProperty().addListener((o, oldVal, selected) -> {
            selectButton.setText(selected ? "Deselect all" : "Select all");
            deleteButton.setDisable(!selected);
            moveToGroupButton.setDisable(!selected);
            removeFromGroupButton.setDisable(!selected);
        });

        selectButton.setOnAction(e -> {
            selectCheckBox.setSelected(!selectCheckBox.isSelected());
        });
        
        initTable();
    }
    
    private Pane getProfileEditor(TableRowExpanderColumn.TableRowDataFeatures<Profile> rowData) {
        URL location = getClass().getResource("/views/ProfileEditView.fxml");
        Pane profileEditPane = null;

        try {
            profileEditPane = FXMLLoader.load(location);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return profileEditPane;
    }

    @SuppressWarnings("unchecked")
    private void initTable() {
        TableRowExpanderColumn<Profile> expanderColumn = new TableRowExpanderColumn<>(this::getProfileEditor);

        expanderColumn.setText("Name");
        expanderColumn.setCellFactory(c -> {
            ExpanderColumnCell columnCell = new ExpanderColumnCell(expanderColumn, selectCheckBox);

            selectCheckBox.selectedProperty().addListener((o, oldVal, newVal) -> {
                columnCell.getRowCheckBox().setSelected(newVal);
            });

            return columnCell;
        });
        
        TableColumn<Profile, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
        
        TableColumn<Profile, String> membersColumn = new TableColumn<>("Members");
        
        TableColumn<Profile, LocalDateTime> lastEditedColumn = new TableColumn<>("Last edited");
        lastEditedColumn.setCellValueFactory(cellData -> cellData.getValue().getLastEditedProperty());
        
        profilesTable.getColumns().addAll(
                expanderColumn, 
                statusColumn, 
                membersColumn,
                lastEditedColumn);

        Collection<Profile> profiles = new ArrayList<>();

        profiles.add(new Profile("Profile 1", "", "", LocalDateTime.now()));
        profiles.add(new Profile("Profile 2", "", "", LocalDateTime.now()));
        profiles.add(new Profile("Profile 3", "", "", LocalDateTime.now()));
        profiles.add(new Profile("Profile 4", "", "", LocalDateTime.now()));
        profiles.add(new Profile("Profile 5", "", "", LocalDateTime.now()));
        profiles.add(new Profile("Profile 6", "", "", LocalDateTime.now()));

        profilesTable.getItems().addAll(profiles);
    }
}
