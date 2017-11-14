package com.hadoopService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class MicroserviceHadoopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceHadoopApplication.class, args);
		
	}
	
	

}
