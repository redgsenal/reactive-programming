package org.react.tutorials.common;

import java.time.Duration;

import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Util {

    private static final Logger log = LoggerFactory.getLogger(Util.class);
    public static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static void main(String[] args) {
        var mono = Mono.just(1);
        mono.subscribe(subscriber("sub_1"));
        mono.subscribe(subscriber("sub_2"));
    }

    public static Faker faker() {
        return faker;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 ->
                Mono.just("Sam");
            case 2 ->
                Mono.empty(); // same as null
            default ->
                Mono.error(new RuntimeException("Invalid user id"));
        };
    }

    public static Flux<String> streamItems() {
        return Flux.generate(
                () -> {
                    log.info("Generating items");
                    return 1;
                }, (state, sink) -> {
                    var item = "Item " + state;
                    log.info("current item: {}", item);
                    sink.next(item);
                    /* if (state >= 5) {
                        sink.complete();
                    } */
                    return ++state;
                })
                .take(100)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
