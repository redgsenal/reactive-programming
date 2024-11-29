package org.react.tutorials.exercise.assignment2;

import org.apache.commons.lang3.StringUtils;
import org.react.tutorials.client.ExternalServiceClient;
import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Assignment2 {

    private static final Logger log = LoggerFactory.getLogger(Assignment2.class);

    private static final int MAX_STOCK_PRICE = 110;
    private static final int MIN_BUYING_STOCK_PRICE = 90;
    private static final int INITIAL_BALANCE = 1000;

    private static int totalStocks = 0;

    public static void main(String[] args) {
        // read the stock market stream
        var client = new ExternalServiceClient();
        client.getStockMarketPrices()
                .map(Assignment2::convertPriceToValue)
                .takeUntil(Assignment2::isCutOffPrice)
                .map(Assignment2::buyStock)
                .doOnComplete(() -> {
                    int profit = INITIAL_BALANCE - totalStocks;
                    log.info("compute total stocks {}: ", totalStocks);
                    log.info("compute profit {}: ", profit);
                })
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }

    private static int convertPriceToValue(String price) {
        if (StringUtils.isBlank(price)) {
            return 0;
        }
        return Integer.parseInt(price);
    }

    private static boolean isCutOffPrice(int price) {
        return price > MAX_STOCK_PRICE;
    }

    private static int buyStock(int price) {
        log.info("price: {}", price);
        if (price < MIN_BUYING_STOCK_PRICE) {
            log.info("buy! at {}: ", price);
            totalStocks = totalStocks + price;
        }
        return price;
    }
}
