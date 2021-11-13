package com.example.webflux_study.reactivestream;

public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
}
