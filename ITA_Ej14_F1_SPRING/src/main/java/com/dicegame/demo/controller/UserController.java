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
		return userServiceImpl.createUser(user);
	}
	
	
	//SELECT todos los users y su porcentage medio de éxito
	@GetMapping("/players")	
	public List<User> getAllUsers(){
		return userServiceImpl.selectAllUsers();
	}
	
	
	//SELECT un user por id
	@GetMapping("/players/{id}")
	public User getUserById(@PathVariable(name="id") Long id){
		return userServiceImpl.selectUserById(id);
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
	@PutMapping("/players/{id}")
	public User updateUser(@PathVariable(name="id")Long id, @RequestBody User user) {
		//Guardamos el user que queremos actualizar
		User user_to_updt = userServiceImpl.selectUserById(id);
		System.out.println("Se actualiza el user: " +user_to_updt);
		
		//Setteamos su nombre y capacidad
		user_to_updt.setName(user.getName());
		
		// asignamos la actualización y retornamos el objeto actualizado
		user =  userServiceImpl.updateUser(user_to_updt);
		System.out.println("Por el user: " +user);
		
		return user;

	}
	
	
	//DELETE un user por id
	@DeleteMapping("/players/{id}")
	public void deleteUser(@PathVariable(name="id")Long id) {

		System.out.println("Se borra el user con id: " +id);

		userServiceImpl.deleteUser(id);
		
		System.out.println("USER BORRADO");

	}
	
}
