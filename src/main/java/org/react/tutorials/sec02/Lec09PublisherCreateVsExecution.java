package org.react.tutorials.sec02;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec09PublisherCreateVsExecution {

    private static final Logger log = LoggerFactory.getLogger(Lec09PublisherCreateVsExecution.class);

    public static void main(String[] args) {
        log.info("calling getName subscriber...");
        getName().subscribe(Util.subscriber());
        log.info("continuing after getName called...");
    }

    private static Mono<String> getName() {
        log.info("wait to generate a name...");
        return Mono.fromSupplier(() -> {
            log.info("generating a name...");
            log.info("(delay 5 seconds...)");
            // simulate a long process
            Util.sleepSeconds(5);
            log.info("done...");
            return Util.faker().name().fullName();
        });
    }
}
