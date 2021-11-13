package com.example.webflux_study.reactivestream;

import java.util.Queue;
import java.util.function.Predicate;

public class TakeFilterOperator<T> implements Publisher<T> {
    @Override
    public void subscribe(Subscriber<? super T> s) {
        source.subscribe(new TakeFilterInner<>(s, take, predicate));
    }

    static final class TakeFilterInner<T> implements Subscriber<T>, Subscription {
        final Subscriber<T> actual;
        final int take;
        final Predicate<T> predicate;
        final Queue<T> queue;

        Subscription current;
        int remaining;
        int filtered;
        volatile long requested;

        TakeFilterInner(Subscriber<T> actual, int take, Predicate<T> predicate) {
            ...
        }

        public void onSubscribe(Subscription current) {
            current.request(take);
        }

        public void onNext(T element) {
            long r = requested;
            Subscriber<T> a = actual;
            Subscription s = current;

            if (remaining > 0) {
                boolean isValid = predicate.test(element);
                boolean isEmpty = queue.isEmpty();
                if (isValid && r > 0 && isEmpty) {
                    a.onNext(element);
                    remaining--;
                    ...
                    return;
                }
                if (isValid && (r == 0 || !isEmpty)) {
                    queue.offer(element);
                    remaining--;
                    ...
                    return;
                }
                if (!isValid) {
                    filtered++;
                    return;
                }
                s.cancel();
                onComplete();
            }

            if (filtered > 0 && remaining / filtered < 2) {
                s.request(take);
                filtered = 0;
            }
        }
        ...
    }
}
