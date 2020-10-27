package com.dicegame.demo.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicegame.demo.dto.User;
import com.dicegame.demo.service.RollServiceImpl;
import com.dicegame.demo.service.UserServiceImpl;



//Capa controller donde la ruta origen será .../api

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	RollServiceImpl rollServiceImpl;
	
	
	
	// CREATE user
	@PostMapping("/players")
	public User postUser(@RequestBody User user) {
		
		List<User> users;
		
		// Miramos el toda la lista de usuarios si hay alguien que se llame como el, si ya lo hay, no lo crearemos. En Mongo no dejamos que haya mas de un
		// usuario anonimo, ya que como seleccionamos los usuarios por nombre, puede crear problemas.
		
		users = userServiceImpl.selectAllUsers();
			
		if (users.stream().filter(x -> x.getName().toUpperCase().equals(user.getName().toUpperCase())).count() != 0) {
		
			System.out.println("No se puede crear el usuario, tiene el mismo nombre que otro ");
			return null;

		}else {
			return userServiceImpl.createUser(user);

		}	
			
	}
	
	
	//SELECT todos los users y su porcentage medio de éxito
	@GetMapping("/players")	
	public List<User> getAllUsers(){
		return userServiceImpl.selectAllUsers();
	}
	
	
	//SELECT un user por NAME
	@GetMapping("/players/{name}")
	public User getUserByName(@PathVariable(name="name") String name){
		return userServiceImpl.selectUserByName(name);
	}
	
	
	//SELECT ranking medio de todos los jugadores (partidas ganadas / partidas totales)
		@GetMapping("players/ranking")
		public String getRanking(){
			
			double total_rolls = rollServiceImpl.selectAllRolls().size();
			double total_win_rolls = rollServiceImpl.selectAllRolls().stream().filter( x -> x.getResult().equals("WIN")).count();
			
			double result = (total_win_rolls/total_rolls)*100;
			
			return "El ranking medio de exitos de todos los jugadores es: " + String.format("%.02f", result)+ "%";
		
		}
		
		//SELECT best player
			@GetMapping("players/ranking/winner")
		public User getBestUser(){
			
			return userServiceImpl.selectAllUsers().stream().max(Comparator.comparing(User::getPorcSuccess)).get();

		}

			
		//SELECT worst player
		@GetMapping("players/ranking/loser")
		public User getWorstUser(){
			 
			return userServiceImpl.selectAllUsers().stream().min(Comparator.comparing(User::getPorcSuccess)).get();

		}
	
	//UPDATE un user
	@PutMapping("/players/{name}")
	public User updateUser(@PathVariable(name="name") String name, @RequestBody User user) {
		//Guardamos el user que queremos actualizar
		User user_to_updt = userServiceImpl.selectUserByName(name);
		System.out.println("Se actualiza el user: " +user_to_updt);
		
		//Setteamos su nombre
		user_to_updt.setName(user.getName());
		
		// asignamos la actualización y retornamos el objeto actualizado
		user =  userServiceImpl.updateUser(user_to_updt);
		System.out.println("Por el user: " +user);
		
		return user;

	}
	
	
	//DELETE un user por NAME
	@DeleteMapping("/players/{name}")
	public void deleteUser(@PathVariable(name="name")String name) {
		
		System.out.println("Primero se borran las rolls del user:" +name);
		
		rollServiceImpl.deleteRollsUser(name);
		
		System.out.println("Se borra el user con nombre: " +name);

		userServiceImpl.deleteUser(name);
		
		System.out.println("USER BORRADO");
		
		

	}
	
}
