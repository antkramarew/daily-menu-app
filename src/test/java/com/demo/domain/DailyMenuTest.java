package com.demo.domain;

import com.demo.repository.DailyMenuRepository;
import com.demo.repository.DishRepository;
import com.demo.repository.ProductItemRepository;
import com.demo.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by anton_kramarev on 8/3/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DailyMenuTest {


    @Autowired
    private DailyMenuRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private ProductItemRepository productItemRepository;


    @Test
    public void shouldSaveAndRetrieveDailyMenu() {
        Product product = new Product("Product", 100, 110, 300, 400);
        productRepository.save(product);
        ProductItem item = new ProductItem();
        item.setProduct(product);
        item.setAmount(200);
        productItemRepository.save(item);
        Dish dish = new Dish();
        dish.add(item);
        dishRepository.save(dish);
        DailyMenu menu = new DailyMenu();
        menu.setDate(new Date());
        menu.add(dish);
        menu.add(item);
        repository.save(menu);
        System.out.println("Menu id: "+ menu.getId() + "\n" + repository.findOne(menu.getId()));
        System.out.println("Menu totals: " + "\n" +
                "Total protein: " + menu.getTotalProtein());
    }

}