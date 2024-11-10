package org.react.tutorials.sec02;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {
    private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {

        var list = List.of(4, 7, 9);
        Mono.fromCallable(()->sum(list)).subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> listIntegers) {
        log.info("compute for the sum: {}", listIntegers);
        return listIntegers.stream().mapToInt(a -> a).sum();
    }
}
