package org.react.tutorials.sec05;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Delay {

    private static final Logger log = LoggerFactory.getLogger(Lec04Delay.class);

    public static void main(String[] args) {

        log.info("main thread start...");
        Flux.range(1, 1000)
                .map(i -> {
                    log.info("current i: {}", i);
                    return i;
                })
                .log()
                .doOnComplete(() -> {
                    log.info("yeah done!!");
                })
                .subscribe(Util.subscriber());

        // do something else that takes some time
        log.info("do country loop...");
        String country = null;
        while(!"canada".equalsIgnoreCase(country)){
            country = Util.faker().country().name();
            log.info("country: {}", country);
        }
        // Util.sleepSeconds(8);
        log.info("main thread done...");

    }
}
