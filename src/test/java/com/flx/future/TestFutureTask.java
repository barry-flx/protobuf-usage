package com.flx.future;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TestFutureTask {

    @Test
    public void testFutureTask() {

    }

}

class Task1 implements Callable<String> {
    FutureTask<String> task2;

    // t1任务需要t2任务的FutureTask
    Task1(FutureTask<String> task2) {
        this.task2 = task2;
    }

    @Override
    public String call() throws Exception {
        System.out.println("t1:洗水壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("t1:烧开水");
        TimeUnit.SECONDS.sleep(15);

        // 获取t2线程的茶叶
        String task = task2.get();
        System.out.println("t1:拿到茶叶:" + task);

        System.out.println("t1:泡茶");
        return "上茶:" + task;
    }

}
// https://blog.csdn.net/sermonlizhi/article/details/123356877
