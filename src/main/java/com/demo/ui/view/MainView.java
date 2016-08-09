package com.demo.ui.view;

import com.demo.ui.DashboardMenu;
import com.demo.ui.MyNavigator;
import com.demo.ui.ViewContainer;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by anton_kramarev on 8/9/2016.
 */

@SpringComponent
public class MainView extends HorizontalLayout {

    @Autowired
    private DashboardMenu menu;
    @Autowired
    private ViewContainer container;
    @Autowired
    private List<MyView> views;

    public MainView() {
        setSizeFull();
        addStyleName("mainview");



    }

    public DashboardMenu getMenu() {
        return menu;
    }

    public void setMenu(DashboardMenu menu) {
        this.menu = menu;
    }

    public ViewContainer getContainer() {
        return container;
    }

    public void setContainer(ViewContainer container) {
        this.container = container;
    }

    @PostConstruct
    public void init() {
        addComponent(menu);
        addComponent(container);
        setExpandRatio(container, 1.0f);
        new MyNavigator(views, container);
    }
}
