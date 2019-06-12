package com.pedrodantas.smartchef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pedrodantas.smartchef.service.DBService;
import com.pedrodantas.smartchef.service.S3Service;

@SpringBootApplication
public class Smartchef1Application implements CommandLineRunner{
	
	@Autowired
	private DBService dbservice;
	
	@Autowired
	private S3Service s3service;

	public static void main(String[] args) {
		SpringApplication.run(Smartchef1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dbservice.instantiateTestDataBase();
	}

}
