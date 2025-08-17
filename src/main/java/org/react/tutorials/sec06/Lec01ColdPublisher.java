package org.react.tutorials.sec06;

import java.util.concurrent.atomic.AtomicInteger;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class Lec01ColdPublisher {

    private static final Logger log = LoggerFactory.getLogger(Lec01ColdPublisher.class);

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        var flux = Flux.create(fluxSink -> {
            log.info("invoked create method");
            // Emit items to the sink
            for (int i = 0; i < 3; i++) {
                fluxSink.next(atomicInteger.incrementAndGet());
            }
            fluxSink.complete();
        });

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }
}
