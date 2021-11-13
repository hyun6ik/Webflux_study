package com.example.webflux_study.reactivestream;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HibridPushPull {

    private final AsyncDbClient dbClient;

    public Publisher<Item> list(int count) {
        Publisher<Item> source = dbClient.getStreamOfItems();
        TakeFilterOperator<Item> takeFilter = new TakeFilterOperator<>(source, count, item -> isValid(item));

        return takeFilter;
    }

}
