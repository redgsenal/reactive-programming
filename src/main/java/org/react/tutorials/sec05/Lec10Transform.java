package org.react.tutorials.sec05;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

public class Lec10Transform {

    private static final Logger log = LoggerFactory.getLogger(Lec10Transform.class);

    record Customer(int id, String fullName) {
    }

    record PurchaseOrder(String productName, int price, int quantity) {
    }

    public static void main(String[] args) {

        generateCustomers()
                .transform(addDebugger())
                .subscribe();

        generatePurchaseOrders()
                .transform(addDebugger())
                .subscribe();

    }

    private static Flux<Customer> generateCustomers() {
        return Flux.range(1, 10)
                .map(i -> new Customer(i, Util.faker().name().fullName()));
    }

    private static Flux<PurchaseOrder> generatePurchaseOrders() {
        return Flux.range(1, 10)
                .map(i -> new PurchaseOrder(Util.faker().commerce().productName(), fakeNumber(), fakeNumber()));
    }

    private static int fakeNumber() {
        return Util.faker().number().numberBetween(5, 100);
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return flux -> flux
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("done!"))
                .doOnError(err -> log.error("error:", err));
    }
}
