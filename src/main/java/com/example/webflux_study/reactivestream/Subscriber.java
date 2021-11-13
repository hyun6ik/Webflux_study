package com.example.webflux_study.reactivestream;

public interface Subscriber<T> {

    void onSubscribe(Subscription s);
    void onNext(T t);
    void onError(Throwable t);
    void onComplete();
}
