package com.dicegame.demo.service;

import java.util.List;
import com.dicegame.demo.dto.Roll;


public interface IRollService {

	
	public Roll createRoll(Roll roll);	//Crea un user (asociado a un user)
	
	public List<Roll> selectAllRolls(); //Seleccionar todas las rolls 
		
	public List<Roll> selectRollsUser(String name); //Listar rolls de un user
	
	//public Roll updatePaint (Roll roll); //Hacer update de una roll   -->> NO DEJAMOS HACER UPDATE PORQUE NO TIENE SENTIDO
	
	// public void deletePaint (Long id); //Borrar un paint  -->> NO DEJAMOS HACER UN BORRADO UNITARIO DE UNA ROLL
	
	public void deleteRollsUser(String name); //Borrar todas las rolls de un user
		
}
