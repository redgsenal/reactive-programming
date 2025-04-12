package org.react.tutorials.sec03;

import org.react.tutorials.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInternal {

    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(200))
                .map(i -> Util.faker().funnyName().name())
                .takeUntil(name -> name.startsWith("A"))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(2);
    }
}
