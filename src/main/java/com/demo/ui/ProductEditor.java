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

/**
 * Created by anton_kramarev on 8/4/2016.
 */
@SpringComponent
@UIScope
public class ProductEditor extends VerticalLayout {

    @Autowired
    private ProductRepository repository;

    private Product product;

    TextField name = new TextField("Product name:");
    TextField protein = new TextField("Protein:");
    TextField fat = new TextField("Fat:");
    TextField carbohydrate = new TextField("Carbohydrate:");
    TextField calories = new TextField("Calories:");

    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);

    CssLayout actions = new CssLayout(save, cancel, delete);


    public ProductEditor() {
        addComponents(name, protein, fat, carbohydrate, calories, actions);
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        save.addClickListener(e -> repository.save(product));
        delete.setClickShortcut(ShortcutAction.KeyCode.DELETE );
        delete.addClickListener(e -> repository.delete(product));
        cancel.addClickListener(e -> this.setVisible(false));
        setVisible(false);
    }


    public interface ChangeHandler {

        void onChange();
    }

    public final void editProduct(Product p) {
        final boolean persisted = p.getId() != null;
        if (persisted) {
            product = repository.findOne(p.getId());
        }
        else {
            product = p;
        }
        cancel.setVisible(persisted);
        BeanFieldGroup.bindFieldsUnbuffered(product, this);
        setVisible(true);
        save.focus();
        name.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }
}
