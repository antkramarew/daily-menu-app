package com.demo.ui;

import com.demo.ui.view.MyView;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by anton_kramarev on 8/9/2016.
 */
@SpringComponent
public class DashboardMenu extends CustomComponent {
    public static final String ID = "dashboard-menu";
    private static final String STYLE_VISIBLE = "valo-menu-visible";


    @Autowired
    private List<MyView> views;

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

        for (final MyView view : views) {
            Component menuItemComponent = new ValoMenuItemButton(view);

            menuItemsLayout.addComponent(menuItemComponent);
        }
        return menuItemsLayout;

    }


    @Override
    public void attach() {
        super.attach();
    }

    public final class ValoMenuItemButton extends Button {

        private static final String STYLE_SELECTED = "selected";

        private final MyView view;

        public ValoMenuItemButton(final MyView view) {
            this.view = view;
            setPrimaryStyleName("valo-menu-item");
            setCaption(view.getName());
            addClickListener(event -> UI.getCurrent().getNavigator()
                    .navigateTo(view.getName()));

        }


    }

    @PostConstruct
    public void init() {
        setCompositionRoot(buildContent());
    }
}
