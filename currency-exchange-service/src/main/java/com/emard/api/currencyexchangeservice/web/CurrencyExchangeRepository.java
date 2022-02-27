package com.emard.api.currencyexchangeservice.web;

import com.emard.api.currencyexchangeservice.model.CurrencyExchange;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByFromAndTo(String from, String to);
    
}
