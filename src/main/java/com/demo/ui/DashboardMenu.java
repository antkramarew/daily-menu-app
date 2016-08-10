package com.demo.ui;

import com.demo.ui.view.ViewType;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by anton_kramarev on 8/9/2016.
 */

public class DashboardMenu extends CustomComponent {
    public static final String ID = "dashboard-menu";
    private static final String STYLE_VISIBLE = "valo-menu-visible";

    public DashboardMenu() {
        setPrimaryStyleName("valo-menu");
        setId(ID);
        setSizeUndefined();

    }

    private Component buildContent() {
        final CssLayout menuContent = new CssLayout();
        menuContent.addStyleName("sidebar");
        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.addStyleName("no-vertical-drag-hints");
        menuContent.addStyleName("no-horizontal-drag-hints");
        menuContent.setWidth(null);
        menuContent.setHeight("100%");

        menuContent.addComponent(buildTitle());
        menuContent.addComponent(buildToggleButton());
        menuContent.addComponent(buildMenuItems());

        return menuContent;
    }

    private Component buildTitle() {
        Label logo = new Label("QuickTickets <strong>Dashboard</strong>",
                ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        return logoWrapper;
    }





    private Component buildToggleButton() {
        Button valoMenuToggleButton = new Button("Menu", new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                if (getCompositionRoot().getStyleName().contains(STYLE_VISIBLE)) {
                    getCompositionRoot().removeStyleName(STYLE_VISIBLE);
                } else {
                    getCompositionRoot().addStyleName(STYLE_VISIBLE);
                }
            }
        });
        valoMenuToggleButton.setIcon(FontAwesome.LIST);
        valoMenuToggleButton.addStyleName("valo-menu-toggle");
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
        return valoMenuToggleButton;
    }

    private Component buildMenuItems() {
        CssLayout menuItemsLayout = new CssLayout();
        menuItemsLayout.addStyleName("valo-menuitems");

        for (ViewType view : ViewType.values()) {
            Component menuItemComponent = new ValoMenuItemButton(view.getViewName());

            menuItemsLayout.addComponent(menuItemComponent);
        }
        return menuItemsLayout;

    }


    @Override
    public void attach() {
        super.attach();
    }

    public final class ValoMenuItemButton extends Button {



        public ValoMenuItemButton(String viewName) {
            setPrimaryStyleName("valo-menu-item");
            setCaption(viewName);
            addClickListener(event -> UI.getCurrent().getNavigator()
                    .navigateTo(viewName));

        }


    }

    @PostConstruct
    public void init() {
        setCompositionRoot(buildContent());
    }
}
