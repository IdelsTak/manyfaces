/*
 Copyright 2019.
 */
package com.manyfaces.spi;

import java.util.Collection;
import org.openide.util.Lookup;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public interface Registry extends Lookup.Provider{

    void add(Object instance);

    void remove(Object instance);

    void setAll(Collection<?> instances);

    void setAll(Object... instances);

    void clear();

}
