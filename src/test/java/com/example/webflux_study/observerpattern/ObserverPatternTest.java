package com.example.webflux_study.observerpattern;

import com.example.webflux_study.observerpattern.observer.ConcreteObserverA;
import com.example.webflux_study.observerpattern.observer.ConcreteObserverB;
import com.example.webflux_study.observerpattern.subject.ConcreteSubject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ObserverPatternTest {

    @Test
    void observersHandleEventsFromSubject() {
        //given
        final ConcreteSubject subject = new ConcreteSubject();
        final ConcreteObserverA observerA = spy(new ConcreteObserverA());
        final ConcreteObserverB observerB = spy(new ConcreteObserverB());
        //when
        subject.notifyObservers("No listeners");

        subject.registerObserver(observerA);
        subject.notifyObservers("Message for A");

        subject.registerObserver(observerB);
        subject.notifyObservers("Message for A and B");

        subject.unRegisterObserver(observerA);
        subject.notifyObservers("Message for B");

        subject.unRegisterObserver(observerB);
        subject.notifyObservers("No listeners");

        //then
        verify(observerA, times(1)).observe("Message for A");
        verify(observerA, times(1)).observe("Message for A and B");
        Mockito.verifyNoMoreInteractions(observerA);

        verify(observerB, times(1)).observe("Message for A and B");
        verify(observerB, times(1)).observe("Message for B");
        Mockito.verifyNoMoreInteractions(observerB);
    }
}
