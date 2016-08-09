package com.demo.ui;

import com.demo.domain.Product;
import com.demo.repository.ProductRepository;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.MBeanFieldGroup;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by anton_kramarev on 8/4/2016.
 */
@SpringComponent
@UIScope
public class ProductForm extends AbstractForm<Product> {

    @Autowired
    private ProductRepository repository;

    private Product product;

    private final MTextField name = new MTextField("Product name:");
    private final MTextField protein = new MTextField("Protein:");
    private final MTextField fat = new MTextField("Fat:");
    private final MTextField carbohydrate = new MTextField("Carbohydrate:");
    private final MTextField calories = new MTextField("Calories:");

    private final MButton save = new MButton(FontAwesome.SAVE,"Save",e -> {
        repository.save(product);
        closePopup();
    }).withClickShortcut(ShortcutAction.KeyCode.ENTER)
            .withStyleName(ValoTheme.BUTTON_PRIMARY);

    private final MButton cancel = new MButton("Cancel", event -> {
        closePopup();
    });

    private final CssLayout actions = new MCssLayout(save, cancel).withStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
    private  MFormLayout formLayout;

    @Override
    protected Component createContent() {

        formLayout = new MFormLayout(this.name,
                this.protein,
                this.fat,
                this.carbohydrate,
                this.calories,
                this.actions).withWidth("");
        MVerticalLayout verticalLayout = new MVerticalLayout(formLayout, getToolbar()).withWidth("");
        return verticalLayout;
    }


    public interface ChangeHandler {

        void onChange();
    }



    @Override
    public MBeanFieldGroup<Product> setEntity(Product entity) {
        this.product = entity;
        return super.setEntity(entity);
    }

    public void setChangeHandler(ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
    }

    @PostConstruct
    public void log() {
        System.out.println("ProductForm created");
    }

}
