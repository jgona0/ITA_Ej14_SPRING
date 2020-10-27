 package com.dicegame.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dicegame.demo.dto.Roll;

public interface IRollDAO extends MongoRepository<Roll, String>{

	//public List<Roll> findByUserId(Long id_user); // SELECT rolls por id del user

	public List<Roll> findByUser(String user); // SELECT rolls por id del user	
	public void deleteByUser(String user); // DELETE rolls por User_Id	
	
}
