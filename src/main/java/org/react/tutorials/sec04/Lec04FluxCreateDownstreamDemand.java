package org.react.tutorials.sec04;

import org.react.tutorials.common.Util;
import org.react.tutorials.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateDownstreamDemand {
    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    public static void main(String[] args) {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            for (int i = 0; i < 15; i++) {
                var name = Util.faker().name().fullName();
                log.info("generated name: {}", name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);

        // do cancel next
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(1);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(1);
        subscriber.getSubscription().cancel();
        // this part will not request
        subscriber.getSubscription().request(3);

    }
}
