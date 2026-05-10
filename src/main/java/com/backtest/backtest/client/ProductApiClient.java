package com.backtest.backtest.client;

import com.backtest.backtest.dto.ProductDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductApiClient {
    private final WebClient webClient;

    //["1","2","3"]
    public Flux<String> getSimilarIds(String productId) {
        return webClient.get()
                .uri("/product/{id}/similarids", productId)
                .retrieve()
                .bodyToFlux(String.class);
    }

    //Objeto ProductDetailsResponse
    public Mono<ProductDetailsResponse> getProduct(String productId) {
        return webClient.get()
                .uri("/product/{id}", productId)
                .retrieve()
                .bodyToMono(ProductDetailsResponse.class);
    }
}
