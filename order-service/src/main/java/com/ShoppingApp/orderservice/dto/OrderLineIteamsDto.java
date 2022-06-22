package com.ShoppingApp.orderservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineIteamsDto {
	
	private Long id;
	
	private String skuCode;
	
	private BigDecimal price;
	
	private Integer quantity;

}
