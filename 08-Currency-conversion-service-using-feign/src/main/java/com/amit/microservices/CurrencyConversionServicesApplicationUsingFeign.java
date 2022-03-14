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

/*

We had to write a lot of tedious code around RestTemplate to get the Currency Conversion
service to talk with the Currency Exchange Microservice.

To make a simple REST API call, we need to write about 20 lines of code. 
	And just imagine what would happen if we have hundreds of Microservices, they are calling each other?

So,we need to repeat this kind of code everywhere.
And that's where Spring Cloud provides us with a framework called Feign.

Feign makes it really, easy to call other Microservices and to make use of Feign
we need to add a specific dependency into our Currency Conversion service.

<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

We would want to be able to talk to the currency-exchange-service from the CurrencyConversionController.


Feign is the rest service client.
	its make it easy to call rest full web service.
@FeignClient is used to talk with external micro services.
	name = name of the currency-exchange microservices
	URL = where currency-exchange ms running.

*/