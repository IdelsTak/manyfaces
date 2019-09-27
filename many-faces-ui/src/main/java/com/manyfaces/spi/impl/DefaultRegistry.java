/*
 Copyright 2019.
 */
package com.manyfaces.spi.impl;

import com.manyfaces.spi.Registry;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = Registry.class)
public class DefaultRegistry implements Registry {

    private final Lookup lookup;
    private final InstanceContent instanceContent;

    /**
     Default constructor.
     */
    public DefaultRegistry() {
        instanceContent = new InstanceContent();
        lookup = new AbstractLookup(instanceContent);
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }

    @Override
    public void add(Object instance) {
        instanceContent.add(instance);
    }

    @Override
    public void remove(Object instance) {
        instanceContent.remove(instance);
    }

    @Override
    public void setAll(Collection<?> instances) {
        instanceContent.set(instances, null);
    }

    @Override
    public void setAll(Object... instances) {
        instanceContent.set(Arrays.asList(instances), null);
    }

    @Override
    public void clear() {
        instanceContent.set(Collections.emptyList(), null);
    }

}
