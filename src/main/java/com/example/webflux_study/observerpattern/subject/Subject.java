package com.example.webflux_study.observerpattern.subject;

import com.example.webflux_study.observerpattern.observer.Observer;

public interface Subject<T> {

    void registerObserver(Observer<T> observer);
    void unRegisterObserver(Observer<T> observer);
    void notifyObservers(T event);
}
