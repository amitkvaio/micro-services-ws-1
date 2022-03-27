package com.amit.microservices.limitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimitsServiceSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(LimitsServiceSpringBootApplication.class, args);
		System.out.println("LimitsServiceSpringBootApplication has started successfull!!");
	}
}

/*
Reading the values from property file

*/