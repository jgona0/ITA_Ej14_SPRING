package com.dicegame.demo.service;

import java.util.List;
import com.dicegame.demo.dto.Roll;


public interface IRollService {

	
	public Roll createRoll(Roll roll);	//Crea una roll (asociado a un user)
	
	public List<Roll> selectAllRolls(); //Seleccionar todas las rolls 
	
	public Roll selectRollById (long id); //Seleccionar una roll por ID
	
	public List<Roll> selectRollsUser(Long id_user); //Listar rolls de un user
	
	//public Roll updatePaint (Roll roll); //Hacer update de una roll   -->> NO DEJAMOS HACER UPDATE PORQUE NO TIENE SENTIDO
	
	// public void deletePaint (Long id); //Borrar un paint  -->> NO DEJAMOS HACER UN BORRADO UNITARIO DE UNA ROLL
	
	public void deleteRollsUSer(Long id_user); //Borrar todas las rolls de un user
	
}
