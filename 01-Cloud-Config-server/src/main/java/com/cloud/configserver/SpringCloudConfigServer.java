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
