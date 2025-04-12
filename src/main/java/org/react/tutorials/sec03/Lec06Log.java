package org.react.tutorials.sec03;

import org.react.tutorials.common.Util;
import reactor.core.publisher.Flux;

public class Lec06Log {

    public static void main(String[] args) {
        Flux.range(1, 100)
                .log("numbers ->")
                .map(i -> Util.faker().name().fullName())
                .log("names ->")
                .subscribe(Util.subscriber("sub1"));
    }
}
