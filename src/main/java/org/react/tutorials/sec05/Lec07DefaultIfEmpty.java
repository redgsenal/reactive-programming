package org.react.tutorials.sec05;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec07DefaultIfEmpty {

    private static final Logger log = LoggerFactory.getLogger(Lec07DefaultIfEmpty.class);

    public static void main(String[] args) {

        defaultIfMonoEmpty();
        defaultIfMonoNotEmpty();
        defaultIfFluxEmpty();
        defaultIfFluxNotEmpty();

    }

    private static void defaultIfFluxNotEmpty() {
        Flux.range(1, 100)
                .filter(i -> i > 10 && i % 2 == 0)
                .map(i -> i * 2)
                .defaultIfEmpty(100)
                .subscribe(Util.subscriber());
    }

    private static void defaultIfFluxEmpty() {
        Flux.range(1, 10)
                .filter(i -> i > 10 && i % 2 == 0)
                .map(i -> i * 2)
                .defaultIfEmpty(100)
                .subscribe(Util.subscriber());
    }

    private static void defaultIfMonoEmpty(){
        Mono.empty()
                .defaultIfEmpty("default text is empty")
                .subscribe(Util.subscriber());

        Mono.just("hello world")
                .defaultIfEmpty("default text is empty")
                .subscribe(Util.subscriber());
    }

    private static void defaultIfMonoNotEmpty(){
        Mono.just("hello world")
                .defaultIfEmpty("default text is empty")
                .subscribe(Util.subscriber());
    }
}
