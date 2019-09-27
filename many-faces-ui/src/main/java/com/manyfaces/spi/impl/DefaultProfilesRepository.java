/*
 Copyright 2019.
 */
package com.manyfaces.spi.impl;

import com.github.javafaker.Ancient;
import com.github.javafaker.Faker;
import com.manyfaces.model.Group;
import com.manyfaces.model.Profile;
import com.manyfaces.spi.GroupsRepository;
import com.manyfaces.spi.ProfilesRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = ProfilesRepository.class)
public class DefaultProfilesRepository implements ProfilesRepository {

    private static final Logger LOG;
    private static final Lookup LOOKUP = Lookup.getDefault();
    private final ObservableSet<Profile> profiles;

    static {
        LOG = Logger.getLogger(DefaultProfilesRepository.class.getName());
    }

    /**
     Default constructor.
     */
    public DefaultProfilesRepository() {
        this.profiles = FXCollections.observableSet(new HashSet<>());

        profiles.addListener((SetChangeListener.Change<? extends Profile> change) -> {
            LOG.log(Level.INFO, "Profiles list change occured: {0}", change);
        });
        
        //Add bogus data
        Ancient ancient = new Faker().ancient();

        for (int i = 0; i < 10; i++) {
            add(ancient.titan());
        }
    }

    @Override
    public void add(String name) {
        String message = "Profile name should not be null nor empty";
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }

        GroupsRepository groupsRepository = LOOKUP.lookup(GroupsRepository.class);
        Group group = groupsRepository.findByName("Unassigned")
                .orElseGet(() -> groupsRepository.add("Unassigned"));

        profiles.add(new Profile(name, group));
    }

    @Override
    public void update(Profile profile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Profile> findbyId(String id) {
        return profiles.stream()
                .filter(profile -> (profile.getId() == null
                                    ? id == null
                                    : profile.getId().equals(id)))
                .findFirst();
    }

    @Override
    public ObservableSet<Profile> findAll() {
        return FXCollections.unmodifiableObservableSet(profiles);
    }

    @Override
    public void delete(Profile profile) {
        profiles.remove(profile);
    }

}
