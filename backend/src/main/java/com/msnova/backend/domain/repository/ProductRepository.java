package com.msnova.backend.domain.repository;

import com.msnova.backend.domain.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(String id);

    List<Product> findAll();

    void deleteById(String id);
}