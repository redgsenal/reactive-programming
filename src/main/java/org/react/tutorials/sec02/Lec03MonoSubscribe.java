package org.react.tutorials.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1(){
        var mono = Mono.just(1);
        // .map(i -> i / 0); tigger the error
        mono.subscribe(
                i -> log.info("demo 1 received: {}", i),
                err -> log.error("error: ", err),
                () -> log.info("completed!")
        );
    }

    private static void demo2(){
        var mono = Mono.just(1);
        // .map(i -> i / 0); tigger the error
        mono.subscribe(
                i -> log.info("demo 2 received: {}", i),
                err -> log.error("error: ", err),
                () -> log.info("completed!"),
                subscription -> subscription.request(5)
        );
    }
}
