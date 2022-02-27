package com.emard.api.currencyexchangeservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class CurrencyExchange {
    @NonNull
    @Id
    private Long id;
    @NonNull
    @Column(name = "fromC")
    private String from;
    @NonNull
    @Column(name = "toC")
    private String to;
    @NonNull
    private BigDecimal conversionMutiple;
    private String environment;
}
