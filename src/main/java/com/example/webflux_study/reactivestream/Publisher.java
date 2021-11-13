package com.example.webflux_study.reactivestream;


public interface Publisher<T> {

    void subscribe(Subscriber<? super T> s);
}
