package org.react.tutorials.sec04;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class Lec05TakeOperator {

    private static final Logger log = LoggerFactory.getLogger(Lec05TakeOperator.class);

    public static void main(String[] args) {
        fluxRange();
    }

    private static void intStream() {
        IntStream.rangeClosed(1, 10)
                .limit(5)
                .forEach(value -> {
                    log.info("value: {}", value);
                });
    }

    private static void fluxRange() {
        Flux.range(1, 10)
                .log("take: ")
                .take(7)
                .log("subscribe: ")
                .subscribe(Util.subscriber());
    }
}
