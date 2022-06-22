package com.ShoppingApp.productservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.ShoppingApp.productservice.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	
	@Container
	static OracleContainer oracleContainer=new OracleContainer("oracleinanutshell/oracle-xe-10g:10.2.0").withUsername("system").withPassword("1234");
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateProduct() throws Exception {
		
		ProductRequest productRequest= getProductRequest();
		
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productRequestString))
				.andExpect(status().isCreated());
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("iphone 13")
				.description("iphone13 Gold")
				.price(BigDecimal.valueOf(1200))
				.build();
		
	}

}
