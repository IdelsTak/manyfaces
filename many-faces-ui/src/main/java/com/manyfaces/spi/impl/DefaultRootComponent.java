/*
 Copyright 2019.
 */
package com.manyfaces.spi.impl;

import com.manyfaces.spi.RootComponent;
import com.manyfaces.ui.Home;
import javafx.scene.layout.StackPane;
import org.openide.util.lookup.ServiceProvider;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
@ServiceProvider(service = RootComponent.class)
public class DefaultRootComponent implements RootComponent {

    private StackPane homePane;

    @Override
    public StackPane getRoot() {
        if (homePane == null) {
            homePane = new Home();
        }
        
        return homePane;
    }

}
