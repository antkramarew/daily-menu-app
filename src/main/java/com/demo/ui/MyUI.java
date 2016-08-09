package com.demo.ui;

import com.demo.ui.view.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by anton_kramarev on 8/9/2016.
 */
@Theme("valo")
@SpringUI
@RequestMapping("/ui")
public class MyUI extends UI {

    @Autowired
    private MainView mainView;
    @Override
    protected void init(VaadinRequest request) {

        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(mainView);
        getNavigator().navigateTo(getNavigator().getState());

    }
}
