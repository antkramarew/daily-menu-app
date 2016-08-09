package com.demo.ui;

import com.demo.ui.view.MyView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by toxa on 8/9/2016.
 */
public class MyNavigator extends Navigator {

    private List<MyView> views;

    public MyNavigator(List<MyView> views, ViewContainer container) {
        super(UI.getCurrent(), container);
        this.views = views;
        initProviders();
    }

    private void initProviders() {
        for (MyView view : views) {
            ViewProvider provider = new ClassBasedViewProvider(view.getName(), view.getClass());
            addProvider(provider);
        }
    }
}
