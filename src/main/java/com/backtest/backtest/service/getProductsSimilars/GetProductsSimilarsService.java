package com.backtest.backtest.service.getProductsSimilars;

import com.backtest.backtest.client.ProductApiClient;
import com.backtest.backtest.commons.messages.Message;
import com.backtest.backtest.dto.ProductDetailsResponse;
import com.backtest.backtest.service.getProductsSimilars.pojos.DatosGetProductsSimilars;
import com.backtest.backtest.service.getProductsSimilars.pojos.PeticionGetProductsSimilars;
import com.backtest.backtest.service.getProductsSimilars.pojos.RespuestaGetProductsSimilars;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductsSimilarsService {
    private final ProductApiClient productApiClient;

    public RespuestaGetProductsSimilars getProductsSimilars(PeticionGetProductsSimilars peticion){
        RespuestaGetProductsSimilars respuesta = new RespuestaGetProductsSimilars();

        DatosGetProductsSimilars datos = peticion.getDatos();
        //Obtener los ids de los productos similares por id del producto
        Flux<String> similarsIds = productApiClient.getSimilarIds(datos.getProductId());
        //Obtener los detalles de los productos similares por su id
        //flatMap para async concurrente
        Flux<ProductDetailsResponse> productsSimilarsDetails = similarsIds.flatMap(productApiClient::getProduct)
                        .onErrorContinue((error, object) -> {
                           System.out.println(Message.ERROR_OBTENER_PRODUCTO + ": " + error.getMessage() + " - Product ID: " + object);
                        });

        Mono<List<ProductDetailsResponse>> productsSimilarsDetailsList = productsSimilarsDetails.collectList();

        respuesta.setProductsSimilarsDetails(productsSimilarsDetailsList);
        return respuesta;
    }
}
