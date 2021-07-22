package com.tipre.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {
	@RequestMapping("/hola")
	public String hola() {
		return "<h1>Hola Spring Boot</h1>";
	}
}
