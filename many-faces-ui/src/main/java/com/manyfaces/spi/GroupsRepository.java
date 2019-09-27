/*
 Copyright 2019.
 */
package com.manyfaces.spi;

import com.manyfaces.model.Group;
import java.util.Optional;
import javafx.collections.ObservableList;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public interface GroupsRepository {

    Group add(String groupName);

    void update(Group group);

    void updateWithPosition(int index, Group group);

    Optional<Group> findbyId(int id);

    Optional<Group> findByName(String name);

    ObservableList<Group> findAll();

    void delete(Group group);

}
