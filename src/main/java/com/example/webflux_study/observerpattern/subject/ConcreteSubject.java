package com.example.webflux_study.observerpattern.subject;

import com.example.webflux_study.observerpattern.observer.Observer;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConcreteSubject implements Subject<String> {

    private final Set<Observer<String>> observers = new CopyOnWriteArraySet<>();

    public void registerObserver(Observer<String> observer) {
        observers.add(observer);
    }

    public void unRegisterObserver(Observer<String> observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String event) {
        observers.forEach(observer -> observer.observe(event));
    }
}