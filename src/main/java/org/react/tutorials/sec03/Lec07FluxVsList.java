package org.react.tutorials.sec03;

import org.react.tutorials.common.Util;
import org.react.tutorials.helper.NameGenerator;
import org.react.tutorials.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec07FluxVsList {

    private static final Logger log = LoggerFactory.getLogger(Lec07FluxVsList.class);

    public static void main(String[] args) {
        /*
        var names = NameGenerator.generateFullNamesList(100);
        log.info("Full names list: {}", names);
        */

        var subscriber = new SubscriberImpl();
        NameGenerator
                .generateFullNamesFlux(10)
                .subscribe(subscriber);

        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();
    }
}
