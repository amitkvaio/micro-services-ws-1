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
	private Configuration conf;
	
	@Value("${limit-service.minimum}")
	int  minimum;
	@Value("${limit-service.maximum}")
	int maximum;
	@Value("${limit-service.name}")
	String name;
	
	@GetMapping("/hard-coded-limits")
	public Limits retrieveLimitsHardCodedValues() {
		//Hard-coded values
		return new Limits(1,1000,"Default-Hard-coded-value");
	}
	
	@GetMapping("/reading-from-property-file")
	public Limits readingFromPropertyFile() {
		//Reading the values from bootstrap.property file using @value
		return new Limits(minimum,maximum,name);
	}
	
	@GetMapping("/reading-from-property-file-limits")
	public Limits retrieveLimitsReadingFromPropertiesFile() {
		//Reading from the centralized properties file using configuration
		return new Limits(conf.getMinimum(),conf.getMaximum(), conf.getName());
	}
}
