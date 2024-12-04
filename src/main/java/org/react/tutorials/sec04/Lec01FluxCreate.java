package org.react.tutorials.sec04;

import org.apache.commons.lang3.StringUtils;
import org.react.tutorials.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    public static void main(String[] args) {
        //doCount();
        //listCountries();
        listCountriesUntil("Philippines");
    }

    private static void listCountriesUntil(String country) {
        if (StringUtils.isNotEmpty(country)) {
            Flux.create(fluxSink -> {
                        String itemCountry = "";
                        while (!itemCountry.equalsIgnoreCase(country)) {
                            itemCountry = Util.faker().country().name();
                            fluxSink.next(itemCountry);
                        }
                        fluxSink.complete();
                    })
                    .subscribe(Util.subscriber());
        }
    }

    private static void listCountries() {
        Flux.create(fluxSink -> {
            int i = 0;
            while (i++ < 10) {
                fluxSink.next(Util.faker().country().name());
            }
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }

    private static void doCount() {
        Flux.create(fluxSink -> {
            int i = 0;
            while (i++ < 10) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
