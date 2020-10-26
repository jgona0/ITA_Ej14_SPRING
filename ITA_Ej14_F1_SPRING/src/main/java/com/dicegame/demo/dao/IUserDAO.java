package com.dicegame.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicegame.demo.dto.User;

public interface IUserDAO extends JpaRepository<User, Long> {

}
