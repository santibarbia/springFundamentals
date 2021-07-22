package com.tipre.springdatajpa.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alumno {
	
	@Id
	private long id;
	private String nombre;
	private int testScore;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTestScore() {
		return testScore;
	}
	public void setTestScore(int testScore) {
		this.testScore = testScore;
	}
	
}
