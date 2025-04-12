package org.react.tutorials.sec04;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec08GenerateWithState {

    private static final Logger log = LoggerFactory.getLogger(Lec08GenerateWithState.class);
    private static final int MAX_COUNT = 100;

    public static void main(String[] args) {
        //generateWithAtomicCounter();
        generateWithFluxFunctions();
    }

    private static void generateWithFluxFunctions() {
        Flux.generate(
                        () -> 0,
                        (counter, sink) -> {
                            counter++;
                            String country = Util.faker().country().name();
                            sink.next(country);
                            log.info("#{} country: {}", counter, country);
                            if (counter == MAX_COUNT || "canada".equalsIgnoreCase(country)) {
                                sink.complete();
                            }
                            return counter;
                        }
                )
                .subscribe(Util.subscriber());
    }

    private static void generateWithAtomicCounter() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                    atomicInteger.incrementAndGet();
                    log.info("#{} country: {}", atomicInteger.get(), country);
                    if (atomicInteger.get() == MAX_COUNT || "canada".equalsIgnoreCase(country)) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }
}
