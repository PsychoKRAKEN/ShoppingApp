package com.ShoppingApp.productservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShoppingApp.productservice.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
