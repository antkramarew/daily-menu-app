package com.demo.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by anton_kramarev on 8/9/2016.
 */
@Theme("valo")
@SpringUI
public class MyUI extends UI {

    @Autowired
    private SpringViewProvider viewProvider;



    @Override
    protected void init(VaadinRequest request) {

        addStyleName(ValoTheme.UI_WITH_MENU);
        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();
        mainLayout.addStyleName("mainview");
        ViewContainer container = new ViewContainer();
        DashboardMenu menu = new DashboardMenu();
        mainLayout.addComponent(menu);
        mainLayout.addComponent(container);
        mainLayout.setExpandRatio(container, 1.0f);
        setContent(mainLayout);


        Navigator navigator = new Navigator(this, container );
        navigator.addProvider(viewProvider);
    }
}
