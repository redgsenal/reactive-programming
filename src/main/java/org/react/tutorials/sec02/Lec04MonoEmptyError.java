package org.react.tutorials.sec02;

import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUserName(3)
                .subscribe(
                        System.out::println,
                        err -> System.out.println(err.getMessage()) // or err -> {} (show no error message)
                );
    }

    private static Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Sam");
            case 2 -> Mono.empty(); // same as null
            default -> Mono.error(new RuntimeException("Invalid user id"));
        };
    }
}
