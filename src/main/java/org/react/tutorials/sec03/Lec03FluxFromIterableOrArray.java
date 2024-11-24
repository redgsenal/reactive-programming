package org.react.tutorials.sec03;

import org.react.tutorials.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {

    public static void main(String[] args) {
        var alphas = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        Flux.fromIterable(alphas).subscribe(Util.subscriber());

        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Flux.fromArray(numbers).subscribe(Util.subscriber());
    }
}
