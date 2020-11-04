package com.dicegame.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicegame.demo.dto.User;


//Interfaz que extiende del repositorio de JPA para usar sus funciones

public interface IUserDAO extends JpaRepository<User, Long> {

}
