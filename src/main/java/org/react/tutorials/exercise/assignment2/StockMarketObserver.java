package org.react.tutorials.exercise.assignment2;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockMarketObserver implements Subscriber<Integer> {

    private static final Logger log = LoggerFactory.getLogger(StockMarketObserver.class);
    private static final Integer MIN_PRICE_VALUE = 90;
    private static final Integer MAX_PRICE_VALUE = 110;
    private static final Integer INITIAL_INVESTMENT = 1000;
    private int quantity = 0;
    private int balance = INITIAL_INVESTMENT;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer price) {
        if (price < MIN_PRICE_VALUE && balance >= price) {
            // buy stock
            quantity++;
            balance = balance - price;
            log.info("bought stock at {}, quantity is at {} current balance now is {}", price, quantity, balance);
        } else if (price > MAX_PRICE_VALUE && quantity > 0) {
            // sell here
            balance = balance + (quantity * price);
            subscription.cancel();
            int profit = balance - INITIAL_INVESTMENT;
            log.info("sell stock at {}, current quantity is at {} current balance now is {}", price, quantity, balance);
            quantity = 0;
            log.info("profit: {}", profit);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error: {}", throwable.getMessage(), throwable);
    }

    @Override
    public void onComplete() {
        log.info("stock watch completed.");
    }
}
