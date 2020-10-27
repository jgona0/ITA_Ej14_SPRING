package com.dicegame.demo.exceptions;

import java.util.NoSuchElementException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



// Clase para gestionar las excepciones que se pueden producir a la hora de ejecutar el programa. Seleccionar, borrar, crear Users y Rolls, etc.
@ControllerAdvice
public class HandlerException {

	// GET user o roll que no existe --> NoSuchElementException  --> 500
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> noEmployeeFoundSearch(NoSuchElementException ex){
		return new ResponseEntity<Object> ("The user or roll that you're looking for, is not found", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	// DELETE user o roll que no existe --> EmptyResultDataAccessException --> 500
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> noEmployeeFoundDelete(EmptyResultDataAccessException ex){
		return new ResponseEntity<Object> ("The user or paint that you want to delete, is not found", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	// CREATE user o roll with bad parameters --> HttpMessageNotReadableException --> 400
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> creteEmployeeBadParameters(HttpMessageNotReadableException ex){
		return new ResponseEntity<Object> ("You are trying to create an user or roll with bad parameters", HttpStatus.BAD_REQUEST);
	}
	
}

	

