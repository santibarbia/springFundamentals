package com.tipre.springrest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tipre.springrest.entities.Product;
import com.tipre.springrest.repository.ProductRepository;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProductRestController {
	
	@Autowired
	ProductRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	
	
	
	
	@ApiOperation(value = "Devuelve una lista de todos los productos",
			notes = "Lista de productos",
			response = Product.class,
			responseContainer = "List",
			produces = "application/json")
	@RequestMapping(value = "/product/",method = RequestMethod.GET)
	public List<Product> getProduct(){
		return repository.findAll();
	}
	
	
	@GetMapping("/product/{id}")
	@Transactional(readOnly = true)
	@Cacheable("product-cache")
	public Product obtenerProduct(@PathVariable int id) {
		LOGGER.info("Buscando producto por id "+id);
		return repository.findById(id).get();
	}
	
	
	@PostMapping("/createProduct")
	public Product createProduct(@Valid @RequestBody Product product) {
		
		
		
		return repository.save(product);
	}
	
	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product product) {
		
		return repository.save(product); //Hibernet si detecta que el producto existe(Verifica si el id existe), en caso de existir, actualiza el dato.
	}
	
	@DeleteMapping("/borrarProduct/{id}")
	@CacheEvict("product-cache")
	public void deleteProduct(@PathVariable int id) {
		repository.deleteById(id);
		
	}
	
	
	
}
