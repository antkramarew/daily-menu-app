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
@SpringComponent
public class MyNavigator extends Navigator {

    @Autowired
    private List<MyView> views;

    @Autowired
    public MyNavigator(ViewContainer container) {
        super(UI.getCurrent(), container);
        initProviders();
    }

    private void initProviders() {
        for (MyView view : views) {
            ViewProvider provider = new ClassBasedViewProvider(view.getName(), view.getClass());
            addProvider(provider);
        }
    }
}
