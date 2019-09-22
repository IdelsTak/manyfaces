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

    void add(String groupName);

    void update(Group group);

    void updateWithPosition(int index, Group group);

    Optional<Group> findbyId(int id);

    ObservableList<Group> findAll();

    void delete(Group group);

}
