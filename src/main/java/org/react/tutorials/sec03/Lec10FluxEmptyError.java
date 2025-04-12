package org.react.tutorials.sec03;

import org.react.tutorials.common.Util;
import reactor.core.publisher.Flux;

public class Lec10FluxEmptyError {

    public static void main(String[] args) {

        Flux.empty().subscribe(Util.subscriber(" done!"));
        Flux.error(new RuntimeException("some error")).subscribe(Util.subscriber(" gotcha!"));
    }
}
