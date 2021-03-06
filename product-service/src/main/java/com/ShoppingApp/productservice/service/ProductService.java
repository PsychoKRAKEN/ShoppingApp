package com.ShoppingApp.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppingApp.productservice.dto.ProductRequest;
import com.ShoppingApp.productservice.dto.ProductResponse;
import com.ShoppingApp.productservice.model.Product;
import com.ShoppingApp.productservice.repo.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	@Autowired
	private final ProductRepo productRepo;
	
	public void createProduct(ProductRequest productRequest) {
		Product product=Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		
		
		productRepo.save(product);
		log.info("Product {} is saved",product.getId());
		
	}
	
	public List<ProductResponse> getAllProduct(){
		
		List<Product> products=productRepo.findAll();
		
		return products.stream().map(this::mapToProductResponse).toList();
		
	}
	
	private ProductResponse mapToProductResponse(Product product)
	{
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

}
