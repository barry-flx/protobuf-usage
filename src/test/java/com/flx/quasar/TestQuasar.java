package com.flx.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.strands.Strand;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestQuasar {

    public static void main(String[] args) throws InterruptedException {
        int number = 1_000_000;
        CountDownLatch latch = new CountDownLatch(1);
        AtomicInteger counter = new AtomicInteger(0);

        for (int i = 0; i < number; i++) {
            new Fiber(() -> {
                counter.incrementAndGet();
                if (counter.get() == number) {
                    System.out.println("done");
                }
                Strand.sleep(1_000_000);
            }).start();
        }
        latch.await();
    }

}
