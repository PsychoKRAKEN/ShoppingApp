package com.ShoppingApp.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ShoppingApp.inventoryservice.Repo.InventoryRepo;
import com.ShoppingApp.inventoryservice.model.Inventory;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo) {
		return args -> {
			Inventory inventory=new Inventory();
			inventory.setSkuCode("iphone 13");
			inventory.setQuantity(100);
			
			Inventory inventory1=new Inventory();
			inventory1.setSkuCode("iphone 13_red");
			inventory1.setQuantity(0);
			
			inventoryRepo.save(inventory);
			inventoryRepo.save(inventory1);
		};
	}

}
