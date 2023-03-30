package com.project.carshar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.project.carshar.repositories")
@ComponentScan(basePackages = { "com.project.carshar.controllers", "com.project.carshar.services",
		"com.project.carshar.security"})
@SpringBootApplication
public class CarsharApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsharApplication.class, args);
	}

}
