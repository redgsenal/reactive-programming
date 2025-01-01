package org.react.tutorials.sec04;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {

    private static final Logger log = LoggerFactory.getLogger(Lec07FluxGenerateUntil.class);

    public static void main(String[] args) {
        doAssignment2();
    }

    private static void doAssignment1() {
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    log.info("#1 country: {}", country);
                    synchronousSink.next(country);
                    if ("canada".equalsIgnoreCase(country)) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }

    private static void doAssignment2() {
        Flux.<String>generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    log.info("#2 country: {}", country);
                    synchronousSink.next(country);
                })
                .takeUntil(s -> s.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }
}

