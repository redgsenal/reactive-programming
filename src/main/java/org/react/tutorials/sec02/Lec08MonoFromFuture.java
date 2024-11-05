package org.react.tutorials.sec02;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec08MonoFromFuture {
    private static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {
        Mono.fromFuture(Lec08MonoFromFuture::getName)
                .subscribe(Util.subscriber());
        Util.sleepSeconds(2);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("generate name...");
            return Util.faker.name().name();
        });
    }
}
