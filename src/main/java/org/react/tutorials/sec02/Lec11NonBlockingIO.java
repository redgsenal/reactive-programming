package org.react.tutorials.sec02;

import org.react.tutorials.common.Util;
import org.react.tutorials.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        log.info("calling get product name...");
        // the response will not be in sequence
        int i = 1;
        while(i < 100) {
            client.getProductName(i++).subscribe(Util.subscriber());
        }
        Util.sleepSeconds(2);
    }
}
