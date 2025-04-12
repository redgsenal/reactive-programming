package org.react.tutorials.sec02;

import reactor.core.publisher.Mono;

import static org.react.tutorials.common.Util.getUserName;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUserName(3)
                .subscribe(
                        System.out::println,
                        err -> System.out.println(err.getMessage()) // or err -> {} (show no error message)
                );
    }
}
