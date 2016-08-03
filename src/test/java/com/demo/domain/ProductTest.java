package com.demo.domain;

import com.demo.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by anton_kramarev on 8/3/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void shouldSaveAndRetrieveProduct() {
        Product product = new Product("Product", 100, 110, 300, 400);
        repository.save(product);
        Long id = product.getId();
        System.out.println(id);
        System.out.println(repository.findOne(id));
    }

}