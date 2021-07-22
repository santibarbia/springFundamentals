package com.tipre.springboot.thymeleaf.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tipre.springboot.thymeleaf.model.Estudiante;

@Controller
public class HolaController {
	
	
	@RequestMapping("/hola")
	public String hola() {
		return "hola";
	}
	
	
	@RequestMapping("/sendData")
	public ModelAndView sendData() {
		ModelAndView mav = new ModelAndView("data");
		mav.addObject("mensaje", "Aca hay una idea");
		return mav;
	}
	
	
	@RequestMapping("/estudiante")
	public ModelAndView getEstudiante() {
		ModelAndView mav = new ModelAndView("estudiante");
		Estudiante estudiante = new Estudiante();
		estudiante.setName("Santiago");
		estudiante.setScore(325);
		mav.addObject("estudiante", estudiante);
		
		return mav;
	}
	
	@RequestMapping("/estudiantes")
	public ModelAndView getEstudiantes() {
		ModelAndView mav = new ModelAndView("estudiantes");
		Estudiante estudiante = new Estudiante();
		estudiante.setName("Santiago");
		estudiante.setScore(325);
		
		Estudiante estudiante2 = new Estudiante();
		estudiante2.setName("Juan");
		estudiante2.setScore(725);
		
		List<Estudiante> estudiantes = Arrays.asList(estudiante,estudiante2);
		mav.addObject("estudiantes", estudiantes);
		
		return mav;
	}


}
