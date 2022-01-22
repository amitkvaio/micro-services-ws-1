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
Here we are trying to connect spring cloud config server to Git hub repository.
For this need to add one more annotation @EnableConfigServer, as shown in this program.
For more details check the applicatin.properties file
*/