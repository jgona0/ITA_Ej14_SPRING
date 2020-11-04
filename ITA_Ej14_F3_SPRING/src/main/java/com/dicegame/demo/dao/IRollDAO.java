 package com.dicegame.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicegame.demo.dto.Roll;

// Interfaz que extiende del repositorio de JPA para usar sus funciones

public interface IRollDAO extends JpaRepository<Roll, Long>{

	public List<Roll> findByUserId(Long id_user); // SELECT rolls por id del user

}
