package com.example.webflux_study.reactivestream;

public interface Subscription {

    void request(long n);
    void cancel();
}
