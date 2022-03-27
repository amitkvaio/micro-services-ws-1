package com.amit.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.microservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {
	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration retriveLimitsFromConfigurations() {
		//Hard Coded values
		//return new LimitConfiguration(1000, 100);
		
		//Reading the values from property file
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
}