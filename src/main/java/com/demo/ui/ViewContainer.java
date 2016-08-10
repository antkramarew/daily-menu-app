package com.demo.ui;

import com.vaadin.spring.annotation.SpringComponent;
import org.vaadin.viritin.layouts.MCssLayout;

/**
 * Created by toxa on 8/9/2016.
 */

public class ViewContainer extends MCssLayout {

    public ViewContainer() {
        addStyleName("view-content");
        setSizeFull();
    }
}
