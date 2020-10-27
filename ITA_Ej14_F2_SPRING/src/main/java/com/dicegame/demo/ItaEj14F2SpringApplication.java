package com.dicegame.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dicegame.demo"})
public class ItaEj14F2SpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItaEj14F2SpringApplication.class, args);
	}

}
