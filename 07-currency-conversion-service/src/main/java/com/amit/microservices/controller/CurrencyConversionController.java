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
	
	@GetMapping("/currency-conversion-hard-coded-values/from/{from}/to/{to}/quantity/{quantity}")
	//http://localhost:8100/currency-conversion-hard-coded-values/from/USD/to/INR/quantity/10
	public CurrencyConvresion calculateCurrencyConversionHardCoded(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
			BigDecimal currencyConversion = new BigDecimal(75);
			System.out.println("From method CurrencyConversionController@calculateCurrencyConversionHardCoded");
		  return new CurrencyConvresion(1000L, from, to, quantity, currencyConversion,
			quantity.multiply(currencyConversion), "8000");
	}
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	//http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/100
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
		
		/*
			Fetching the CurrencyConversion object from above specified URL and we are making use of that.
		*/
		CurrencyConvresion currencyConvresion = responseEntity.getBody();
		System.out.println(">>>>>>>>>>>>>>currencyConvresion>>>>>>>>>>>"+currencyConvresion);
		
		return new CurrencyConvresion(currencyConvresion.getId(), 
				from, to, quantity, 
				currencyConvresion.getConversionMultiple(), 
				quantity.multiply(currencyConvresion.getConversionMultiple()), 
				currencyConvresion.getEnvironment());
	}
}
