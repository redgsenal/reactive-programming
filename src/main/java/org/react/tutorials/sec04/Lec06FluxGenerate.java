package org.react.tutorials.sec04;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {
        doAssignment();
    }

    private static void syncSink() {
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(1);
                    //synchronousSink.next(2); // will not work
                    //synchronousSink.complete();
                })
                .subscribe(Util.subscriber()); // infinite loop
    }

    private static void syncSinkWithTake() {
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(1);
                    //synchronousSink.next(2);
                    //synchronousSink.complete();
                })
                .take(10) // loop until 10 takes
                .subscribe(Util.subscriber());
    }

    private static void doAssignment() {
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                    if ("canada".equalsIgnoreCase(country)) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }
}
