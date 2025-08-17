package org.react.tutorials.sec06;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec03AutoConnect {

    private static final Logger log = LoggerFactory.getLogger(Lec03AutoConnect.class);

    public static void main(String[] args) {
        log.info("Starting Lec03AutoConnect...");
        var streamFluxItems = Util.streamItems().publish().autoConnect();

        streamFluxItems
                .take(8)
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(3);
        streamFluxItems
                .take(3)
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(15);

    }

}
