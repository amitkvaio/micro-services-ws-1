package com.amit.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaNamingServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaNamingServerApplication.class, args);
		System.out.println("EurekaNamingServerApplication application has started!");
	}
}