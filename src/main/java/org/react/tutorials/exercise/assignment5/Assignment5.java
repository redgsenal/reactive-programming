package org.react.tutorials.exercise.assignment5;

import org.react.tutorials.client.ExternalServiceClient;
import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Assignment5 {

    private static final Logger log = LoggerFactory.getLogger(Assignment5.class);

    public static void main(String[] args) {
        generateProductName();
    }

    private static void generateProductName() {

        var client = new ExternalServiceClient();
        for (int productId = 1; productId < 5; productId++) {
            log.info("product id: {}", productId);
            client.getProduct(productId)
                    .defaultIfEmpty("result is empty for this product id")
                    .timeout(Duration.ofSeconds(1), client.getTimeoutFallbackProduct(productId))
                    .switchIfEmpty(client.getEmptyFallbackProduct(productId))
                    .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(2);
    }
}
