package com.example.webflux_study.reactivestream;

import rx.Subscriber;

public interface Publisher<T> {

    void subscribe(Subscriber<? super T> s);
}
