package org.react.tutorials.sec05;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec09Timeout {

    private static final Logger log = LoggerFactory.getLogger(Lec09Timeout.class);

    public static void main(String[] args) {

        demoTimeout();
        demoNoTimeout();

    }

    private static void demoTimeout(){
        // timeout is shorter than the product name call time
        generateSlowProductName()
                .timeout(Duration.ofSeconds(2), generateFallBackProductName())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static void demoNoTimeout(){
        // timeout is long; more time for product name call
        generateSlowProductName()
                .timeout(Duration.ofSeconds(5), generateFallBackProductName())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static Mono<String> generateSlowProductName() {
        return Mono.fromSupplier(() -> Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(3));
    }

    private static Mono<String> generateFallBackProductName() {
        return Mono.fromSupplier(() -> "Acme Fallback Co.")
                .delayElement(Duration.ofSeconds(1));
    }

}
