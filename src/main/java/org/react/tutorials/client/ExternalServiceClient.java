package org.react.tutorials.client;

import org.react.tutorials.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId) {
        return this.httpClient
                .get().uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }

    public Flux<String> getProductNames() {
        return this.httpClient
                .get().uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<String> getStockMarketPrices() {
        return this.httpClient
                .get().uri("/demo02/stock/stream")
                .responseContent()
                .asString();
    }

    public Flux<Integer> getStockMarketStream() {
        return this.httpClient
                .get().uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }

    public Mono<String> getProduct(int productId) {
        return this.httpClient
                .get().uri("/demo03/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> getEmptyFallbackProduct(int productId) {
        return this.httpClient
                .get().uri("/demo03/empty-fallback/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> getTimeoutFallbackProduct(int productId) {
        return this.httpClient
                .get().uri("/demo03/timeout-fallback/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }
}
