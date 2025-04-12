package org.react.tutorials.sec03;

import org.react.tutorials.common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {
        // range(starting, number of items)
        // Flux.range(1, 100).subscribe(Util.subscriber());

        Flux.range(1, 10).map(i -> Util.faker().name().fullName()).subscribe(Util.subscriber());
    }
}
