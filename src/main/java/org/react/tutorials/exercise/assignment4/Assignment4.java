package org.react.tutorials.exercise.assignment4;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Assignment4 {

    private static final Logger log = LoggerFactory.getLogger(Assignment4.class);

    public static void main(String[] args) {

        Flux.<String>generate(sink -> {
                    sink.next(Util.faker().country().name());
                })
                .handle(
                        (country, sink) -> {
                            sink.next(country);
                            log.info("country: {}", country);
                            if ("canada".equalsIgnoreCase(country)) {
                                sink.complete();
                            }
                        })
                .subscribe(Util.subscriber());

    }


}
