package com.tipre.springrest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.*;

import com.tipre.springrest.entities.Product;

@SpringBootTest
class ProductrestapiApplicationTests {
	
	@Value("${productrestapi.services.url}")
	private String baseUrl;
	
	@Test
	void testGetProduct() {
		System.out.println(baseUrl);
	
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseUrl+"/2",Product.class);
		assertNotNull(product);
		assertNotEquals("", product.getName());
		
	}
	@Test
	void testPostProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = new Product();
		product.setName("Santi");
		product.setDescription("Algo para describir");
		product.setPrice(1258.0);
		Product newProduct = restTemplate.postForObject("http://localhost:8080/createProduct", product,Product.class);
		assertNotNull(newProduct);
		assertNotNull(newProduct.getId());
		assertEquals("Santi", newProduct.getName());
		
		
		
	}
	
	@Test
	void testPutProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseUrl+"/2",Product.class);
		product.setPrice(3500.0);
		
		restTemplate.put("http://localhost:8080/updateProduct", product);
		
		
	}

}
