package org.react.tutorials.sec03;

import org.react.tutorials.client.ExternalServiceClient;
import org.react.tutorials.common.Util;

public class Lec08NonBlockingStreamMessages {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        client.getProductNames()
                .subscribe(Util.subscriber("sub1"));
        client.getProductNames()
                .subscribe(Util.subscriber("sub2"));
        Util.sleepSeconds(6);
    }
}
