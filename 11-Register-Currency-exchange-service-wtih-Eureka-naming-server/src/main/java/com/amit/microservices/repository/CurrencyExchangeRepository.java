package com.amit.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.microservices.bean.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	CurrencyExchange findByFromAndTo(String from,String to);
}
