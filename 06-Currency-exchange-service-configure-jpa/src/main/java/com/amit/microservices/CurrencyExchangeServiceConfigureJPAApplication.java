package com.amit.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyExchangeServiceConfigureJPAApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceConfigureJPAApplication.class, args);
		System.out.println("CurrencyExchangeServiceConfigureJPAApplication has been started!!");
	}
}