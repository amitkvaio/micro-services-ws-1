package com.amit.microservices.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amit.microservices.bean.CurrencyExchange;
import com.amit.microservices.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	//It will return the hard-coded values.
	@GetMapping("/currency-exchange-hard-coded/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchnageValueHardCoded(
			@PathVariable String from,
			@PathVariable String to) {
		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, "USD", "INR", BigDecimal.valueOf(50));
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
	
	@GetMapping("/currency-exchange-jpa/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchnageValue(
			@PathVariable String from,
			@PathVariable String to) {
		//Getting currencyExchange from the db
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if (currencyExchange == null)
			throw new RuntimeException("Unable to Find data for " + from + " to " + to);
		
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
