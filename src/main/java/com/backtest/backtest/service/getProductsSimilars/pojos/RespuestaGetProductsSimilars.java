package com.backtest.backtest.service.getProductsSimilars.pojos;

import com.backtest.backtest.dto.ProductDetailsResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RespuestaGetProductsSimilars {
    private Mono<List<ProductDetailsResponse>> productsSimilarsDetails;
}
