package com.cloud.configclient;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
@SpringBootApplication
@RefreshScope
public class SpringCloudConfigClient {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigClient.class, args);
		System.out.println("SpringCloudConfigClient has been started successfully!!");
	}
}

/*

In this project we will exposing the hard-coded values on the UI.
	Reading the values from configuration file/property file at the centralize location.
		Before reading the data from the centralized location spring cloud config server should 
		be start else it will give the default values.

@RefreshScope annotation is used to load the configuration properties value from the Config server.
for newly added property in the configuration file/properties
*/
































/*
spring-cloud-starter-config is used to connect the spring-cloud-config-server in order
to fetch the centralized configuration file.

-- Need to change the application.properties file to bootstrap.properties file
spring.application.name=centralized
	above property is very important - here centralized name is not the application name
	it the property file name in the git repository.
server.port=2021
spring.cloud.config.uri=http://localhost:8888 
	above url is for where our cloud-config-server is running
spring.profiles.active=prod : used for profile name as prod environment

-- Need to add one more dependency for bootstrap in the pom.xml
	<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-bootstrap</artifactId>
		</dependency>
*/