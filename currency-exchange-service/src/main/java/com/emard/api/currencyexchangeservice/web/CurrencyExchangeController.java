package com.emard.api.currencyexchangeservice.web;

import java.math.BigDecimal;

import com.emard.api.currencyexchangeservice.model.CurrencyExchange;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class CurrencyExchangeController {
    private final CurrencyExchangeRepository repository;
    private final Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange name(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if(currencyExchange==null) throw new RuntimeException("Unable to find  data for "+from+" to "+to);
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
