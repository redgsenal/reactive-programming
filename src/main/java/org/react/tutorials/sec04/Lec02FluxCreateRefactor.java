package org.react.tutorials.sec04;

import org.react.tutorials.common.Util;
import org.react.tutorials.helper.CountryNameGenerator;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactor {

    public static void main(String[] args) {

        var generator = new CountryNameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(Util.subscriber());

        int i = 0;
        while (i++ < 10) {
            generator.generate();
        }
    }
}
