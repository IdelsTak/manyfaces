/*
 Copyright 2019.
 */
package com.manyfaces.model;

import com.manyfaces.spi.ProfilesRepository;
import java.util.Objects;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import org.openide.util.Lookup;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class Group {

    private final SimpleStringProperty groupNameProperty;
    private final ReadOnlyIntegerWrapper idProperty;
    private final ObservableSet<Profile> profiles;

    public Group(int id, String groupName) {
        this.idProperty = new ReadOnlyIntegerWrapper(id);
        this.groupNameProperty = new SimpleStringProperty(groupName);
        this.profiles = FXCollections.emptyObservableSet();
    }

    public ReadOnlyIntegerWrapper idProperty() {
        return idProperty;
    }

    public SimpleStringProperty groupNameProperty() {
        return groupNameProperty;
    }

    public int getId() {
        return idProperty.get();
    }

    public String getName() {
        return groupNameProperty.get();
    }

    public void setName(String groupName) {
        groupNameProperty.set(groupName);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + getId();
        hash = 97 * hash + Objects.hashCode(getName());
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
        final Group other = (Group) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return Objects.equals(getName(), other.getName());
    }

    @Override
    public String toString() {
        return getName() + " (" + getNumberOfRelatedProfiles() + ")";
    }

    private int getNumberOfRelatedProfiles() {
        long count = Lookup.getDefault().lookup(ProfilesRepository.class).findAll()
                .stream()
                .filter(profile -> profile.getGroup().equals(this))
                .count();

        return Integer.valueOf(Long.toString(count)).intValue();
    }

}
