package com.demo.ui;

import com.demo.domain.Product;
import com.demo.repository.ProductRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MCssLayout;

import java.util.Collection;

/**
 * Created by anton_kramarev on 8/4/2016.
 */
@Theme("valo")
@SpringUI
public class VaadinUI extends UI {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductForm productForm;

    private final MTable<Product> table = new MTable(Product.class)
            .withProperties("id", "name", "protein", "fat", "carbohydrate", "calories")
            .withColumnHeaders("Number", "Name", "Protein", "Fat", "Carbohydrate", "Calories")
            .withFullWidth();

    private final MButton add = new MButton(FontAwesome.PLUS,
            event -> {
                productForm.setEntity(new Product());
                productForm.openInModalPopup();
            });

    private final MButton delete = new ConfirmButton(FontAwesome.TRASH_O,"Are you sure?",
            event -> {
                productRepository.delete(table.getValue().getId());
            });

    private final MButton edit = new MButton(FontAwesome.PENCIL_SQUARE_O,
            event -> {
                productForm.setEntity(productRepository.findOne(table.getValue().getId()));
                productForm.openInModalPopup();
            });

    private final MCssLayout actions = new MCssLayout(add, edit, delete)
            .withStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

    private final TextField filter;


    public VaadinUI() {
        this.filter = new TextField();
    }



    @Override
    protected void init(VaadinRequest vaadinRequest) {


        HorizontalLayout toolbar = new HorizontalLayout(filter, actions);
        VerticalLayout productsLayout = new VerticalLayout(toolbar, table);
        table.setBeans((Collection<Product>) productRepository.findAll());
        table.setSelectable(true);
        toolbar.setSpacing(true);
        productsLayout.setMargin(true);
        productsLayout.setSpacing(true);
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();
        tabSheet.addTab(productsLayout, "Products");
        setContent(tabSheet);

        filter.setInputPrompt("Filter by name");
        filter.addTextChangeListener(e -> listProducts(e.getText()));

        productForm.setChangeHandler(() -> {
            productForm.setVisible(false);
            listProducts(filter.getValue());
        });
        listProducts(null);
    }

    private void listProducts(String text) {
        if (StringUtils.isEmpty(text)) {
            table.setBeans((Collection) productRepository.findAll());
        }
        else {
            table.setBeans(productRepository.findByNameStartsWithIgnoreCase(text));
        }
        applyButtonStates();
    }

    private void applyButtonStates() {
        boolean isSelected = table.getValue() != null;
        edit.setEnabled(isSelected);
        delete.setEnabled(isSelected);
    }


}
