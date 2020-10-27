package com.dicegame.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicegame.demo.dao.IRollDAO;
import com.dicegame.demo.dao.IUserDAO;
import com.dicegame.demo.dto.Roll;
import com.dicegame.demo.dto.User;

//Capa de implementación del servicio

@Service
public class RollServiceImpl implements IRollService{

	@Autowired
	IRollDAO irollDAO;
	
	@Autowired
	IUserDAO iuserDAO;
	
	@Override
	public Roll createRoll(Roll roll) {
		return irollDAO.save(roll);
	}
	
	@Override
	public List<Roll> selectAllRolls() {
		return irollDAO.findAll();
	}
	
	
	public List<Roll> selectRollsUser (String user_id) {
		return irollDAO.findByUser(user_id);
	}


	@Override
	public void deleteRollsUser(String name) {
		
		User user = iuserDAO.findByName(name);
		
		List<Roll> rollsToDelete = irollDAO.findByUser(user.getId());
		
		for (Roll iter : rollsToDelete) {
			irollDAO.deleteById(iter.getId());
		}
	
	}
	
}
