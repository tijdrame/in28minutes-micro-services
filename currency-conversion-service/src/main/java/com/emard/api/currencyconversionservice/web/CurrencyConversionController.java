package com.emard.api.currencyconversionservice.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.emard.api.currencyconversionservice.model.CurrencyConversion;
import com.emard.api.currencyconversionservice.proxy.CurrencyExchangeProxy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class CurrencyConversionController {
    private final RestTemplate restTemplate;
    private final CurrencyExchangeProxy proxy;
    
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        CurrencyConversion currencyConversion =  proxy.name(from, to);
        return new CurrencyConversion(1000l, from, to, currencyConversion.getConversionMutiple(), quantity, 
        quantity.multiply(currencyConversion.getConversionMutiple()), currencyConversion.getEnvironment());
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculate(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> forEntity = restTemplate.getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}", 
        CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = forEntity.getBody();
        if(currencyConversion == null) throw new RuntimeException("Unable to find  data for "+from+" to "+to);
        return new CurrencyConversion(1000l, from, to, currencyConversion.getConversionMutiple(), quantity, 
        quantity.multiply(currencyConversion.getConversionMutiple()), currencyConversion.getEnvironment());
    }
}
