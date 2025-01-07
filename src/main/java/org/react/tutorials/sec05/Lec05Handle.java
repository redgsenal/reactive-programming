package org.react.tutorials.sec05;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05Handle {

    private static final Logger log = LoggerFactory.getLogger(Lec05Handle.class);

    public static void main(String[] args) {
        fluxHandler();
    }

    private static void fluxThowError() {
        Flux<Integer> flux = Flux.range(1, 100);
        flux.handle((intValue, sink) -> {
            sink.error(new RuntimeException("runtime error"));
        }).subscribe(Util.subscriber());
    }

    private static void fluxHandler() {
        Flux.range(1, 10)
                .filter(i ->
                        (i != 7)
                )
                .handle((intValue, sink) -> {
                    switch (intValue) {
                        case 1 -> sink.next(-2);
                        case 4 -> {}
                        case 7 -> sink.error(new RuntimeException("value 7, throw error"));
                        default -> sink.next(intValue);
                    }
                })
                .onErrorContinue((throwable, o) -> {
                    log.warn(throwable.getMessage());
                })
                .map(o -> {
                    log.info("value: {}", o);
                    return o;
                })
                .subscribe(Util.subscriber());
    }
}
