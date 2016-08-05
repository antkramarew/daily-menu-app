package com.demo.ui;

import com.demo.domain.Product;
import com.demo.repository.ProductRepository;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * Created by anton_kramarev on 8/4/2016.
 */
@SpringUI
public class VaadinUI extends UI {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductEditor productEditor;

    private final Button addNewBtn;

    private final Grid grid;
    private final TextField filter;


    public VaadinUI() {
        this.grid = new Grid();
        this.addNewBtn = new Button("New product", FontAwesome.PLUS);
        this.filter = new TextField();

    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid, productEditor);
        setContent(mainLayout);

        actions.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "name", "protein", "fat", "carbohydrate", "calories");

        filter.setInputPrompt("Filter by last name");
        filter.addTextChangeListener(e -> listProducts(e.getText()));

        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                productEditor.setVisible(false);
            }
            else {
                productEditor.editProduct((Product) grid.getSelectedRow());
            }
        });

        addNewBtn.addClickListener(e -> productEditor.editProduct(new Product("", 0,0,0,0)));
        productEditor.setChangeHandler(() -> {
            productEditor.setVisible(false);
            listProducts(filter.getValue());
        });
        listProducts(null);
    }

    private void listProducts(String text) {
        if (StringUtils.isEmpty(text)) {
            grid.setContainerDataSource(
                    new BeanItemContainer(Product.class, (Collection) productRepository.findAll()));
        }
        else {
            grid.setContainerDataSource(new BeanItemContainer(Product.class,
                    productRepository.findByNameStartsWithIgnoreCase(text)));
        }
    }


}
