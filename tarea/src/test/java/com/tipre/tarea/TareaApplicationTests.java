package com.tipre.tarea;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
class TareaApplicationTests {
	
	@Autowired
	Suma suma;
	
	@Test
	void checkSuma() {
		assertEquals(suma.sumar(200, 200),400);
	}

}
