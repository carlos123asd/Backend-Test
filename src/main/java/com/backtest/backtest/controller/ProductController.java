package com.backtest.backtest.controller;

import com.backtest.backtest.dto.ProductDetailsResponse;
import com.backtest.backtest.service.getProductsSimilars.GetProductsSimilarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final GetProductsSimilarsService getProductsSimilarsService;

    @GetMapping("/{productId}/similar")
    public Mono<List<ProductDetailsResponse>> getProductsSimilars(@PathVariable String productId){
        return getProductsSimilarsService.getProductsSimilars(productId);
    }
}
