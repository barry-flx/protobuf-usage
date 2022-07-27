package com.flx.fork;

import java.util.concurrent.RecursiveTask;

public class TestRecursiveTask extends RecursiveTask<Integer> {
    private int begin;
    private int end;

    public TestRecursiveTask(int begin, int end) {
        super();
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) != 0) {
            int min = (begin + end) / 2;
            TestRecursiveTask recursiveTaskLeft = new TestRecursiveTask(begin, min);
            TestRecursiveTask recursiveTaskRight = new TestRecursiveTask(min + 1, end);

            invokeAll(recursiveTaskLeft, recursiveTaskRight);

            int left = recursiveTaskLeft.join();
            int right = recursiveTaskRight.join();
            return left + right;
        } else {
            int sum = 0;
            for (int j = 0; j <= 1000000; j++) {
                sum += j;
            }
            return sum;
        }
    }
}
