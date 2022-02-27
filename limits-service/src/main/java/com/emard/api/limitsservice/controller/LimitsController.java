package com.emard.api.limitsservice.controller;

import com.emard.api.limitsservice.bean.Limits;
import com.emard.api.limitsservice.configuration.Configuration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LimitsController {
    private final Configuration configuration;
    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
