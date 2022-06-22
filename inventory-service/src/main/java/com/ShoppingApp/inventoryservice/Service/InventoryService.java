package com.ShoppingApp.inventoryservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingApp.inventoryservice.Repo.InventoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	
	@Autowired
	private final InventoryRepo inventoryRepo;
	
	@Transactional(readOnly = true)
	public boolean isInStock(String skuCode)
	{
		return inventoryRepo.findBySkuCode().isPresent();
	}

}
