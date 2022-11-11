package com.emard.api.currencyexchangeservice.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CircuitBreakerController {
    
    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //circuit breaker casse le circuit et donne une réponse par def au lieu de surcharger l'api qui a un pb
    //@CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //@RateLimiter(name = "default")//nb de call par periode accepté
    @Bulkhead(name = "default")
    public String sampleApi() {
        log.info("Sample APi called received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return forEntity.getBody();
        //return "Sample api";
    }

    public String hardcodedResponse(Exception e) {
        return "fallback-response";
    }
}
