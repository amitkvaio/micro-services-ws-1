package com.cloud.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudConfigClient {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigClient.class, args);
		System.out.println("SpringCloudConfigClient has been started successfully!!");
	}
}
