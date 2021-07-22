package com.tipre.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipre.core.services.PaymentService;
import com.tipre.core.services.PaymentServiceImpl;

import static org.junit.Assert.*;

@SpringBootTest
class CoreApplicationTests {
	
	@Autowired
	PaymentServiceImpl service;
	
	@Test
	void testDependencyInjection() {
		assertNotNull(service.getDao());
	}

}
