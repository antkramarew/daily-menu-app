package com.demo.domain;

import com.demo.repository.ProductItemRepository;
import com.demo.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by anton_kramarev on 8/3/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductItemTest {

    @Autowired
    private ProductItemRepository repository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void shouldSaveAndRetrieveProductItem() {
        Product product = new Product("Product", 100, 110, 300, 400);
        productRepository.save(product);
        ProductItem item = new ProductItem();
        item.setProduct(product);
        item.setAmount(200);
        repository.save(item);
        Long id = item.getId();
        System.out.println(id);
        System.out.println(repository.findOne(id));

    }

}