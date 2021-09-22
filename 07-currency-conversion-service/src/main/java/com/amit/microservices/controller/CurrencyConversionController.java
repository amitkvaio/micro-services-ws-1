package com.amit.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amit.microservices.bean.CurrencyConvresion;

@RestController
public class CurrencyConversionController {
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	//http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
	public CurrencyConvresion calculateCurrencyConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		//http://localhost:8000/currency-exchange/from/USD/to/INR
		 ResponseEntity<CurrencyConvresion> responseEntity = 
				 new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
						 CurrencyConvresion.class,uriVariables);
		CurrencyConvresion currencyConvresion = responseEntity.getBody();
		/*
		 * return new CurrencyConvresion(1000L, from, to, quantity, BigDecimal.ONE,
		 * BigDecimal.ONE, "");
		 */
		
		return new CurrencyConvresion(currencyConvresion.getId(), 
				from, to, quantity, 
				currencyConvresion.getConversionMultiple(), 
				quantity.multiply(currencyConvresion.getConversionMultiple()), 
				currencyConvresion.getEnvironment());
	}
}
