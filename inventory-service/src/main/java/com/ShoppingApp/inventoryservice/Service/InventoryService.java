package com.ShoppingApp.inventoryservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingApp.inventoryservice.Repo.InventoryRepo;
import com.ShoppingApp.inventoryservice.dto.InventoryResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
	
	@Autowired
	private final InventoryRepo inventoryRepo;
	
	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode)
	{
		log.info("Checking Inventory");
		return inventoryRepo.findBySkuCodeIn(skuCode).stream()
				.map(inventory ->
                		InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build()
				).toList();
	}

}
