package com.tipre.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tipre.springrest.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	

}
