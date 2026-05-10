package com.backtest.backtest.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDetailsResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private boolean availability;
}
