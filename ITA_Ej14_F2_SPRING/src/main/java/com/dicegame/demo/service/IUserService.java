package com.dicegame.demo.service;

import java.util.List;

import com.dicegame.demo.dto.User;

public interface IUserService {

	//Metodos del CRUD
	
	public User createUser(User user);	//Crea un user

	public List<User> selectAllUsers(); //Seleccionar todos los users 
	
	public User selectUserByName (String name); //Seleccionar un user por NAME
	
	public User updateUser (User user); //Hacer update de un user
	
	public void deleteUser (String name); //Borrar un user por NAME
	
}
