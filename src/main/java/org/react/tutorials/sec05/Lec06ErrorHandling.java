package org.react.tutorials.sec05;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {
        onErrorContinue();
    }

    private static void onErrorContinue() {
        Flux.range(1, 10)
                .map(v -> (v == 5) ? v / 0 : v)
                .onErrorContinue((err, v) -> {
                    log.warn("error: value {} - {}", v, err.getMessage());
                })
                .subscribe(Util.subscriber());
    }

    private static void onErrorResume() {
        Flux.range(1, 10)
                .map(v -> (v == 5) ? v / 0 : v)
                .onErrorResume(err -> Mono.just(5))
                .subscribe(Util.subscriber());
    }

    private static void onErrorComplete() {
        Flux.range(1, 10)
                .map(v -> (v == 5) ? v / 0 : v)
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }

    private static void onErrorHandling4() {
        Flux.range(1, 10)
                .map(v -> (v == 5) ? v / 0 : v)
                .onErrorResume(ArithmeticException.class, err -> {
                    // fallback on error resume
                    log.warn("arithmetic exception, error resume with new value");
                    return fallback();
                })
                .onErrorResume(IllegalArgumentException.class, err -> {
                    // fallback on error resume
                    log.warn("Illegal arg exception, error resume with new value");
                    return fallback(100, 1000);
                })
                .subscribe(Util.subscriber());
    }

    private static void onErrorHandling3() {
        Flux.range(1, 10)
                .map(v -> (v == 5) ? v / 0 : v)
                .onErrorResume(err -> {
                    // fallback on error resume
                    log.warn("general error exception, error resume with new value");
                    return Mono.fromSupplier(() -> Util.faker().number().numberBetween(10, 100));
                })
                .subscribe(Util.subscriber());
    }

    private static void onErrorHandling1() {
        Flux.range(1, 10)
                .map(v -> (v == 5) ? v / 0 : v)
                .onErrorReturn(ArithmeticException.class, -1)
                .onErrorReturn(0)
                .subscribe(Util.subscriber());
    }

    private static void onErrorHandling2() {
        Flux.range(1, 10)
                .map(v -> {
                    switch (v) {
                        case 9 -> {
                            throw new IllegalArgumentException("Error 1");
                        }
                        case 7 -> {
                            return v / 0;
                        }
                        default -> {
                            return v;
                        }
                    }
                })
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback() {
        return fallback(10, 100);
    }

    private static Mono<Integer> fallback(int min, int max) {
        return Mono.fromSupplier(() -> Util.faker().number().numberBetween(min, max));
    }

}
