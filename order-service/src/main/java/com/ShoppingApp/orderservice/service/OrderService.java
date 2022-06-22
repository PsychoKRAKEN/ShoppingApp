package com.ShoppingApp.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingApp.orderservice.dto.OrderLineIteamsDto;
import com.ShoppingApp.orderservice.dto.OrderRequest;
import com.ShoppingApp.orderservice.model.Order;
import com.ShoppingApp.orderservice.model.OrderLineIteams;
import com.ShoppingApp.orderservice.repo.OrderRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	@Autowired
	private final OrderRepo orderRepo;
	
	public void placeOrder(OrderRequest orderRequest)
	{
		Order order=new Order();
		
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineIteams> orderLineIteams=orderRequest.getOrderLineItemsDtoList()
														.stream()
														.map(this::mapToDto)
														.toList();
		
		order.setOrderLineItemsList(orderLineIteams);
		
		orderRepo.save(order);
	}

	private OrderLineIteams mapToDto(OrderLineIteamsDto orderLineIteamsDto) {
		
		OrderLineIteams orderLineIteams=new OrderLineIteams();
		
		orderLineIteams.setPrice(orderLineIteamsDto.getPrice());
		orderLineIteams.setQuantity(orderLineIteamsDto.getQuantity());
		orderLineIteams.setSkuCode(orderLineIteamsDto.getSkuCode());
		
		return orderLineIteams;
	}

}
