package org.react.tutorials.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05Subscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec05Subscribe.class);

    public static void main(String[] args) {

        Flux.range(0, 100)
                .doOnNext(i -> {
                    log.info("i: {}", i);
                    if (i == 20) {
                        throw new RuntimeException("do error on this value");
                    }
                })
                .doOnComplete(() -> log.info("complete!"))
                .onErrorContinue((err, intValue) -> {
                    log.warn("some error thrown: {} - {}", err.getMessage(), intValue);
                })
                .subscribe();

    }
}
