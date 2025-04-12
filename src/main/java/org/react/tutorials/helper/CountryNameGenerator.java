package org.react.tutorials.helper;

import org.react.tutorials.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class CountryNameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> fluxSink) {
        this.fluxSink = fluxSink;
    }

    public void generate() {
        this.fluxSink.next(Util.faker().country().name());
    }
}
