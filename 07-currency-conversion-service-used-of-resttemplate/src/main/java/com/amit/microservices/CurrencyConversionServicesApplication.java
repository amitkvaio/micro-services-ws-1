package com.amit.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyConversionServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServicesApplication.class, args);
		System.out.println("CurrencyConversionServicesApplication service has been started successfully!!");
	}
}

/*
1.	There is two api available here.
	a.	http://localhost:8100/currency-conversion-hard-coded-values/from/USD/to/INR/quantity/10
	b.	http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/100
2.a its returning the hard-coded values as we have set, will get the output like below
		id		1000
		from	"USD"
		to		"INR"
		quantity	10
		conversionMultiple	75
		totalCalucatedAmout	750
		environment	"8000"

Invoking Currency Exchange services from the currency conversion.
	How to call the Currency Exchange Microservice from the Currency Conversion Microservice?
		RestTemplate can be used to make REST API calls.
		getforEntity(); Used to send a get request and we would want to get a object back.
		first argument is what is the URL that you would want to invoke.
		
	ResponseEntity<CurrencyConvresion> responseEntity = 
				 new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
						 CurrencyConvresion.class,uriVariables);

2.b Fetching the CurrencyConversion object from (http://localhost:8000/currency-exchange/from/USD/to/INR) 
		URL and we are making use of that, for more details check CurrencyConversionController.java class
		will get the output like below.
	
		id		1000
		from	"USD"
		to		"INR"
		quantity	100
		conversionMultiple	75
		totalCalucatedAmout	7500
		environment	"8000"

*/