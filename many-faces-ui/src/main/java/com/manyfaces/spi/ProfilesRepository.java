/*
 Copyright 2019.
 */
package com.manyfaces.spi;

import com.manyfaces.model.Profile;
import java.util.Optional;
import javafx.collections.ObservableSet;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public interface ProfilesRepository {

    void add(String profileName);

    void update(Profile profile);

    Optional<Profile> findbyId(String id);

    ObservableSet<Profile> findAll();

    void delete(Profile profile);
}
