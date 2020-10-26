package com.dicegame.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicegame.demo.dao.IRollDAO;
import com.dicegame.demo.dto.Roll;



@Service
public class RollServiceImpl implements IRollService{

	@Autowired
	IRollDAO irollDAO;
	
	@Override
	public Roll createRoll(Roll roll) {
		return irollDAO.save(roll);
	}
	
	@Override
	public List<Roll> selectAllRolls() {
		return irollDAO.findAll();
	}
	
	@Override
	public Roll selectRollById(long id) {
		return irollDAO.findById(id).get();
	}
	
	public List<Roll> selectRollsUser (Long id_user) {
		return irollDAO.findByUserId(id_user);
	}


	@Override
	public void deleteRollsUSer(Long id_user) {
		
		List<Roll> rollsToDelete = irollDAO.findByUserId(id_user);
		
		for (Roll iter : rollsToDelete) {
			irollDAO.deleteById(iter.getId());
		}
	
	}
	
}
