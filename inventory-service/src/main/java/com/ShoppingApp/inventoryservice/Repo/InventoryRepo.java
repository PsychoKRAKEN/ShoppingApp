package com.ShoppingApp.inventoryservice.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShoppingApp.inventoryservice.model.Inventory;


public interface InventoryRepo extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findBySkuCode();

}
