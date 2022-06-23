package com.ShoppingApp.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.ShoppingApp.orderservice.dto.InventoryResponse;
import com.ShoppingApp.orderservice.dto.OrderLineIteamsDto;
import com.ShoppingApp.orderservice.dto.OrderRequest;
import com.ShoppingApp.orderservice.model.Order;
import com.ShoppingApp.orderservice.model.OrderLineIteams;
import com.ShoppingApp.orderservice.repo.OrderRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
	
	@Autowired
	private final OrderRepo orderRepo;
	
	@Autowired
	private final WebClient.Builder webClientBuilder;
	
	public void placeOrder(OrderRequest orderRequest)
	{
		Order order=new Order();
		
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineIteams> orderLineIteams=orderRequest.getOrderLineItemsDtoList()
														.stream()
														.map(this::mapToDto)
														.toList();
		
		order.setOrderLineItemsList(orderLineIteams);
		
		List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineIteams::getSkuCode).toList();
		
		//Call Inventory Service if product is in stock
		
		InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
		 
		 boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
		
		
		if(allProductsInStock) {
			orderRepo.save(order);
		}else{
			throw new IllegalArgumentException("Product is not in stock, Please try again later");
		}
		
		
	}

	private OrderLineIteams mapToDto(OrderLineIteamsDto orderLineIteamsDto) {
		
		OrderLineIteams orderLineIteams=new OrderLineIteams();
		
		orderLineIteams.setPrice(orderLineIteamsDto.getPrice());
		orderLineIteams.setQuantity(orderLineIteamsDto.getQuantity());
		orderLineIteams.setSkuCode(orderLineIteamsDto.getSkuCode());
		
		return orderLineIteams;
	}

}
