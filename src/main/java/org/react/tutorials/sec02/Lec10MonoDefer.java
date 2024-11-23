package org.react.tutorials.sec02;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Lec10MonoDefer {

    private static final Logger log = LoggerFactory.getLogger(Lec10MonoDefer.class);

    public static void main(String[] args) {

        int[] numbers = IntStream.rangeClosed(1, 1000).toArray();
        List<Integer> items = Arrays.stream(numbers).boxed().toList();
        log.info("items {}", items);
        // Mono.fromSupplier(() -> sum(items)).subscribe(Util.subscriber());

        // createPublisher(items).subscribe(Util.subscriber());

        Mono.defer(() -> createPublisher(items)).subscribe(Util.subscriber());
    }

    private static Mono<Integer> createPublisher(List<Integer> items) {
        log.info("calling create publisher...");
        Util.sleepSeconds(5);
        return Mono.fromSupplier(() -> sum(items));
    }

    // time-consuming business logic process
    private static int sum(List<Integer> items) {
        log.info("calling sum function....");
        Util.sleepSeconds(5);
        return items.stream().mapToInt(a -> a).sum();
    }
}
