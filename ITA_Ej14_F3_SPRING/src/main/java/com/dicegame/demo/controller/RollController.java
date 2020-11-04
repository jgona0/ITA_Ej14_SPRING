package com.dicegame.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicegame.demo.dto.Roll;
import com.dicegame.demo.dto.User;
import com.dicegame.demo.service.RollServiceImpl;
import com.dicegame.demo.service.UserServiceImpl;



//Capa controller donde la ruta origen será .../api

@RestController
@RequestMapping("/api")
public class RollController {

	@Autowired
	RollServiceImpl rollServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;

	
	// CREATE roll
	@PostMapping("/players/{id}/games")
	public Roll postRoll(@PathVariable(name="id")Long id) {
		
		//Seleccionamos el user de la roll
		User roll_user = userServiceImpl.selectUserById(id);
		
		//Creamos la roll, lanzando sus dados y seteando su user
		Roll roll = new Roll();
		
		roll.playGame();
		roll.setUser(roll_user);
		
		// Insertamos la roll
		rollServiceImpl.createRoll(roll);
		 
		//Actualizamos el %de exito del usuario
		double total_rolls = rollServiceImpl.selectRollsUser(roll_user.getId()).stream().count();
		double total_win_rolls = rollServiceImpl.selectRollsUser(roll_user.getId()).stream().filter( x -> x.getResult().equals("WIN")).count(); 
		
		double porc_success = 0.0;
		
		if(total_rolls == 0.0) {
			porc_success = 0.0;
		}else {
			porc_success = (total_win_rolls/total_rolls) * 100;
		}
				
		roll_user.setPorcSucces(Math.floor(porc_success * 100) / 100);
		userServiceImpl.updateUser(roll_user);
		
		
		return roll;
	}	
	
	
	//SELECT todas las rolls
	@GetMapping("/games")
	public List<Roll> getAllRolls(){
		return rollServiceImpl.selectAllRolls();
	}
	
	//SELECT roll por id
	@GetMapping("/games/{id}")
	public Roll getRollById(@PathVariable(name="id") Long id) {
		return rollServiceImpl.selectRollById(id);
	}
	
	
	//SELECT rolls por User ID
	@GetMapping("players/{id}/games")
	public List<Roll> getRollsUser(@PathVariable(name="id") Long id){
		return rollServiceImpl.selectRollsUser(id);
	}
	

	//DELETE rolls por User ID
	@PreAuthorize("hasRole('ADMIN')")  ///// SOLO LOS ADMINS PUEDEN BORRAR ROLLS
	@DeleteMapping ("players/{id}/games")
	public void deleteRollsUser(@PathVariable(name="id") Long id) {
		System.out.println("Se borran todas las rolls de del user: " +id);

		rollServiceImpl.deleteRollsUSer(id);
		
		System.out.println("ROLLS BORRADAS");
	}
}
