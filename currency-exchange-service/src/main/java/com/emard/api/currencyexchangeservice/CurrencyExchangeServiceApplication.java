package com.emard.api.currencyexchangeservice;

import java.math.BigDecimal;

import com.emard.api.currencyexchangeservice.model.CurrencyExchange;
import com.emard.api.currencyexchangeservice.web.CurrencyExchangeRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CurrencyExchangeRepository repository){
		return args -> {
			CurrencyExchange c1 = new CurrencyExchange();
			c1.setConversionMutiple(BigDecimal.valueOf(65));c1.setFrom("USD");c1.setTo("INR");c1.setId(10001l);
			CurrencyExchange c2 = new CurrencyExchange();
			c2.setConversionMutiple(BigDecimal.valueOf(75));c2.setFrom("EUR");c2.setTo("INR");c2.setId(10002l);
			CurrencyExchange c3 = new CurrencyExchange();
			c3.setConversionMutiple(BigDecimal.valueOf(25));c3.setFrom("AUD");c3.setTo("INR");c3.setId(10003l);
			repository.save(c1);
			repository.save(c2);
			repository.save(c3);
		};
	}
}
