package org.react.tutorials.sec05;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec08SwitchIfEmpty {

    private static final Logger log = LoggerFactory.getLogger(Lec08SwitchIfEmpty.class);

    public static void main(String[] args) {

        defaultIfFluxEmpty();
    }

    private static void defaultIfFluxEmpty() {
        Flux.range(1, 10)
                .filter(i -> i > 11)
                .switchIfEmpty(defaultRange())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> defaultRange() {
        log.info("calling default range...");
        return Flux.range(20, 10)
                .filter(i -> i % 2 == 0)
                .map(i -> i * 3);
    }
}
