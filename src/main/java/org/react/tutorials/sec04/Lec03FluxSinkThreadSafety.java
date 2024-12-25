package org.react.tutorials.sec04;

import org.react.tutorials.common.Util;
import org.react.tutorials.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Lec03FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

    public static void main(String[] args) {
        //notThreadSafe();
        fluxThreadSafe();
    }

    private static void notThreadSafe() {
        var list = new ArrayList<Integer>();
        Runnable runnable = () -> {
            int i = 0;
            while (i < 1000) {
                list.add(i++);
            }
        };
        for (int ii = 0; ii < 10; ii++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleepSeconds(3);
        log.info("not safe thread list size: {}", list.size());
    }

    private static void fluxThreadSafe() {
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);
        Runnable runnable = () -> {
            int i = 0;
            while (i++ < 1000) {
                generator.generate();
            }
        };
        for (int ii = 0; ii < 10; ii++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleepSeconds(3);
        log.info("flux thread safe list size: {}", list.size());

    }
}
