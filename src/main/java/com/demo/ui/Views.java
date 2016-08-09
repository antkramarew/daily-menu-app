package com.demo.ui;

import com.demo.ui.view.ProductView;
import com.vaadin.server.FontAwesome;

/**
 * Created by toxa on 8/9/2016.
 */
public enum Views {

    PRODUCT("products", ProductView.class, FontAwesome.BARCODE);

    private String name;
    private Class clazz;
    private FontAwesome icon;

    Views(String name, Class<ProductView> clazz, FontAwesome icon) {
        this.name = name;
        this.clazz = clazz;
        this.icon = icon;
    }

    public Views getViewByName(String name) {
        for (Views view : Views.values()) {
            if (view.getName().equals(name)) {
                return view;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public Class getClazz() {
        return clazz;
    }

    public FontAwesome getIcon() {
        return icon;
    }
}
