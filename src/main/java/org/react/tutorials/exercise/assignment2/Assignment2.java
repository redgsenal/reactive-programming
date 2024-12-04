package org.react.tutorials.exercise.assignment2;

import org.react.tutorials.client.ExternalServiceClient;
import org.react.tutorials.common.Util;

public class Assignment2 {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        var subscriber = new StockMarketObserver();
        client.getStockMarketStream()
                .subscribe(subscriber);

        Util.sleepSeconds(20);
    }
}
