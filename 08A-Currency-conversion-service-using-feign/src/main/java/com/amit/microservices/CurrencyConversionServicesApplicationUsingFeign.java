package com.amit.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.amit.microservices")
public class CurrencyConversionServicesApplicationUsingFeign {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServicesApplicationUsingFeign.class, args);
		System.out.println("CurrencyConversionServicesApplicationUsingFeign"
				+ " service has been started successfully!!");
	}
}