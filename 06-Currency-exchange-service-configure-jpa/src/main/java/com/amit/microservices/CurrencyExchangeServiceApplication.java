package com.amit.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
		System.out.println("CurrencyExchangeServiceApplication has been started!!");
	}
}


/*
1.	Using inbuilt database i.e h2 data base
2.	Check the property file for configuration.
3.	spring.jpa.defer-datasource-initialization=true
4.	Above property will called the data.sql file at the time of startup- it will create the table
	and insert the data.
5.	h2 console access URL http://localhost:8000/h2-console
6.	http://localhost:8000/currency-exchange-hard-coded/from/USD/to/INR - will return the hard-coded values.
7.	http://localhost:8000/currency-exchange/from/USD/to/INR - will read the required data from the db and
	return the same values.
8.	For each server startup existing data in h2 database will be wipe out.	

*/