/*
 Copyright 2019.
 */
package com.manyfaces.spi.impl;

import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import com.manyfaces.model.Group;
import com.manyfaces.spi.GroupsRepository;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = GroupsRepository.class)
public class DefaultGroupsRepository implements GroupsRepository {

    private static final Logger LOG;
    private final ObservableList<Group> groups;
    private int id;

    static {
        LOG = Logger.getLogger(DefaultGroupsRepository.class.getName());
    }

    public DefaultGroupsRepository() {
        this.groups = FXCollections.observableArrayList();
        //Bogus data
        Company company = new Faker().company();
        
        for (int i = 1; i < 11; i++) {
            groups.add(new Group(i, company.buzzword()));
        }

        groups.addListener((ListChangeListener.Change<? extends Group> change) -> {
            LOG.log(Level.INFO, "CHANGE OCCURED: {0}", change);
        });
    }

    @Override
    public void add(String groupName) {
        id += 1;
        groups.add(new Group(id, groupName));
    }

    @Override
    public void update(Group group) {
        groups.stream()
                .filter(g -> {
                    int anId = g.getIdProperty().get();
                    int groupId = group.getIdProperty().get();
                    return anId == groupId;
                })
                .findFirst()
                .ifPresent(g -> {
                    int index = groups.indexOf(g);
                    int ID = g.getIdProperty().get();
                    String newGroupName = group.getGroupNameProperty().get();

                    if (groups.remove(g)) {
                        groups.add(index, new Group(ID, newGroupName));
                    }
                });
    }

    @Override
    public Optional<Group> findbyId(int id) {
        return groups.stream()
                .filter(group -> group.getIdProperty().get() == id)
                .findFirst();
    }

    @Override
    public ObservableList<Group> findAll() {
        return FXCollections.unmodifiableObservableList(groups);
    }

    @Override
    public void delete(Group group) {
        groups.remove(group);
    }

}
