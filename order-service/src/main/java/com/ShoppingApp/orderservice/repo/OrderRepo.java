package com.ShoppingApp.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShoppingApp.orderservice.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
