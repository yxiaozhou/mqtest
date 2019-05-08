package com.tim.threadtest;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class TestCallable implements Callable {
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNamePrefix("demo-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
//        Callable c = new TestCallable();
//        try {
//            System.out.println(c.call()+"");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
        return 111;
    }
}
