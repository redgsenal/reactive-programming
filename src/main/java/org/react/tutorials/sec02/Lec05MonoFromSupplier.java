package org.react.tutorials.sec02;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {
    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {

        var list = List.of(4, 7, 10);

        // Mono.just(sum(list)).subscribe(Util.subscriber());
        Mono.fromSupplier(() -> sum(list)).subscribe(Util.subscriber());

        var list2 = List.of(4, 8, 12);
        Mono.fromSupplier(() -> {
            try {
                return sumWithException(list2);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return 0;
        }).subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> listIntegers) {
        log.info("compute for the sum: {}", listIntegers);
        return listIntegers.stream().mapToInt(a -> a).sum();
    }

    private static int sumWithException(List<Integer> listIntegers) throws Exception {
        log.info("compute for the sum with exception: {}", listIntegers);
        int v = listIntegers.stream().mapToInt(a -> a).sum();
        if (v % 2 == 0) throw new Exception("Invalid results");
        return v;
    }
}
