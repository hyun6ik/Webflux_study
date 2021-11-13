package com.example.webflux_study.reactivestream;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class NewsServiceSubscriber implements Subscriber<NewsLetter> {

    final Queue<NewsLetter> mailbox = new ConcurrentLinkedDeque<>();
    final int take;
    final AtomicInteger remaining = new AtomicInteger();
    Subscription subscription;

    public NewsServiceSubscriber(int take) {
        this.take = take;
    }


    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
        subscription.request(take);
    }

    @Override
    public void onNext(NewsLetter newsLetter) {
        mailbox.offer(newsLetter);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }

    public Optional<NewsLetter> eventuallyReadDigest() {
        final NewsLetter letter = mailbox.poll();
        if (letter != null) {
            if (remaining.decrementAndGet() == 0) {
                subscription.request(take);

                remaining.set(take);
            }
            return Optional.of(letter);
        }
        return Optional.empty();
    }
}
