package org.react.tutorials.helper;

import org.react.tutorials.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    public static List<String> generateFullNamesList(int count) {
        return IntStream
                .rangeClosed(1, count)
                .mapToObj(i -> generateFullName())
                .toList();
    }

    public static Flux<String> generateFullNamesFlux(int count) {
        return Flux
                .range(1, count)
                .map(i -> generateFullName());
    }

    public static String generateFullName() {
        Util.sleepSeconds(1);
        return Util.faker().name().fullName();
    }

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        fluxSink = stringFluxSink;
    }

    public void generate() {
        this.fluxSink.next(Util.faker().name().fullName());
    }
}
