package com.dicegame.demo.service;

import java.util.List;

import com.dicegame.demo.dto.User;

public interface IUserService {

	//Metodos del CRUD
	
	public User createUser(User user);	//Crea un user

	public List<User> selectAllUsers(); //Seleccionar todos los users 
	
	public User selectUserById (Long id); //Seleccionar un user por ID
	
	public User updateUser (User user); //Hacer update de un user
	
	public void deleteUser (Long id); //Borrar un user por ID
	
}
