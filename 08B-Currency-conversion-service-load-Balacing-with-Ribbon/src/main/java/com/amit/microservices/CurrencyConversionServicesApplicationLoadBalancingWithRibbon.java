package com.amit.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.amit.microservices")
public class CurrencyConversionServicesApplicationLoadBalancingWithRibbon {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServicesApplicationLoadBalancingWithRibbon.class, args);
		System.out.println("CurrencyConversionServicesApplicationLoadBalancingWithRibbon"
				+ " service has been started successfully!!");
	}
}