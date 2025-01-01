package org.react.tutorials.sec04;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class Lec05TakeOperator {

    private static final Logger log = LoggerFactory.getLogger(Lec05TakeOperator.class);

    public static void main(String[] args) {
        fluxRangeTakeUntil();
    }

    private static void intStream() {
        IntStream.rangeClosed(1, 10)
                .limit(5) // loop until limit of 5 items
                .forEach(value -> {
                    log.info("value: {}", value);
                });
    }

    private static void fluxRangeTake() {
        Flux.range(1, 10)
                .log("take: ")
                .take(7) // take 7 items from a range of 1 to 10
                .log("subscribe: ")
                .subscribe(Util.subscriber());
    }

    private static void fluxRangeTakeWhile() {
        Flux.range(1, 100)
                .log("take: ")
                .takeWhile(v -> v < 30) // loop while value less than 30
                .log("subscribe: ")
                .subscribe(Util.subscriber());
    }


    private static void fluxRangeTakeUntil() {
        Flux.range(1, 100)
                .log("take: ")
                .takeUntil(v -> v == 20) // loop until v == 20
                .log("subscribe: ")
                .subscribe(Util.subscriber());
    }
}
