package com.amit.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients("com.amit.microservices")
@EnableDiscoveryClient
public class CurrencyConversionServicesApplicationUsingFeign {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServicesApplicationUsingFeign.class, args);
		System.out.println("CurrencyConversionServicesApplicationUsingFeign"
				+ " service has been started successfully!!");
	}
}

/*
-->	Will connect Currency-Conversion-services to Eureka naming server for that need to add 
	one more dependency to the pom.xl
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
			<version>1.4.7.RELEASE</version>
</dependency>

--> To Register Currency-Conversion-services with the Eureka naming server 
	need to add @EnableDiscoveryClient annotation to the CurrencyConversionServicesApplicationUsingFeign class

--> After this need to configure the URL for Eureka naming server, to do this need to add below property 
		in the application.property file
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka


0. 10-Register-Currency-conversion-service-with-eureka-naming-server@CurrencyExchangeProxy
	@FeignClient(name="currency-exchange", url="localhost:8000"): Please uncomment it if it is commented.
	
1. Start the Eureka naming server (09-Eureka-naming-server-setup)
2. Start the Currency Exchange service(11-Register-Currency-exchange-service-with-Eureka-naming-server)
3. Start the currency-coversion-service (10-Register-Currency-conversion-service-with-eureka-naming-server)
4. check the eureka console.
5. Run the multiple instance of 11-Register-Currency-exchange-service-with-Eureka-naming-server
6. Check the eureka console,
7. Hit this url::   http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
	will get the response from only one server with port 8000
8. Till now we are not maintaining the load balance.
9. Comments the step 0 and uncomment below if it is commented.
	@FeignClient(name="currency-exchange")
*/