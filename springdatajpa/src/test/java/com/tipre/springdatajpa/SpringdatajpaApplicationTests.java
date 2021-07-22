package com.tipre.springdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipre.springdatajpa.entities.Alumno;
import com.tipre.springdatajpa.repository.AlumnoRepository;
import static org.junit.Assert.*;

@SpringBootTest
class SpringdatajpaApplicationTests {
	
	@Autowired
	private AlumnoRepository repository;
	
	@Test
	void testSaveAlumno() {
		
		Alumno alumno = new Alumno();
		alumno.setId(1l);
		alumno.setNombre("Santiago");
		alumno.setTestScore(100);
		repository.save(alumno);
		
		repository.findById(1l).get();
		
		Alumno saveAlumno = repository.findById(1l).get();
		assertNotNull(saveAlumno);
		
	}

}
