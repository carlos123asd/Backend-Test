package com.backtest.backtest.service.getProductsSimilars;

import com.backtest.backtest.client.ProductApiClient;
import com.backtest.backtest.commons.messages.Message;
import com.backtest.backtest.dto.ProductDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductsSimilarsService {
    private final ProductApiClient productApiClient;
    private static final Logger log =
            LoggerFactory.getLogger(GetProductsSimilarsService.class);

    public Mono<List<ProductDetailsResponse>> getProductsSimilars(String productId){
        //Obtener los ids de los productos similares por id del producto
        Flux<String> similarsIds = productApiClient.getSimilarIds(productId)
                .onErrorResume(error -> {;
                    log.error("{} con ID {}: {}", Message.PRODUCTOS_SIMILARES_NO_OBTENIDOS, productId, error.getMessage());
                    return Flux.empty();
                });
        //Obtener los detalles de los productos similares por su id
        //flatMap para async concurrente
        Flux<ProductDetailsResponse> productsSimilarsDetails = similarsIds
                .flatMap(id ->
                        productApiClient.getProduct(id)
                                .onErrorContinue((error, object) -> {
                                    log.error("{} con ID {}: {}", Message.ERROR_OBTENER_PRODUCTO, id, error.getMessage());
                                })
                );

        return productsSimilarsDetails.collectList();
    }
}
