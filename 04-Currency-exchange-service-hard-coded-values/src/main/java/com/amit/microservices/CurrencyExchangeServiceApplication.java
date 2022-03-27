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

Here we are printing the hard-coded values - its similar to previous example.

id		1000
from	"USD"
to		"INR"
ConversionMultiple	75

*/