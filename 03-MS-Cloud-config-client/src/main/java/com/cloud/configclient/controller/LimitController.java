package com.cloud.configclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.configclient.bean.Limits;
import com.cloud.configclient.config.Configuration;

@RestController
public class LimitController {
	
	@Autowired
	private Configuration configuration;
	
	@Value("${limit-service.minimum}")
	int  minimum;
	@Value("${limit-service.maximum}")
	int maximum;
	
	@GetMapping("/hard-coded-limits")
	public Limits retrieveLimitsHardCodedValues() {
		//Hard-coded values
		return new Limits(1,1000);
	}
	
	@GetMapping("/hard-coded-limits_")
	public Limits retrieveLimitsHardCodedValues_() {
		//Reading the values from bootstrap.property file using @value
		return new Limits(minimum,maximum);
	}
	
	@GetMapping("/reading-from-property-file-limits")
	public Limits retrieveLimitsReadingFromPropertiesFile() {
		//Reading from the centralized properties file using configuration
		return new Limits(configuration.getMinimum(),configuration.getMaximum());
	}
}
