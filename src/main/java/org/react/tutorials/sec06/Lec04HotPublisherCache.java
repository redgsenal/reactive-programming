package org.react.tutorials.sec06;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {
        log.info("Starting Lec04HotPublisherCache...");
        var stockFlux = stockStream().replay().autoConnect(0);

        Util.sleepSeconds(4);
        log.info("sam joining...");
        stockFlux
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(4);
        log.info("mike joining...");
        stockFlux
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);
    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 1000)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("price emitting at {}", price))
                .cast(Integer.class);
    }
}