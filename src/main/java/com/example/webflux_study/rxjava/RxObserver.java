package com.example.webflux_study.rxjava;

public interface RxObserver<T> {
    void onNext(T next);
    void onComplete();
    void onError(Exception e);

}
