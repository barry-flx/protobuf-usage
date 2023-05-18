package com.flx.subscribe;

import io.reactivex.BackpressureStrategy;
import org.junit.Test;
import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class FlowableTest {

    @Test
    public void testSubscribe() {
        Flowable.just("Hello world").subscribe(System.out::println);

        Flowable<String> flowable = Flowable.create(emitter -> {
            emitter.onNext("Hello");
            emitter.onNext("World");

            for (int i = 0; i < 30; i++) {
                emitter.onNext(i + "");
                Thread.sleep(300);
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);
        flowable.subscribe(System.out::println);
    }

    @Test
    public void testSubscribe2() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Flowable<String> flowable = Flowable.create(emitter -> {
            // 发送事件
            emitter.onNext("Hello");
            emitter.onNext("World");
            // 结束事件流
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);

        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(() -> {
            flowable.subscribe(new Subscriber<String>() {
                @Override
                public void onSubscribe(Subscription s) {
                    s.request(Long.MAX_VALUE);
                }

                @Override
                public void onNext(String s) {
                    System.out.println(Thread.currentThread().getName() + " onNext: " + s);
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println(Thread.currentThread().getName() + " onError: " + t.getMessage());
                }

                @Override
                public void onComplete() {
                    System.out.println(Thread.currentThread().getName() + " onComplete");
                }
            });
        }, 0, 1, TimeUnit.SECONDS);

        executor.schedule(() -> {
            scheduledFuture.cancel(true);
            executor.shutdown();
        }, 10, TimeUnit.SECONDS);
    }

    @Test
    public void testSubscribe3() {
        Flowable<String> flowable = Flowable.create(emitter -> {
            // 发送事件
            emitter.onNext("Hello");
            emitter.onNext("World");
            // 结束事件流
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);

        flowable.subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber 1 onNext: " + s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Subscriber 1 onError: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Subscriber 1 onComplete");
            }
        });

        flowable.subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber 2 onNext: " + s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Subscriber 2 onError: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Subscriber 2 onComplete");
            }
        });
    }

}
