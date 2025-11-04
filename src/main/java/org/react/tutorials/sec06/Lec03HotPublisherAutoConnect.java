package org.react.tutorials.sec06;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03HotPublisherAutoConnect {

    private static final Logger log = LoggerFactory.getLogger(Lec03HotPublisherAutoConnect.class);

    public static void main(String[] args) {
        log.info("Starting Lec03AutoConnect...");
        var movieFlux = movieStream().publish().autoConnect();

        Util.sleepSeconds(3);
        movieFlux
                .take(3)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(3);
        movieFlux
                .take(3)
                .subscribe(Util.subscriber("mike"));
        Util.sleepSeconds(15);
    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                        () -> {
                            log.info("receive request");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "move scene ".concat(String.valueOf(state));
                            log.info("playing {}", scene);
                            sink.next(scene);
                            return ++state;
                        }
                )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}