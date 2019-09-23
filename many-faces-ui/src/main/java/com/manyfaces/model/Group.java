/*
 Copyright 2019.
 */
package com.manyfaces.model;

import java.util.Objects;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class Group {

    private final SimpleStringProperty groupNameProperty;
    private final ReadOnlyIntegerWrapper idProperty;
//    private int id;
//    private String groupName;

//    public Group(String groupName) {
//        this.idProperty = new ReadOnlyIntegerWrapper();
//        this.groupNameProperty = new SimpleStringProperty(groupName);
//    }
    public Group(int id, String groupName) {
        this.idProperty = new ReadOnlyIntegerWrapper(id);
        this.groupNameProperty = new SimpleStringProperty(groupName);
    }

    public ReadOnlyIntegerWrapper getIdProperty() {
        return idProperty;
    }

    public SimpleStringProperty getGroupNameProperty() {
        return groupNameProperty;
    }

    @Override
    public int hashCode() {
        int id = getIdProperty().get();
        String groupName = getGroupNameProperty().get();
        int hash = 3;
        hash = 97 * hash + id;
        hash = 97 * hash + Objects.hashCode(groupName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        int id = getIdProperty().get();
        String groupName = getGroupNameProperty().get();
        final Group other = (Group) obj;
        if (id != other.getIdProperty().get()) {
            return false;
        }
        return Objects.equals(groupName, other.getGroupNameProperty().get());
    }

    @Override
    public String toString() {
        int id = getIdProperty().get();
        String name = getGroupNameProperty().get();
//        return "[" + id + ", " + name + "]";
        return name;
    }

}
