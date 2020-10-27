package com.dicegame.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dicegame.demo.dto.User;

public interface IUserDAO extends MongoRepository<User, String> {

	public User findByName(String name); // SELECT users por NAME
	public void deleteByName(String name); // DELETE users por NAME


}
