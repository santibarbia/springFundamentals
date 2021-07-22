package com.tipre.springrest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tipre.springrest.entities.Product;
import com.tipre.springrest.repository.ProductRepository;




@WebMvcTest
class ProductRestControllerMvcTest {

	private static final String PRODUCT_URL = "/api/product/";
	
	private static final String CREATE_URL = "/createProduct";
	
	private static final String UPDATE_URL = "/updateProduct";
	
	
	private static final String CONTEXT_URL = "/api";
	
	private static final Double PRICE = 1000.00;
	
	private static final String PRODUCT_DESCRIPTION = "Algo para describir";
	
	private static final String PRODUCT_NAME = "Santiago";
	
	private static final int PRODUCT_ID = 2;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductRepository repository;
	
	
	@Test
	void testFindAll() throws JsonProcessingException, Exception {
		
		Product product = buildProduct();
		List<Product> products = Arrays.asList(product);
		when(repository.findAll()).thenReturn(products);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(get(PRODUCT_URL).contextPath(CONTEXT_URL)).andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(product)));
		
		
	}
	
	@Test
	void testCreateProduct() throws JsonProcessingException, Exception {
		
		Product product = buildProduct();
		when(repository.save(any())).thenReturn(product);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(post(CREATE_URL).contentType(MediaType.APPLICATION_JSON)
				.content(objectWriter.writeValueAsString(product))).andExpect(status().isOk())
		.andExpect((ResultMatcher) content().json(objectWriter.writeValueAsString(product)));
		
		
	}
	
	@Test
	void testUpdateProduct() throws JsonProcessingException, Exception {
		
		Product product = buildProduct();
		product.setPrice((double) PRICE);
		when(repository.save(any())).thenReturn(product);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(put(UPDATE_URL).contentType(MediaType.APPLICATION_JSON)
				.content(objectWriter.writeValueAsString(product))).andExpect(status().isOk())
		.andExpect((ResultMatcher) content().json(objectWriter.writeValueAsString(product)));
		
		
	}
	
	@Test
	void testDelete() throws Exception {
		doNothing().when(repository).deleteById(PRODUCT_ID);
		
		mockMvc.perform(delete("/api/borrarProduct/"+PRODUCT_ID).contextPath(CONTEXT_URL)).andExpect(status().isOk());
	}
	
	private Product buildProduct(){
		Product product = new Product();
		product.setId(PRODUCT_ID);
		product.setName(PRODUCT_NAME);
		product.setDescription(PRODUCT_DESCRIPTION);
		product.setPrice(3500.000);
		return product;
	}


}
