package org.react.tutorials.sec03;

import org.react.tutorials.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {

    public static void main(String[] args) {
        Flux.just("a", "b", "c", 1, 2, 3).subscribe(Util.subscriber());
    }
}
