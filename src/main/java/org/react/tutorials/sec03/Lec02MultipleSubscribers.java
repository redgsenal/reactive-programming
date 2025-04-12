package org.react.tutorials.sec03;

import org.react.tutorials.common.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {
    public static void main(String[] args) {
        var flux1 = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        flux1.subscribe(Util.subscriber("sub1"));
        flux1
                .filter(i -> i > 10)
                .subscribe(Util.subscriber("sub2"));
        flux1.filter(i -> i % 2 == 0).map(i -> String.valueOf(i * 3).concat(" -> i")).subscribe(Util.subscriber("sub3"));
    }
}
