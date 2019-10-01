/*
 Copyright 2019.
 */
package com.manyfaces.spi.impl;

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
//        Company company = new Faker().company();
//
//        for (int i = 1; i < 11; i++) {
//            groups.add(new Group(i, company.buzzword()));
//        }

        groups.addListener((ListChangeListener.Change<? extends Group> change) -> {
            LOG.log(Level.INFO, "Groups list change occured: {0}", change);
        });
    }

    @Override
    public Group add(String groupName) {
        LOG.log(Level.INFO, "Adding group with name: {0}", groupName);
        
        id += 1;
        Group newGroup = new Group(id, groupName);
        groups.add(newGroup);
        return newGroup;
    }

    @Override
    public void update(Group group) {
        groups.stream()
                .filter(g -> g.getId() == group.getId())
                .findFirst()
                .ifPresent(g -> {
                    int thisId = g.getId();
                    int thisIdx = groups.indexOf(g);
                    String otherName = group.getName();

                    if (groups.remove(g)) {
                        groups.add(thisIdx, new Group(thisId, otherName));
                    }
                });
    }

    @Override
    public void updateWithPosition(int index, Group group) {
        groups.stream()
                .filter(g -> g.getId() == group.getId())
                .findFirst()
                .ifPresent(g -> {
                    if (groups.remove(g)) {
                        groups.add(index, new Group(g.getId(), group.getName()));
                    }
                });
    }

    @Override
    public Optional<Group> findbyId(int id) {
        return groups.stream()
                .filter(g -> g.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<Group> findByName(String name) {
        return groups.stream()
                .filter(group -> name == null 
                                 ? group.getName() == null 
                                 : group.getName().equals(name))
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
