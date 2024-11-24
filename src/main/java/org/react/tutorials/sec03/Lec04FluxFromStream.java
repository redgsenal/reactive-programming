package org.react.tutorials.sec03;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxFromStream.class);

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        var stream = numbers.stream();
        /*
        // stream is one time use
        stream.forEach(i -> log.info("i: {}", i));
        stream.forEach(i -> log.info("i: {}", i));
        */
        var flux = Flux.fromStream(numbers::stream);
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }
}
