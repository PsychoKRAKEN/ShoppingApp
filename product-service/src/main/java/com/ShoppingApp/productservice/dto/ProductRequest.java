package com.ShoppingApp.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class ProductRequest {
	
	private String name;
	private String description;
	private BigDecimal price;


}
