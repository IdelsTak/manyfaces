/*
 Copyright 2019.
 */
package com.manyfaces.model;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class Profile {

    private final SimpleStringProperty profileName;
    private final SimpleStringProperty status;
    private final SimpleStringProperty members;
    private final SimpleObjectProperty<LocalDateTime> lastEdited;

    public Profile(String profileName, String status, String members, LocalDateTime lastEdited) {
        this.profileName = new SimpleStringProperty(profileName);
        this.status = new SimpleStringProperty(status);
        this.members = new SimpleStringProperty(members);
        this.lastEdited = new SimpleObjectProperty<>(lastEdited);
    }

    public SimpleStringProperty getProfileNameProperty() {
        return profileName;
    }

    public SimpleStringProperty getStatusProperty() {
        return status;
    }

    public SimpleStringProperty getMembersProperty() {
        return members;
    }

    public SimpleObjectProperty<LocalDateTime> getLastEditedProperty() {
        return lastEdited;
    }
    
    
}
