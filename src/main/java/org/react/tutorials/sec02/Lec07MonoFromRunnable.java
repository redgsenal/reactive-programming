package org.react.tutorials.sec02;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec07MonoFromRunnable {
    private static final Logger log = LoggerFactory.getLogger(Lec07MonoFromRunnable.class);

    public static void main(String[] args) {
        getProductName(31)
                .subscribe(Util.subscriber());
    }

    public static Mono<String> getProductName(int productId) {
        return (productId == 1) ?
                Mono.fromSupplier(() -> Util.faker().commerce().productName()) :
                Mono.fromRunnable(() -> notifyBusiness(productId));
    }

    public static void notifyBusiness(int productId) {
        log.info("notify business team, unavailable product id: {}", productId);
    }
}
