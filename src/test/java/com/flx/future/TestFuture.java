package com.flx.future;

import org.junit.Test;

import java.util.concurrent.*;

public class TestFuture {

    @Test
    public void testFuture() {
        try {
            // 创建线程池
            ExecutorService executor = Executors.newFixedThreadPool(2);
            // 异步获取结果1
            Future<String> future1 = executor.submit(this::getResult1);
            // 异步获取结果2
            Future<String> future2 = executor.submit(this::getResult2);
            // 获取结果1异步保存
            executor.execute(() -> {
                try {
                    save(future1.get());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            // 获取结果2异步保存
            executor.execute(() -> {
                try {
                    save(future2.get());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testCompletionService() throws Exception {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // 创建CompletionService
        CompletionService<String> cs = new ExecutorCompletionService<>(executor);
        // 异步获取结果1
        cs.submit(this::getResult1);
        // 异步获取结果2
        cs.submit(this::getResult2);
        // 将获取结果异步保存到数据库
        for (int i = 0; i < 2; i++) {
            String result = cs.take().get();
            executor.execute(() -> save(result));
        }
    }

    public void save(String result) {
        System.out.println(Thread.currentThread().getName() + ":" + result);
    }

    public String getResult1() throws Exception {
        Thread.sleep(2000);
        return "result 1";
    }

    public String getResult2() {
        return "result 2";
    }

}
