/*
 Copyright 2019.
 */
package com.manyfaces.spi;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public interface RootComponent {

    StackPane getRoot();

    void resetContent();

    void setContent(Node content);
}
