package org.react.tutorials.sec03;

import org.react.tutorials.common.Util;
import reactor.core.publisher.Flux;

import static org.react.tutorials.common.Util.getUserName;

public class Lec11FluxMono {

    public static void main(String[] args) {
        fluxToMono();
    }

    private static void fluxToMono(){
        var flux = Flux.range(1, 100);
        flux.next().subscribe(Util.subscriber());
    }

    private static void monoToFlux(){
        var mono = getUserName(1);
        save(Flux.from(mono));
    }

    private static void save(Flux<String> flux){
        flux.subscribe(Util.subscriber());
    }
}
