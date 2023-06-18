package com.backend.integrador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
@ComponentScan
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		LOGGER.info("Proyecto integrador is now running ...");
	}
}
