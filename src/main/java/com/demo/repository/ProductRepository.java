package com.demo.repository;

import com.demo.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by anton_kramarev on 8/3/2016.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    public List<Product> findByNameStartsWithIgnoreCase(String name);

}
