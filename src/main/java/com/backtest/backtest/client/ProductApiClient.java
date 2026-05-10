package com.backtest.backtest.client;

import com.backtest.backtest.dto.ProductDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ProductApiClient {
    private final WebClient webClient;

    //["1","2","3"]
    public Flux<Integer> getSimilarIds(String productId) {
        return webClient.get()
                .uri("/product/{id}/similarids", productId)
                .retrieve()
                .bodyToFlux(Integer.class);
    }

    //Objeto ProductDetailsResponse
    /*
    * En las pruebas k6 lanza una prueba de rendimiento de 200 usuario tomando en cuenta el mockeado de los endpoints,
    *  se ha uno con un delay de casi 1 min (50 seg.), por lo que que genera una latencia alta llegado a este punto
    * por eso se ha establecido un timeout de 2 segundos para evitar esperas prolongadas y mejorar la experiencia del usuario.
    * */
    public Mono<ProductDetailsResponse> getProduct(Integer productId) {
        return webClient.get()
                .uri("/product/{id}", productId)
                .retrieve()
                .bodyToMono(ProductDetailsResponse.class)
                .timeout(Duration.ofSeconds(2)); // Timeout de 2 segundos para evitar esperas prolongadas
    }
}
