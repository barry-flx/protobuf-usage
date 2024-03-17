package com.flx.fork;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class TestForkJoinPool {

    @Test
    public void testParallelOut() {
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numberList.parallelStream().forEach(num -> {
            System.out.println(Thread.currentThread().getName() + ":::" + num);
        });
    }

    /**
     * 多计算量多层循环分治编程
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testForkJoinPool() throws ExecutionException, InterruptedException {
        long begin = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i <= 86400; i++) {
            for (int j = 0; j <= 1000000; j++) {
                sum += j;
            }
        }
        System.out.println("结果值：" + sum);
        System.out.println("消耗时间：" + (System.currentTimeMillis() - begin));

        long begin2 = System.currentTimeMillis();
        TestRecursiveTask recursiveTask = new TestRecursiveTask(0, 86400);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(recursiveTask);
        System.out.println("结果值：" + recursiveTask.get());
        pool.shutdown();
        System.out.println("消耗时间：" + (System.currentTimeMillis() - begin2));
    }

}
