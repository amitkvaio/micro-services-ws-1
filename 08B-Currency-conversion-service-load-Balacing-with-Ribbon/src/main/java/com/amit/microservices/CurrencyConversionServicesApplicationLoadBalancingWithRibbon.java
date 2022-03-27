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

/*
	
Types of Load Balancing:
************************
There are two types of load balancing

Server Side Load Balancing: 
	Server side load balancing is a monolithic It applies between the client and the server. 
Client-Side Load Balancing: 
	The client holds the list of server’s IPs so that it can deliver the requests. 
	The client selects an IP from the list, randomly, and forwards the request to the server.


Netflix Ribbon
****************

<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
*******************

Netflix Ribbon is a Part of Netflix Open Source Software (Netflix OSS). 
	It is a cloud library that provides the client-side load balancing. 
	It automatically interacts with Netflix Service Discovery (Eureka) because it is a member of the Netflix family.


The Ribbon mainly provides client-side load balancing algorithms. 
	It is a client-side load balancer that provides control over the behavior of HTTP and TCP client. 
	The important point is that when we use Feign, the Ribbon also applies.
	
Features of Ribbon
*******************
Load balancing
Fault tolerance
Multiple protocol support in Asynchronous model
Caching and batching

we have configured the Ribbon and distributed the load between the three services
*/