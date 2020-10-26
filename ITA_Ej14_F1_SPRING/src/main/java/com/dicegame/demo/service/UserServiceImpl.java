package com.dicegame.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicegame.demo.dao.IUserDAO;
import com.dicegame.demo.dto.User;


@Service
public class UserServiceImpl implements IUserService {

	//Utilizamos los metodos de la interface IUserDAO, es como si instaciaramos.
	@Autowired
	IUserDAO iUserDAO;
	
	@Override
	public User createUser(User user) {		
		
		return iUserDAO.save(user);
	}

	@Override
	public List<User> selectAllUsers() {
		
		return iUserDAO.findAll();
	}
	
	@Override
	public User selectUserById (Long id) {
		
		return iUserDAO.findById(id).get();
	}
	

	@Override
	public User updateUser(User user) {
		return iUserDAO.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		iUserDAO.deleteById(id);	

	}
	
}