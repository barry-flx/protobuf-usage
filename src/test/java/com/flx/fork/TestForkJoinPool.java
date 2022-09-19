package com.flx.fork;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class TestForkJoinPool {

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
