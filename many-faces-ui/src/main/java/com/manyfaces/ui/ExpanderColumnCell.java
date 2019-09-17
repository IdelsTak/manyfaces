/*
 Copyright 2019.
 */
package com.manyfaces.ui;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleNode;
import com.manyfaces.model.Profile;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Pane;
import org.controlsfx.control.table.TableRowExpanderColumn;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ExpanderColumnCell extends TableCell<Profile, Boolean> {

    private static final Logger LOG;
    private JFXToggleNode toggleButton;
    private Pane pane;
    private JFXCheckBox rowCheckBox;
    private Label nameLabel;
    private JFXCheckBox selectAllCheckBox;
    private final TableRowExpanderColumn<Profile> column;

    static {
        LOG = Logger.getLogger(ExpanderColumnCell.class.getName());
    }

    public ExpanderColumnCell(TableRowExpanderColumn<Profile> column, JFXCheckBox selectAllCheckBox) {
        this.column = column;
        this.selectAllCheckBox = selectAllCheckBox;

        URL location = getClass().getResource("/views/ExpanderColumnButton.fxml");

        try {
            pane = FXMLLoader.load(location);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        if (pane != null) {
            toggleButton = (JFXToggleNode) pane.lookup("#toggleButton");
            rowCheckBox = (JFXCheckBox) pane.lookup("#checkBox");
            nameLabel = (Label) pane.lookup("#nameLabel");

            nameLabel.getStyleClass().add("profile-name-label");
            toggleButton.getStyleClass().add("profile-row-toggle");

            toggleButton.setOnAction(e -> column.toggleExpanded(getIndex()));
        }
    }

    public JFXCheckBox getRowCheckBox() {
        return rowCheckBox;
    }

    @Override
    protected void updateItem(Boolean expanded, boolean empty) {
        super.updateItem(expanded, empty);

        if (expanded == null || empty) {
            setGraphic(null);
        } else {
            toggleButton.setSelected(expanded);
            rowCheckBox.setSelected(selectAllCheckBox.isSelected());

            int index = getIndex();
            if (index > -1 && index < getTableView().getItems().size()) {
                Profile profile = getTableView().getItems().get(index);
                nameLabel.setText(profile.getProfileNameProperty().get());
            }

            setGraphic(pane);
        }
    }
}
