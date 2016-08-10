package com.demo.ui.view;

import com.demo.domain.Product;
import com.demo.repository.ProductRepository;
import com.demo.ui.ProductForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MCssLayout;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Created by toxa on 8/9/2016.
 */

@SpringView(name = ProductView.VIEW_NAME)
public class ProductView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "product-view";
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductForm productForm;

    private String name = "products";

    private final MTable<Product> table = new MTable(Product.class)
            .withProperties("id", "name", "protein", "fat", "carbohydrate", "calories")
            .withColumnHeaders("Number", "Name", "Protein", "Fat", "Carbohydrate", "Calories")
            .withFullWidth();

    private final MButton add = new MButton(FontAwesome.PLUS,
            event -> {
                productForm.setEntity(new Product());
                productForm.openInModalPopup();
            });

    private final ConfirmButton delete = new ConfirmButton(FontAwesome.TRASH_O,"Are you sure?",
            event -> {
                productRepository.delete(table.getValue().getId());
                table.setSelected(null);
                listProducts(null);
            });

    private final MButton edit = new MButton(FontAwesome.PENCIL_SQUARE_O,
            event -> {
                productForm.setEntity(productRepository.findOne(table.getValue().getId()));
                productForm.openInModalPopup();
            });

    private final MCssLayout actions = new MCssLayout(add, edit, delete)
            .withStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

    private final MTextField filter = new MTextField()
            .withInputPrompt("Filter by name")
            .withTextChangeListener(e -> {
                table.setSelected(null);
                listProducts(e.getText());
            });


    public ProductView() {
        HorizontalLayout toolbar = new HorizontalLayout(filter, actions);
        addComponents(toolbar, table);
        setMargin(true);
        setSpacing(true);
        table.setSelectable(true);
        table.addMValueChangeListener(e -> {
            applyButtonStates();
        });
        toolbar.setSpacing(true);
    }


    @PostConstruct
    public void init() {
        productForm.setChangeHandler(() -> {
            listProducts(null);
        });
    }
    public void listProducts(String text) {
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        listProducts(null);
    }

    public String getName() {
        return name;
    }
}
