package com.demo.repository;

import com.demo.domain.ProductItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anton_kramarev on 8/3/2016.
 */
public interface ProductItemRepository extends CrudRepository<ProductItem, Long> {
}
