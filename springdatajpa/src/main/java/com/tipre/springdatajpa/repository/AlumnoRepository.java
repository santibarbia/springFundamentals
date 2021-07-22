package com.tipre.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tipre.springdatajpa.entities.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

}
