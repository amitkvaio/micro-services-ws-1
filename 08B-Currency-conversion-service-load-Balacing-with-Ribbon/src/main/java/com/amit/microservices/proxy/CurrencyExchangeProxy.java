package com.amit.microservices.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amit.microservices.bean.CurrencyConversion;


@FeignClient(name="currency-exchange")
@RibbonClient(name="currency-exchange")  
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange-jpa/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);
}

/*
Feign is the rest service client.
	its make it easy to call rest full web service.
@FeignClient is used to talk with external micro services.
	name = name of the currency-exchange microservices
	URL = where currency-exchange ms running.
	
Enable Ribbon by adding an annotation @RibbonClient and specify the name of the service 
	which we want to talk to.
*/