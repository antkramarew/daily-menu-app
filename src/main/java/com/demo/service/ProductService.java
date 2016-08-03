package com.demo.service;

import com.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anton_kramarev on 8/3/2016.
 */
@Component
public class ProductService {

    @Autowired
    private ProductRepository repository;


}
