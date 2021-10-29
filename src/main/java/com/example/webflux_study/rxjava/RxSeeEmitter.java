package com.example.webflux_study.rxjava;

import com.example.webflux_study.pubsubpattern.Temperature;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Subscriber;

import java.io.IOException;

@Getter
public class RxSeeEmitter extends SseEmitter {

    private static final long SSE_SESSION_TIME_OUT = 30*60*1000L;
    private final Subscriber<Temperature> subscriber;

    public RxSeeEmitter() {
        super(SSE_SESSION_TIME_OUT);

        this.subscriber = new Subscriber<Temperature>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Temperature temperature) {
                try {
                    RxSeeEmitter.this.send(temperature);
                } catch (IOException e) {
                    unsubscribe();
                }
            }
        };
        onCompletion(subscriber::unsubscribe);
        onTimeout(subscriber::unsubscribe);
    }
}

