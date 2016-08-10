package com.demo.ui.view;

/**
 * Created by toxa on 8/9/2016.
 */
public enum ViewType {

    PRODUCT_VIEW(ProductView.VIEW_NAME);

    private final String viewName;

    ViewType(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
