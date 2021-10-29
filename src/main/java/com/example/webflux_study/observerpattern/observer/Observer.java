package com.example.webflux_study.observerpattern.observer;

public interface Observer<T>{

    void observe(T event);
}
