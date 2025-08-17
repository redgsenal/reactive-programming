package org.react.tutorials.sec06;

import java.time.Duration;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class Loc02HotPublisher {

    private static final Logger log = LoggerFactory.getLogger(Loc02HotPublisher.class);

    public static void main(String[] args) {
        var streamFluxItems = streamItems().share();

        Util.sleepSeconds(1);
        streamFluxItems
                .take(15)
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(3);
        streamFluxItems
                .take(5)
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(15);
    }

    public static Flux<String> streamItems() {
        return Flux.generate(
                () -> {
                    log.info("Generating items");
                    return 1;
                }, (state, sink) -> {
                    var item = "Item " + state;
                    log.info("current item: {}", item);
                    sink.next(item);
                    /* if (state >= 5) {
                        sink.complete();
                    } */
                    return ++state;
                })
                .take(100)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }

}
