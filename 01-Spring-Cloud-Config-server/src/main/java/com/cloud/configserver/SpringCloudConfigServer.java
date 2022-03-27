package com.cloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServer {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServer.class, args);
		System.out.println("SpringCloudConfigServer has been started successfully!!");
	}
}
/*
Here we are trying to connect spring cloud config server to local git hub repository.
For this need to add one more annotation @EnableConfigServer, as shown in this program.
For more details check the applicatin.properties file

we can store all the configuration for different environments of different microservices in 
just one place in a centralized location and 
spring cloud config server can be used to expose that configuration to all the microservices.


We have establish the connection between SprinCloudConfigServer and the local Git repository.
*/